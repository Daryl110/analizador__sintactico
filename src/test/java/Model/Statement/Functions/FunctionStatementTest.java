/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Functions;

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
public class FunctionStatementTest {
    
    private Logger log = Logger.getLogger(FunctionStatementTest.class.getName());
    
    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "function", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "number", LexemeTypes.DATA_TYPE));
        lexemes.add(new Lexeme(0, 0, "hello", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "number", LexemeTypes.DATA_TYPE));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "Array", LexemeTypes.DATA_TYPE));
        lexemes.add(new Lexeme(0, 0, "b", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "hola", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        FunctionStatement function = new FunctionStatement(null);
        
        assertEquals(11, function.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
