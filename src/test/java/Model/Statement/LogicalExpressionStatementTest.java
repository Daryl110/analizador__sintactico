/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

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
public class LogicalExpressionStatementTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "true", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "&&", LexemeTypes.LOGICAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "52", LexemeTypes.NUMBERS));
        
        LogicalExpressionStatement logicalExpressionStatement = new LogicalExpressionStatement(null, new TokensFlow(lexemes));
        
        assertEquals(3, logicalExpressionStatement.analyze().getChildCount());
    }
}
