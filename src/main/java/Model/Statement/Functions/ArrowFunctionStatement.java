/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Functions;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.BlockStatement;
import Model.Statement.Others.ReturnStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class ArrowFunctionStatement extends Statement {

    public ArrowFunctionStatement(Statement root) {
        super(root);
    }

    public ArrowFunctionStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.ARROW_FUNCTION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                Statement parameter = new ParameterStatement(this, tokensFlow.getPositionCurrent());
                parameter = parameter.analyze(tokensFlow, lexeme);
                if (parameter != null) {
                    this.childs.add(parameter);
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                            && lexeme.getWord().equals(","))) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();
                    } else {
                        break;
                    }
                }
            }

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();

                if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                        && lexeme.getWord().equals("-"))) {
                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();

                    if (lexeme != null && (lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)
                            && lexeme.getWord().equals(">"))) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACES)) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();

                            while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {

                                if (lexeme.getType().equals(LexemeTypes.FUNCTIONS) && lexeme.getWord().equals("return")) {

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
                                tokensFlow.move();

                                return this;
                            }
                        }
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
