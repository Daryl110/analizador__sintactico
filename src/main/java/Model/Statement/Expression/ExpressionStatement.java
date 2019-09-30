/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Expression;

import Model.Lexeme;
import Model.Statement.Structure.Statement;
import Model.TokensFlow;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class ExpressionStatement extends Statement {

    private Statement expression;
    private int positionBack = -1;

    public ExpressionStatement(Statement root) {
        this.root = root;
        this.childs = new ArrayList<>();
    }
    
    public ExpressionStatement(Statement root, int positionBack) {
        this.root = root;
        this.childs = new ArrayList<>();
        this.positionBack = positionBack;
    }

    @Override
    public String toString() {
        return this.expression.toString();
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexem) {
        this.expression = new ArrayExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new LogicalExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new RelationalExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new NumericExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new StringExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        return null;
    }

}
