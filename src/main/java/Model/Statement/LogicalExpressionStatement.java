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
public class LogicalExpressionStatement extends Statement {

    private RelationalExpressionStatement relational;
    private int openedParenthesis;
    private Lexeme lexeme;
    private boolean operation;
    private final TokensFlow tokensFlow;

    public LogicalExpressionStatement(Statement root, TokensFlow tokensFlow) {
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
        this.lexeme = tokensFlow.getCurrentToken();
        this.tokensFlow = tokensFlow;
        this.operation = false;
        this.tokensFlow.savePositionCurrent();
    }

    @Override
    public String toString() {
        return SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze() {

        if (this.lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

            this.openedParenthesis++;
            this.childs.add(this.lexeme);
            this.lexeme = this.tokensFlow.move();
        }
        this.relational = new RelationalExpressionStatement(this, this.tokensFlow);
        this.relational = (RelationalExpressionStatement) this.relational.analyze();
        if (this.relational != null || (this.lexeme.getType().equals(LexemeTypes.OTHERS)
                && (this.lexeme.getWord().equals("true") || this.lexeme.getWord().equals("false")))) {

            if (this.relational != null) {
                this.tokensFlow.moveTo(this.relational.getPositionTokensFlow());
                this.childs.add(this.relational);
                this.lexeme = this.tokensFlow.getCurrentToken();
            } else {
                this.childs.add(lexeme);
                this.lexeme = this.tokensFlow.move();
            }

            if (this.lexeme != null && this.lexeme.getType().equals(LexemeTypes.LOGICAL_OPERATORS)) {

                this.childs.add(this.lexeme);
                this.lexeme = this.tokensFlow.move();
                this.operation = true;
                
                return this.analyze();

            } else if (this.openedParenthesis != 0 && this.lexeme != null) {
                if (this.lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {

                    this.openedParenthesis--;
                    this.childs.add(this.lexeme);
                    this.lexeme = this.tokensFlow.move();

                    if (this.lexeme != null && !(this.lexeme.getType().equals(LexemeTypes.DELIMITERS)
                            || this.lexeme.getType().equals(LexemeTypes.OPEN_BRACES))
                            && this.lexeme.getType().equals(LexemeTypes.LOGICAL_OPERATORS)) {

                        this.childs.add(this.lexeme);
                        this.lexeme = this.tokensFlow.move();
                        this.operation = true;
                        
                        return this.analyze();

                    } else {
                        if (this.openedParenthesis == 0 && operation) {
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
                if (this.openedParenthesis == 0 && operation) {
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
    public int getPositionTokensFlow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
