/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daryl Ospina
 */
public class NumericExpressionStatementTest {
    
    static final Logger log = Logger.getLogger(NumericExpressionStatementTest.class.getName());

    @Test
    public void testAnalize() {
        log.info("Character Expression Analyze");
        log.info("Expression to analyze ((5-6)*(5+6))/5*(5-7)");
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        // expresion a validar ((5-6)*(5+6))/5*(5-7)

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

        NumericExpressionStatement instance = new NumericExpressionStatement(null);
        
        lexemes.forEach((lexeme) -> {
            instance.analyze(lexeme);
        });
        
        Assert.assertNotEquals(null, instance.getStatement());
    }

}
