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
public class NumericExpressionStatement extends Statement {

    private int openedParenthesis;
    private int positionBack = -1;

    public NumericExpressionStatement(Statement root) {
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
    }

    public NumericExpressionStatement(Statement root, int positionBack) {
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
        this.positionBack = positionBack;
    }

    @Override
    public String toString() {
        return SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

            this.openedParenthesis++;
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.NUMBERS)
                || lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();

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
                        }
                        lexeme = tokensFlow.move();

                        return analyze(tokensFlow, lexeme);

                    } else {
                        if (this.openedParenthesis == 0) {
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
                if (this.openedParenthesis == 0) {
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
                }
                lexeme = tokensFlow.move();

                return analyze(tokensFlow, lexeme);

            } else {
                if (this.openedParenthesis == 0) {
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
