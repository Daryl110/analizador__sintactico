/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class CompilationUnitStatement extends Statement{

    public CompilationUnitStatement() {
        this.childs = new ArrayList<>();
        this.root = null;
    }

    @Override
    public boolean analize(Lexeme lexeme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Statement getStatement() {
        return this;
    }
    
}
