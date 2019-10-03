/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Switch;

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
public class CaseStatementTest {
    
    private Logger log = Logger.getLogger(CaseStatementTest.class.getName());
    
    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "case", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ":", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "+=", LexemeTypes.ASSIGNMENT_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "sum", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        CaseStatement cases = new CaseStatement(null);
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        assertEquals(5, cases.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
