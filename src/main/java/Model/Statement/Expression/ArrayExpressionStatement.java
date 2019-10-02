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
public class ArrayExpressionStatement extends Statement {

    private NumericExpressionStatement numeric;
    private StringExpressionStatement string;
    private LogicalExpressionStatement logical;
    private RelationalExpressionStatement relational;
    private InvokeFunctionStatement invokeFunction;
    private ArrayExpressionStatement array;

    public ArrayExpressionStatement(Statement root) {
        super(root);
    }

    public ArrayExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACKETS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_BRACKETS)) {
                this.childs.add(lexeme);
                tokensFlow.move();
                return this;
            } else {
                lexeme = (Lexeme) this.recursiveAnalyze(tokensFlow, lexeme);

                if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_BRACKETS)) {
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

    private Statement recursiveAnalyze(TokensFlow tokensFlow, Lexeme lexeme) {
        this.invokeFunction = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
        this.invokeFunction = (InvokeFunctionStatement) this.invokeFunction.analyze(tokensFlow, lexeme);
        if (this.invokeFunction != null) {
            this.childs.add(this.invokeFunction);
            lexeme = tokensFlow.getCurrentToken();

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS) && lexeme.getWord().equals(","))) {
                this.childs.add(lexeme);
                return recursiveAnalyze(tokensFlow, tokensFlow.move());
            } else {
                return lexeme;
            }
        } else {
            this.logical = new LogicalExpressionStatement(this, tokensFlow.getPositionCurrent());
            this.logical = (LogicalExpressionStatement) this.logical.analyze(tokensFlow, lexeme);
            if (this.logical != null) {
                this.childs.add(this.logical);
                lexeme = tokensFlow.getCurrentToken();

                if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS) && lexeme.getWord().equals(","))) {
                    this.childs.add(lexeme);
                    return recursiveAnalyze(tokensFlow, tokensFlow.move());
                } else {
                    return lexeme;
                }
            } else {
                this.relational = new RelationalExpressionStatement(this, tokensFlow.getPositionCurrent());
                this.relational = (RelationalExpressionStatement) this.relational.analyze(tokensFlow, lexeme);
                if (this.relational != null) {
                    this.childs.add(this.relational);
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS) && lexeme.getWord().equals(","))) {
                        this.childs.add(lexeme);
                        return recursiveAnalyze(tokensFlow, tokensFlow.move());
                    } else {
                        return lexeme;
                    }
                } else {
                    this.numeric = new NumericExpressionStatement(this, tokensFlow.getPositionCurrent());
                    this.numeric = (NumericExpressionStatement) this.numeric.analyze(tokensFlow, lexeme);

                    if (this.numeric != null) {
                        this.childs.add(this.numeric);
                        lexeme = tokensFlow.getCurrentToken();

                        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS) && lexeme.getWord().equals(","))) {
                            this.childs.add(lexeme);
                            return recursiveAnalyze(tokensFlow, tokensFlow.move());
                        } else {
                            return lexeme;
                        }
                    } else {
                        this.string = new StringExpressionStatement(this, tokensFlow.getPositionCurrent());
                        this.string = (StringExpressionStatement) this.string.analyze(tokensFlow, lexeme);

                        if (this.string != null) {
                            this.childs.add(this.string);
                            lexeme = tokensFlow.getCurrentToken();

                            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS) && lexeme.getWord().equals(","))) {
                                this.childs.add(lexeme);
                                return recursiveAnalyze(tokensFlow, tokensFlow.move());
                            } else {
                                return lexeme;
                            }
                        } else {
                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACKETS)) {
                                this.array = new ArrayExpressionStatement(this, tokensFlow.getPositionCurrent());
                                this.array = (ArrayExpressionStatement) this.array.analyze(tokensFlow, lexeme);
                                if (this.array != null) {
                                    this.childs.add(this.array);
                                    lexeme = tokensFlow.getCurrentToken();

                                    if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS) && lexeme.getWord().equals(","))) {
                                        this.childs.add(lexeme);
                                        return recursiveAnalyze(tokensFlow, tokensFlow.move());
                                    } else {
                                        return lexeme;
                                    }
                                }
                            } else {
                                return lexeme;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return SyntacticTypes.ARRAY_EXPRESSION_STATEMENT;
    }

}
