/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.IF;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.BlockStatement;
import Model.Statement.Expression.ExpressionStatement;
import Model.Statement.Others.ReturnStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;
import Model.exceptions.SyntaxError;

/**
 *
 * @author Daryl Ospina
 */
public class IfStatement extends Statement {

    public IfStatement(Statement root) {
        super(root);
    }

    public IfStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.IF_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                && lexeme.getWord().equals("if"))) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();

                Statement logical = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                logical = logical.analyze(tokensFlow, lexeme);
                if (logical != null && (logical.toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                        || logical.toString().equals(SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT))) {
                    this.childs.add(logical);
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACES)) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();

                            while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {

                                if ((lexeme.getType().equals(LexemeTypes.FUNCTIONS) && lexeme.getWord().equals("return"))
                                        || (lexeme.getType().equals(LexemeTypes.OTHERS) && (lexeme.getWord().equals("break")
                                        || lexeme.getWord().equals("continue")))) {

                                    if (lexeme.getWord().equals("return")) {
                                        Statement returnStatement = new ReturnStatement(this, tokensFlow.getPositionCurrent());
                                        returnStatement = returnStatement.analyze(tokensFlow, lexeme);
                                        if (returnStatement != null) {
                                            this.childs.add(returnStatement);
                                            lexeme = tokensFlow.getCurrentToken();
                                            break;
                                        } else {
                                            if (this.positionBack != -1) {
                                                tokensFlow.moveTo(this.positionBack);
                                            } else {
                                                tokensFlow.backTrack();
                                            }
                                            return null;
                                        }
                                    }

                                    this.childs.add(lexeme);
                                    lexeme = tokensFlow.move();

                                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                                        this.childs.add(lexeme);
                                        lexeme = tokensFlow.move();
                                        break;
                                    } else {
                                        if (this.positionBack != -1) {
                                            tokensFlow.moveTo(this.positionBack);
                                        } else {
                                            tokensFlow.backTrack();
                                        }
                                        return null;
                                    }
                                }

                                Statement statement = new BlockStatement(this, tokensFlow.getPositionCurrent());
                                statement = statement.analyze(tokensFlow, lexeme);
                                if (statement != null) {
                                    this.childs.add(statement);
                                    lexeme = tokensFlow.getCurrentToken();
                                }
                            }

                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {
                                this.childs.add(lexeme);
                                lexeme = tokensFlow.move();

                                Statement elseStatement = new ElseStatement(this, tokensFlow.getPositionCurrent());
                                elseStatement = elseStatement.analyze(tokensFlow, lexeme);
                                if (elseStatement != null) {
                                    this.childs.add(elseStatement);
                                }

                                return this;
                            } else {
                                throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba un }");
                            }
                        } else {
                            throw new SyntaxError("[Error] : "
                                    + tokensFlow.getCurrentToken().toString()
                                    + " se esperaba un {");
                        }
                    } else {
                        throw new SyntaxError("[Error] : "
                                + tokensFlow.getCurrentToken().toString()
                                + " se esperaba un )");
                    }
                } else {
                    throw new SyntaxError("[Error] : "
                            + tokensFlow.getCurrentToken().toString()
                            + " se esperaba una operacion logica o relacional");
                }
            } else {
                throw new SyntaxError("[Error] : "
                        + tokensFlow.getCurrentToken().toString()
                        + " se esperaba un (");
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
