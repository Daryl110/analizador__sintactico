/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Others;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Expression.ExpressionStatement;
import Model.Statement.Functions.ArrowFunctionStatement;
import Model.Statement.Functions.InvokeFunctionStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;
import Model.exceptions.SyntaxError;

/**
 *
 * @author Daryl Ospina
 */
public class ReturnStatement extends Statement {

    public ReturnStatement(Statement root) {
        super(root);
    }

    public ReturnStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.RETURN_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null
                && (lexeme.getType().equals(LexemeTypes.FUNCTIONS) && lexeme.getWord().equals("return"))) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                    && (lexeme.getWord().equals("NaN") || lexeme.getWord().equals("null")))) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
            } else {
                Statement expression = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                expression = expression.analyze(tokensFlow, lexeme);
                if (expression != null) {
                    this.childs.add(expression);
                    lexeme = tokensFlow.getCurrentToken();
                } else {
                    Statement invokeFunction = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
                    invokeFunction = invokeFunction.analyze(tokensFlow, lexeme);
                    if (invokeFunction != null) {
                        this.childs.add(invokeFunction);
                        lexeme = tokensFlow.getCurrentToken();
                    } else {
                        Statement arrowFunction = new ArrowFunctionStatement(this, tokensFlow.getPositionCurrent());
                        arrowFunction = arrowFunction.analyze(tokensFlow, lexeme);
                        if (arrowFunction != null) {
                            this.childs.add(arrowFunction);
                            lexeme = tokensFlow.getCurrentToken();
                        } else {
                            throw new SyntaxError("[Error] : "
                                    + tokensFlow.getCurrentToken().toString()
                                    + " se esperaba una expresion valida de retorno");
                        }
                    }
                }
            }

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.childs.add(lexeme);
                tokensFlow.move();
                return this;
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
