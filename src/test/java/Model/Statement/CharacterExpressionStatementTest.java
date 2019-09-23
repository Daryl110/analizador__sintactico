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
public class CharacterExpressionStatementTest {
    
    static final Logger log = Logger.getLogger(CharacterExpressionStatementTest.class.getName());
    
    @Test
    public void testAnalyze() {
        log.info("Character Expression Analyze");
        log.info("Expression to analyze 'a'");
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        // expresion a validar 'a'

        lexemes.add(new Lexeme(0, 0, "'", LexemeTypes.SINGLE_QUOTE));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.CHAR));
        lexemes.add(new Lexeme(0, 0, "'", LexemeTypes.SINGLE_QUOTE));

        CharacterExpressionStatement instance = new CharacterExpressionStatement(null);
        
        lexemes.forEach((lexeme) -> {
            instance.analyze(lexeme);
        });
        
        Assert.assertNotEquals(null, instance.getStatement());
    }
    
}
