/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daryl Ospina
 */
public class NumericExpressionStatementTest {

    @Test
    public void testAnalize() {
        System.out.println("analize");
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        // expresion a validar ((5-6)*(5+6))/5*(5-7)

        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.GROUPING_SYMBOLS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.GROUPING_SYMBOLS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "-", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.GROUPING_SYMBOLS));
        lexemes.add(new Lexeme(0, 0, "*", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.GROUPING_SYMBOLS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "+", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.GROUPING_SYMBOLS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.GROUPING_SYMBOLS));
        lexemes.add(new Lexeme(0, 0, "/", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "*", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.GROUPING_SYMBOLS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "-", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "7", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.GROUPING_SYMBOLS));

        NumericExpressionStatement instance = new NumericExpressionStatement(null);
        
        lexemes.forEach((lexeme) -> {
            instance.analize(lexeme);
        });
        
        assertNotEquals(null, instance.getStatement());
    }

}
