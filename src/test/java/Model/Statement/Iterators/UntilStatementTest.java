/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Iterators;

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
public class UntilStatementTest {
    
    private Logger log = Logger.getLogger(UntilStatementTest.class.getName());

    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "until", LexemeTypes.ITERATIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "i", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "<", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "break", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        UntilStatement forStatement = new UntilStatement(null);
        
        assertEquals(8, forStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
