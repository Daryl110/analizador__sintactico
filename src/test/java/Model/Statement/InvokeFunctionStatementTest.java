/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.Statement;
import Model.TokensFlow;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Daryl Ospina
 */
public class InvokeFunctionStatementTest {
    
    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "sum", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "sum", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "-", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, ">", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "sum", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        
        InvokeFunctionStatement invokeFunction = new InvokeFunctionStatement(null);
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        assertEquals(4, invokeFunction.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
