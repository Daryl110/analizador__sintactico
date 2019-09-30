/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.Statement.Structure.Statement;
import Model.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class InvokeFunctionStatement extends Statement {

    public InvokeFunctionStatement(Statement root) {
        super(root);
    }

    public InvokeFunctionStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
