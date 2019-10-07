/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Expression;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Functions.InvokeFunctionStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;
import Model.exceptions.SyntaxError;

/**
 *
 * @author Daryl Ospina
 */
public class LogicalExpressionStatement extends Statement {

    private int openedParenthesis;
    private boolean operation;
    private boolean withTerminalBoolean;

    public LogicalExpressionStatement(Statement root) {
        super(root);
        this.openedParenthesis = 0;
        this.operation = false;
        this.withTerminalBoolean = false;
    }

    public LogicalExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
        this.openedParenthesis = 0;
        this.operation = false;
        this.withTerminalBoolean = false;
    }

    @Override
    public String toString() {
        return SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
            this.childs.add(lexeme);
            this.openedParenthesis++;
            return this.analyze(tokensFlow, tokensFlow.move());
        }

        Statement relational = new RelationalExpressionStatement(this, tokensFlow.getPositionCurrent());
        relational = relational.analyze(tokensFlow, lexeme);
        if (relational != null || (lexeme != null && lexeme.getType().equals(LexemeTypes.OTHERS)
                && (lexeme.getWord().equals("true") || lexeme.getWord().equals("false")))) {
            if (relational != null) {
                this.childs.add(relational);
                return this.recursiveAnalyze(tokensFlow, tokensFlow.getCurrentToken());
            } else {
                this.childs.add(lexeme);
                this.withTerminalBoolean = true;
                return this.recursiveAnalyze(tokensFlow, tokensFlow.move());
            }
        } else {
            int size = this.childs.size() - 1;
            if (size > 0) {
                if (((Lexeme) (this.childs.get(size))).getType().equals(LexemeTypes.LOGICAL_OPERATORS)) {
                    throw new SyntaxError("la expresion logica no puede terminar con un operador");
                }
            }
            if (this.positionBack != -1) {
                tokensFlow.moveTo(this.positionBack);
            } else {
                tokensFlow.backTrack();
            }
            return null;
        }
    }

    private Statement recursiveAnalyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis > 0) {
            this.childs.add(lexeme);
            this.openedParenthesis--;
            return this.recursiveAnalyze(tokensFlow, tokensFlow.move());
        }
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.LOGICAL_OPERATORS)) {
            this.childs.add(lexeme);
            this.operation = true;
            return analyze(tokensFlow, tokensFlow.move());
        } else {
            if (this.openedParenthesis == 0 && this.childs.size() > 0 && (this.operation || this.withTerminalBoolean)) {
                if (this.childs.size() == 1) {
                    lexeme = (Lexeme) this.childs.get(this.childs.size() - 1);
                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                        if (this.positionBack != -1) {
                            tokensFlow.moveTo(this.positionBack);
                        } else {
                            tokensFlow.backTrack();
                        }
                        return null;
                    }
                }
                return this;
            } else if (this.openedParenthesis > 0) {
                throw new SyntaxError("los parentesis de la expresion logica, estan mal distribuidos.");
            } else {
                if (this.positionBack != -1) {
                    tokensFlow.moveTo(this.positionBack);
                } else {
                    tokensFlow.backTrack();
                }
                return null;
            }
        }
    }

}
