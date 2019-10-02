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
public class ArrayExpressionStatementTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
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
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        assertEquals(17, expressionStatement.analyze(tokensFlow,tokensFlow.getCurrentToken()).getChildCount());
    }
}
