/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Assignment;

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
public class IncrementalDecrementalOperationStatementTest {
    
    private final Logger log = Logger.getLogger(IncrementalDecrementalOperationStatement.class.getName());

    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "++", LexemeTypes.INCREMENTAL_AND_DECREMENTAL_OPERATORS));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        IncrementalDecrementalOperationStatement incrementalDecrementalOperationStatement = new IncrementalDecrementalOperationStatement(null);
        
        assertEquals(2, incrementalDecrementalOperationStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
