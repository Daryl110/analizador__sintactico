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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class ArrayExpressionStatementTest {
    
    private final Logger log = Logger.getLogger(ArrayExpressionStatementTest.class.getName());
    
    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        ArrayExpressionStatement expressionStatement = new ArrayExpressionStatement(null);
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "[", LexemeTypes.OPEN_BRACKETS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "[", LexemeTypes.OPEN_BRACKETS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "]", LexemeTypes.CLOSE_BRACKETS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "]", LexemeTypes.CLOSE_BRACKETS));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        assertEquals(17, expressionStatement.analyze(tokensFlow,tokensFlow.getCurrentToken()).getChildCount());
    }
}
