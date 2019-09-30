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
public class StringExpressionStatement extends Statement {

    private int openedParenthesis;

    public StringExpressionStatement(Statement root) {
        super(root);
    }

    public StringExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

            this.openedParenthesis++;
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.STRINGS)) {

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                    && lexeme.getWord().equals("+"))) {
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
                            && ((lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                            && lexeme.getWord().equals("+"))
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
                    && ((lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                    && lexeme.getWord().equals("+"))
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

    @Override
    public String toString() {
        return SyntacticTypes.STRING_EXPRESSION_STATEMENT;
    }

}
