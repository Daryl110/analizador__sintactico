/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Expression.ExpressionStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class SimpleAssignmentStatement extends Statement {
    
    private Statement expression;

    public SimpleAssignmentStatement(Statement root) {
        this.childs = new ArrayList<>();
        this.root = root;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme.getType().equals(LexemeTypes.DATA_TYPE)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }

        if (lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme.getType().equals(LexemeTypes.ASSIGNMENT_OPERATORS)
                    && lexeme.getWord().equals("=")) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
                
                this.expression = new ExpressionStatement(this.root, tokensFlow.getPositionCurrent());
                this.expression = this.expression.analyze(tokensFlow, lexeme);
                if (this.expression != null) {
                    this.childs.add(this.expression);
                    lexeme = tokensFlow.getCurrentToken();
                }else{
                    // invocar funcion
                    return null;
                }
            }

            if (lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.childs.add(lexeme);
                tokensFlow.move();

                return this;
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public String toString() {
        return SyntacticTypes.SIMPLE_ASSIGNMENT_STATMENT;
    }

}
