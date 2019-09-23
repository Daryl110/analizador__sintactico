/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Statement.Structure.Statement;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class SyntacticAnalizerTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "'", LexemeTypes.SINGLE_QUOTE));
        lexemes.add(new Lexeme(0, 0, "d", LexemeTypes.CHAR));
        lexemes.add(new Lexeme(0, 0, "'", LexemeTypes.SINGLE_QUOTE));
        
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "-", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "*", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "+", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "/", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "*", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "-", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "7", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        
        assertNotEquals(null, new SyntacticAnalizer(lexemes).analyze());
    }
    
}
