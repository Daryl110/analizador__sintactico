/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Iterators;

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
public class ForEachStatement extends Statement {

    public ForEachStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    public ForEachStatement(Statement root) {
        super(root);
    }

    @Override
    public String toString() {
        return SyntacticTypes.FOR_EACH_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ITERATIVE_CONTROL_STRUCTURE)
                && lexeme.getWord().equals("for"))) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
                
                if (lexeme != null && lexeme.getType().equals(LexemeTypes.DATA_TYPE)) {
                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();
                    
                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();
                        
                        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                                && lexeme.getWord().equals(":"))) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();
                            
                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
                                this.childs.add(lexeme);
                                lexeme = tokensFlow.move();
                                
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
