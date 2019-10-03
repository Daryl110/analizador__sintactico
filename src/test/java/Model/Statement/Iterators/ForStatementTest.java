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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class ForStatementTest {

    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "for", LexemeTypes.ITERATIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "i", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "i", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "<", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "i", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "++", LexemeTypes.INCREMENTAL_AND_DECREMENTAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "break", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        ForStatement forStatement = new ForStatement(null);
        
        assertEquals(11, forStatement.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}