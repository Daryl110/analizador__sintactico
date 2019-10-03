/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Iterators;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Assignment.IncrementalDecrementalOperationStatement;
import Model.Statement.Assignment.SimpleAssignmentStatement;
import Model.Statement.BlockStatement;
import Model.Statement.Expression.ExpressionStatement;
import Model.Statement.Others.ReturnStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class ForStatement extends Statement {

    public ForStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    public ForStatement(Statement root) {
        super(root);
    }

    @Override
    public String toString() {
        return SyntacticTypes.FOR_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ITERATIVE_CONTROL_STRUCTURE)
                && lexeme.getWord().equals("for"))) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                this.childs.add(lexeme);

                Statement assignStatement = new SimpleAssignmentStatement(this, tokensFlow.getPositionCurrent());
                assignStatement = assignStatement.analyze(tokensFlow, tokensFlow.move());
                if (assignStatement != null) {
                    this.childs.add(assignStatement);
                    Statement logicalOrRelational = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                    logicalOrRelational = logicalOrRelational.analyze(tokensFlow, tokensFlow.getCurrentToken());
                    if (logicalOrRelational != null && (logicalOrRelational.toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                            || logicalOrRelational.toString().equals(SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT))) {
                        this.childs.add(logicalOrRelational);

                        lexeme = tokensFlow.getCurrentToken();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                            this.childs.add(lexeme);

                            Statement incrementalDecrementalOperation = new IncrementalDecrementalOperationStatement(this, tokensFlow.getPositionCurrent());
                            incrementalDecrementalOperation = incrementalDecrementalOperation.analyze(tokensFlow, tokensFlow.move());
                            if (incrementalDecrementalOperation != null) {
                                this.childs.add(incrementalDecrementalOperation);
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
                                            tokensFlow.move();

                                            return this;
                                        }
                                    }
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
