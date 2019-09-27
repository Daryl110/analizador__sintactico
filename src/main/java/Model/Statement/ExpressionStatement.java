/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.Statement;
import Model.TokensFlow;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class ExpressionStatement extends Statement{
    
    private Statement expression;
    private final TokensFlow tokensFlow;
    
    public ExpressionStatement(Statement root, TokensFlow tokensFlow) {
        this.root = root;
        this.childs = new ArrayList<>();
        this.tokensFlow = tokensFlow;
        this.tokensFlow.savePositionCurrent();
    }
    
    @Override
    public String toString() {
        return this.expression.toString();
    }

    @Override
    public Statement analyze() {
        this.expression = new LogicalExpressionStatement(this.root, this.tokensFlow);
        this.expression = this.expression.analyze();
        if (this.expression != null) {
            return this.expression;
        }
        this.tokensFlow.moveTo(0);
        this.expression = new RelationalExpressionStatement(this.root, this.tokensFlow);
        this.expression = this.expression.analyze();
        if (this.expression != null) {
            return this.expression;
        }
        this.tokensFlow.moveTo(0);
        this.expression = new NumericExpressionStatement(this.root, this.tokensFlow);
        this.expression = this.expression.analyze();
        if (this.expression != null) {
            return this.expression;
        }
        return null;
    }

    @Override
    public int getPositionTokensFlow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
