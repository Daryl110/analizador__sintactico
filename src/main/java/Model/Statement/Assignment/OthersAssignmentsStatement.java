/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Assignment;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Expression.NumericExpressionStatement;
import Model.Statement.Expression.StringExpressionStatement;
import Model.Statement.Functions.InvokeFunctionStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class OthersAssignmentsStatement extends Statement {

    public OthersAssignmentsStatement(Statement root) {
        super(root);
    }

    public OthersAssignmentsStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.OTHERS_ASSIGNMENTS_STATMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.ASSIGNMENT_OPERATORS)
                    && !lexeme.getWord().equals("=")) {
                this.childs.add(lexeme);

                Statement value;

                if (lexeme.getWord().equals("+=")) {
                    value = new StringExpressionStatement(this, tokensFlow.getPositionCurrent());
                    value = value.analyze(tokensFlow, tokensFlow.move());
                    if (value != null) {
                        this.childs.add(value);
                        lexeme = tokensFlow.getCurrentToken();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                            this.childs.add(lexeme);
                            tokensFlow.move();

                            return this;
                        } else {
                            if (this.positionBack != -1) {
                                tokensFlow.moveTo(this.positionBack);
                            } else {
                                tokensFlow.backTrack();
                            }
                            return null;
                        }
                    } else {
                        lexeme = tokensFlow.move();
                    }
                } else {
                    lexeme = tokensFlow.move();
                }
                value = new NumericExpressionStatement(this, tokensFlow.getPositionCurrent());
                value = value.analyze(tokensFlow, lexeme);
                if (value != null) {
                    this.childs.add(value);
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                        this.childs.add(lexeme);
                        tokensFlow.move();

                        return this;
                    } else {
                        if (this.positionBack != -1) {
                            tokensFlow.moveTo(this.positionBack);
                        } else {
                            tokensFlow.backTrack();
                        }
                        return null;
                    }
                } else {
                    value = new InvokeFunctionStatement(this.root, tokensFlow.getPositionCurrent());
                    value = value.analyze(tokensFlow, lexeme);
                    if (value != null) {
                        this.childs.add(value);
                        lexeme = tokensFlow.getCurrentToken();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                            this.childs.add(lexeme);
                            tokensFlow.move();

                            return this;
                        } else {
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
                }
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
