/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Statement.ExpressionStatement;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class SyntacticAnalizer {

    private ArrayList<Lexeme> lexemes;

    public SyntacticAnalizer(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Statement analyze() throws Exception {
        return new ExpressionStatement(null, new TokensFlow(this.lexemes)).analyze();
    }
}
