/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Iterators;

import Model.Lexeme;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class ForStatement extends Statement{

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
