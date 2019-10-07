/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Assignment;

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
public class SimpleAssignmentStatement extends Statement {

    private Statement expression;

    public SimpleAssignmentStatement(Statement root) {
        super(root);
    }

    public SimpleAssignmentStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.SIMPLE_ASSIGNMENT_STATMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme.getType().equals(LexemeTypes.DATA_TYPE)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }

        if (lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme.getType().equals(LexemeTypes.ASSIGNMENT_OPERATORS)
                    && lexeme.getWord().equals("=")) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();

                if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                        && (lexeme.getWord().equals("NaN") || lexeme.getWord().equals("null")))) {
                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();
                } else {
                    this.expression = new ArrowFunctionStatement(this.root, tokensFlow.getPositionCurrent());
                    this.expression = this.expression.analyze(tokensFlow, lexeme);
                    if (this.expression != null) {
                        this.childs.add(this.expression);
                        lexeme = tokensFlow.getCurrentToken();
                    } else {
                        this.expression = new InvokeFunctionStatement(this.root, tokensFlow.getPositionCurrent());
                        this.expression = this.expression.analyze(tokensFlow, lexeme);
                        if (this.expression != null) {
                            this.childs.add(this.expression);
                            lexeme = tokensFlow.getCurrentToken();
                        } else {
                            this.expression = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                            this.expression = this.expression.analyze(tokensFlow, lexeme);
                            if (this.expression != null) {
                                this.childs.add(this.expression);
                                lexeme = tokensFlow.getCurrentToken();
                            } else {
                                throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba una expresion valida ");
                            }
                        }
                    }
                }
            } else if ((!lexeme.getType().equals(LexemeTypes.ASSIGNMENT_OPERATORS) || !(lexeme.getType().equals(LexemeTypes.ASSIGNMENT_OPERATORS)
                    && lexeme.getWord().equals("="))) && !lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                if (this.positionBack != -1) {
                    tokensFlow.moveTo(this.positionBack);
                } else {
                    tokensFlow.backTrack();
                }
                return null;
            }

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.childs.add(lexeme);
                tokensFlow.move();

                return this;
            } else {
                if (lexeme == null) {
                    lexeme = tokensFlow.moveTo(tokensFlow.getPositionCurrent() - 1);
                    throw new SyntaxError("[Error] : "
                            + "se esperaba un ; al final de " + this.toString() 
                            + " posicion: row: " + lexeme.getRow() + " - columna: " + lexeme.getColumn());
                } else {
                    throw new SyntaxError("[Error] : "
                            + tokensFlow.moveTo(tokensFlow.getPositionCurrent() - 1).toString()
                            + " se esperaba un ; ");
                }
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
