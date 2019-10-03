/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Others;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.TokensFlow;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class ReturnStatementTest {
    
    private Logger log = Logger.getLogger(ReturnStatementTest.class.getName());

    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "0", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
       
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        ReturnStatement returnStatement = new ReturnStatement(null);
        
        assertEquals(3, returnStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
