/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Expression;

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
public class StringExpressionStatementTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(0, 0, "+", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "+", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        
        StringExpressionStatement stringExpressionStatement = new StringExpressionStatement(null);
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        assertEquals(9, stringExpressionStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    
    }
}
