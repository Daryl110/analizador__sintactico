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

/**
 *
 * @author Daryl Ospina
 */
public class NumericExpressionStatement extends Statement {

    private int openedParenthesis;
    private InvokeFunctionStatement invokeFunction;
    private boolean invocationFunction;
    private boolean operator;
    private boolean endOperator;

    public NumericExpressionStatement(Statement root) {
        super(root);
        this.openedParenthesis = 0;
        this.invocationFunction = false;
        this.operator = false;
        this.endOperator = false;
    }

    public NumericExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
        this.openedParenthesis = 0;
        this.invocationFunction = false;
        this.operator = false;
        this.endOperator = false;
    }

    @Override
    public String toString() {
        return SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                && lexeme.getWord().equals("-"))) {

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

            this.openedParenthesis++;
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }
        this.invokeFunction = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
        this.invokeFunction = (InvokeFunctionStatement) this.invokeFunction.analyze(tokensFlow, lexeme);
        if (this.invokeFunction != null || (lexeme != null && lexeme.getType().equals(LexemeTypes.NUMBERS)
                || lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS))) {

            if (this.invokeFunction != null) {
                this.childs.add(this.invokeFunction);
                lexeme = tokensFlow.getCurrentToken();
                this.invocationFunction = true;
            } else {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
            }
            this.endOperator = false;

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
                this.operator = true;
                this.endOperator = true;

                return analyze(tokensFlow, lexeme);

            } else if (this.openedParenthesis != 0 && lexeme != null) {
                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {

                    this.openedParenthesis--;
                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();

                    if (lexeme != null && !(lexeme.getType().equals(LexemeTypes.DELIMITERS)
                            || lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                            && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                            || (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis != 0))) {

                        this.childs.add(lexeme);
                        if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                            this.openedParenthesis--;
                        } else {
                            this.endOperator = true;
                        }
                        lexeme = tokensFlow.move();

                        return analyze(tokensFlow, lexeme);

                    } else {
                        if (this.openedParenthesis == 0
                                && ((this.invocationFunction && this.operator)
                                || !this.invocationFunction) && !this.endOperator) {
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
                if (this.openedParenthesis == 0
                        && ((this.invocationFunction && this.operator)
                        || !this.invocationFunction) && !this.endOperator) {
                    return this;
                }
                if (this.positionBack != -1) {
                    tokensFlow.moveTo(this.positionBack);
                } else {
                    tokensFlow.backTrack();
                }
                return null;
            }
        } else if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis != 0) {

            if (!(lexeme.getType().equals(LexemeTypes.DELIMITERS)
                    || lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                    && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                    || lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS))) {

                this.childs.add(lexeme);
                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                    this.openedParenthesis--;
                } else {
                    this.endOperator = true;
                }
                lexeme = tokensFlow.move();

                return analyze(tokensFlow, lexeme);

            } else {
                if (this.openedParenthesis == 0
                        && ((this.invocationFunction && this.operator)
                        || !this.invocationFunction) && !this.endOperator) {
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
            if (this.openedParenthesis == 0
                    && ((this.invocationFunction && this.operator)
                    || !this.invocationFunction)
                    && this.childs.size() > 0 && !this.endOperator) {
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
