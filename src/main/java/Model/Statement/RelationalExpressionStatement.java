/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.SyntacticTypes;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class RelationalExpressionStatement extends Statement {

    private int state;
    private final int STATE_TOTAL;
    private Statement expression, expression1;

    public static String[] typesLexemes = {
        LexemeTypes.RELATIONAL_OPERATORS
    };

    public RelationalExpressionStatement(Statement root) {
        this.root = root;
        this.state = 0;
        this.STATE_TOTAL = 3;
        this.childs = new ArrayList<>();
        this.expression = new ExpressionStatement(this);
        this.expression1 = new ExpressionStatement(this);
    }
    
    public RelationalExpressionStatement(Statement root, int initialState) {
        this.root = root;
        this.state = initialState;
        this.STATE_TOTAL = 3;
        this.childs = new ArrayList<>();
        this.expression = new ExpressionStatement(this);
        this.expression1 = new ExpressionStatement(this);
    }

    @Override
    public boolean analyze(Lexeme lexeme) throws Exception{
        switch (this.state) {
            case 0:
                if (this.expression.analyze(lexeme)) {
                    Statement exp = this.expression.getStatement();
                    if (exp != null) {
                        this.childs.add(exp);
                        this.state = 1;
                    }
                    return true;
                }
                return false;
            case 1:
                if (lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)) {
                    this.childs.add(lexeme);
                    this.state = 2;
                    return true;
                }
                return false;
            case 2:
                if (this.expression1.analyze(lexeme)) {
                    Statement exp = this.expression1.getStatement();
                    if (exp != null) {
                        this.childs.add(exp);
                        this.state = 3;
                        this.expression = new ExpressionStatement(this);
                        this.expression1 = new ExpressionStatement(this);
                    }
                    return true;
                }
                return false;
            default:
                this.state = 1;
                return this.analyze(lexeme);
        }
    }

    @Override
    public Statement getStatement() {
        if (this.state == this.STATE_TOTAL) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return SyntacticTypes.RELATIONAL_EXPRESSION;
    }

    public static boolean lexemesIs(String type) {
        for (String typeLexeme : RelationalExpressionStatement.typesLexemes) {
            if (type.equals(typeLexeme)) {
                return true;
            }
        }
        return false;
    }

    public void setExpression(Statement expression) {
        this.expression = expression;
        this.state = 1;
    }

    public void setExpression1(Statement expression1) {
        this.expression1 = expression1;
    }
}
