/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Expression;

import Model.Statement.Expression.ExpressionStatement;
import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class ExpressionStatementTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "[", LexemeTypes.OPEN_BRACKETS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(0, 0, "&&", LexemeTypes.LOGICAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(0, 0, "]", LexemeTypes.CLOSE_BRACKETS));
        
        ExpressionStatement expressionStatement = new ExpressionStatement(null, 0);
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        Statement statement = expressionStatement.analyze(tokensFlow,tokensFlow.getCurrentToken());
        
        assertEquals(true, (statement.toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT) 
                && statement.getChildCount() == 5));
    }
    
}
