/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Statement.BlockStatement;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class SyntacticAnalyzer {

    private final ArrayList<Lexeme> lexemes;

    public SyntacticAnalyzer(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Statement analyze() throws Exception {
        TokensFlow tokensFlow = new TokensFlow(this.lexemes);
        return new BlockStatement(null).analyze(tokensFlow, tokensFlow.getCurrentToken());
    }
}
