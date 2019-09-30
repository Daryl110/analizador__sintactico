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

/**
 *
 * @author Daryl Ospina
 */
public class RelationalExpressionStatement extends Statement {

    private int openedParenthesis;
    private NumericExpressionStatement numeric;
    private StringExpressionStatement string;
    private boolean operation;

    public RelationalExpressionStatement(Statement root) {
        super(root);
        this.openedParenthesis = 0;
        this.operation = false;
    }

    public RelationalExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
        this.openedParenthesis = 0;
        this.operation = false;
    }

    @Override
    public String toString() {
        return SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

            this.openedParenthesis++;
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }

        this.numeric = new NumericExpressionStatement(this, tokensFlow.getPositionCurrent());
        if (this.numeric.analyze(tokensFlow, lexeme) != null) {

            this.childs.add(this.numeric);
            lexeme = tokensFlow.getCurrentToken();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)) {

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
                            && (lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)
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
        } else if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis != 0) {
            if (!(lexeme.getType().equals(LexemeTypes.DELIMITERS)
                    || lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                    && (lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)
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
            this.string = new StringExpressionStatement(this, tokensFlow.getPositionCurrent());
            if (this.string.analyze(tokensFlow, lexeme) != null) {
                this.childs.add(this.string);
                lexeme = tokensFlow.getCurrentToken();

                if (lexeme != null && lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)) {

                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();
                    this.operation = true;

                    return this.analyze(tokensFlow, lexeme);

                } else if (this.openedParenthesis != 0 && lexeme != null) {
                    if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {

                        this.openedParenthesis--;
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        if (!(lexeme.getType().equals(LexemeTypes.DELIMITERS)
                                || lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                                && (lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)
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

}
