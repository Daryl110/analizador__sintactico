/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Expression;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class LogicalExpressionStatement extends Statement {

    private RelationalExpressionStatement relational;
    private int openedParenthesis;
    private int positionBack = -1;
    private boolean operation;

    public LogicalExpressionStatement(Statement root) {
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
        this.operation = false;
    }

    public LogicalExpressionStatement(Statement root, int positionBack) {
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
        this.operation = false;
        this.positionBack = positionBack;
    }

    @Override
    public String toString() {
        return SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.LOGICAL_OPERATORS)
                && lexeme.getWord().equals("!")) {

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

            this.openedParenthesis++;
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }
        this.relational = new RelationalExpressionStatement(this, tokensFlow.getPositionCurrent());
        this.relational = (RelationalExpressionStatement) this.relational.analyze(tokensFlow, lexeme);
        if (this.relational != null || (lexeme != null && lexeme.getType().equals(LexemeTypes.OTHERS)
                && (lexeme.getWord().equals("true") || lexeme.getWord().equals("false")))) {

            if (this.relational != null) {
                this.childs.add(this.relational);
                lexeme = tokensFlow.getCurrentToken();
            } else {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
            }

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.LOGICAL_OPERATORS)) {

                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
                this.operation = true;

                return this.analyze(tokensFlow, lexeme);

            } else if (this.openedParenthesis != 0 && lexeme != null) {
                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {

                    this.openedParenthesis--;
                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();

                    if (lexeme != null && !(lexeme.getType().equals(LexemeTypes.DELIMITERS)
                            || lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                            && (lexeme.getType().equals(LexemeTypes.LOGICAL_OPERATORS)
                            || (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis != 0))) {

                        this.childs.add(lexeme);
                        if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                            this.openedParenthesis--;
                        }
                        lexeme = tokensFlow.move();
                        this.operation = true;

                        return this.analyze(tokensFlow, lexeme);

                    } else {
                        if (this.openedParenthesis == 0 && operation) {
                            return this;
                        }
                        if (this.positionBack != -1) {
                            tokensFlow.moveTo(this.positionBack);
                        } else {
                            tokensFlow.backTrack();
                        }
                        return null;
                    }
                } else {
                    if (this.positionBack != -1) {
                        tokensFlow.moveTo(this.positionBack);
                    } else {
                        tokensFlow.backTrack();
                    }
                    return null;
                }
            } else {
                if (this.openedParenthesis == 0 && operation) {
                    return this;
                }
                if (this.positionBack != -1) {
                    tokensFlow.moveTo(this.positionBack);
                } else {
                    tokensFlow.backTrack();
                }
                return null;
            }
        } else if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)  && this.openedParenthesis != 0) {

            if (!(lexeme.getType().equals(LexemeTypes.DELIMITERS)
                    || lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                    && (lexeme.getType().equals(LexemeTypes.LOGICAL_OPERATORS)
                    || lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS))) {

                this.childs.add(lexeme);
                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                    this.openedParenthesis--;
                }
                lexeme = tokensFlow.move();
                this.operation = true;

                return this.analyze(tokensFlow, lexeme);

            } else {
                if (this.openedParenthesis == 0 && operation) {
                    return this;
                }
                if (this.positionBack != -1) {
                    tokensFlow.moveTo(this.positionBack);
                } else {
                    tokensFlow.backTrack();
                }
                return null;
            }
        } else {
            if (this.openedParenthesis == 0 && this.childs.size() > 0) {
                return this;
            }
            if (this.positionBack != -1) {
                tokensFlow.moveTo(this.positionBack);
            } else {
                tokensFlow.backTrack();
            }
            return null;
        }
    }

}
