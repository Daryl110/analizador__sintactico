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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class ParameterStatementTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "Array", LexemeTypes.DATA_TYPE));
        lexemes.add(new Lexeme(0, 0, "array", LexemeTypes.IDENTIFIERS));
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        ParameterStatement parameterStatement = new ParameterStatement(null);
        
        assertEquals(2, parameterStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
