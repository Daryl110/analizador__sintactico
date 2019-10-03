/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.IF;

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
public class ElseStatementTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "else", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "if", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "true", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        lexemes.add(new Lexeme(0, 0, "else", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        ElseStatement elseStatement = new ElseStatement(null);
        
        assertEquals(4, elseStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
