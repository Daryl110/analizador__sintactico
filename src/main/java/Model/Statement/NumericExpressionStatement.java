/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

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
    private Lexeme lexeme;
    private final TokensFlow tokensFlow;

    public NumericExpressionStatement(Statement root, TokensFlow tokensFlow) {
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
        this.lexeme = tokensFlow.getCurrentToken();
        this.tokensFlow = tokensFlow;
        this.tokensFlow.savePositionCurrent();
    }

    @Override
    public String toString() {
        return SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze() {

        if (this.lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

            this.openedParenthesis++;
            this.childs.add(this.lexeme);
            this.lexeme = this.tokensFlow.move();
        }
        if (this.lexeme.getType().equals(LexemeTypes.NUMBERS)
                || this.lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {

            this.childs.add(this.lexeme);
            this.lexeme = this.tokensFlow.move();

            if (this.lexeme != null && this.lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {
                this.childs.add(this.lexeme);
                this.lexeme = this.tokensFlow.move();

                return this.analyze();

            } else if (this.openedParenthesis != 0 && this.lexeme != null) {
                if (this.lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {

                    this.openedParenthesis--;
                    this.childs.add(this.lexeme);
                    this.lexeme = this.tokensFlow.move();
                    
                    if (this.lexeme != null && !(this.lexeme.getType().equals(LexemeTypes.DELIMITERS)
                            || this.lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                            && this.lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {

                        this.childs.add(this.lexeme);
                        this.lexeme = this.tokensFlow.move();
                        
                        return this.analyze();

                    } else {
                        if (this.openedParenthesis == 0) {
                            return this;
                        }
                        this.tokensFlow.backTrack();
                        return null;
                    }
                } else {
                    this.tokensFlow.backTrack();
                    return null;
                }
            } else {
                if (this.openedParenthesis == 0) {
                    return this;
                }
                this.tokensFlow.backTrack();
                return null;
            }
        } else {
            this.tokensFlow.backTrack();
            return null;
        }
    }
    
    @Override
    public int getPositionTokensFlow(){
        return this.tokensFlow.getPositionCurrent();
    }

}
