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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class IncrementalDecrementalOperationStatementTest {

    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "++", LexemeTypes.INCREMENTAL_AND_DECREMENTAL_OPERATORS));
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        IncrementalDecrementalOperationStatement incrementalDecrementalOperationStatement = new IncrementalDecrementalOperationStatement(null);
        
        assertEquals(2, incrementalDecrementalOperationStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
