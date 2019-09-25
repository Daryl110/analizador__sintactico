/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daryl Ospina
 */
public class ExpressionStatementTest {
    
    static final Logger log = Logger.getLogger(ExpressionStatementTest.class.getName());
    
    @Test
    public void testAnalyze() {
        log.info("Expression Analyze");
        log.info("Expression to analyze 'a' o ((5-6)*(5+6))/5*(5-7)");
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        // expresion a validar 'a' o ((5-6)*(5+6))/5*(5-7)

        lexemes.add(new Lexeme(0, 0, "'a'", LexemeTypes.CHAR));
        
//        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
//        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
//        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
//        lexemes.add(new Lexeme(0, 0, "-", LexemeTypes.ARITHMETIC_OPERATORS));
//        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
//        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
//        lexemes.add(new Lexeme(0, 0, "*", LexemeTypes.ARITHMETIC_OPERATORS));
//        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
//        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
//        lexemes.add(new Lexeme(0, 0, "+", LexemeTypes.ARITHMETIC_OPERATORS));
//        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
//        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
//        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
//        lexemes.add(new Lexeme(0, 0, "/", LexemeTypes.ARITHMETIC_OPERATORS));
//        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
//        lexemes.add(new Lexeme(0, 0, "*", LexemeTypes.ARITHMETIC_OPERATORS));
//        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
//        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
//        lexemes.add(new Lexeme(0, 0, "-", LexemeTypes.ARITHMETIC_OPERATORS));
//        lexemes.add(new Lexeme(0, 0, "7", LexemeTypes.NUMBERS));
//        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));

        ExpressionStatement instance = new ExpressionStatement(null);
        
        lexemes.forEach((lexeme) -> {
            try {
                instance.analyze(lexeme);
            } catch (Exception ex) {
                Logger.getLogger(ExpressionStatementTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Assert.assertNotEquals(null, instance.getStatement());
    }
}
