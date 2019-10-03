/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Expression;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
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
public class ExpressionStatementTest {
    
    private final Logger log = Logger.getLogger(ExpressionStatementTest.class.getName());
    
    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        ExpressionStatement expressionStatement = new ExpressionStatement(null, 0);
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "[", LexemeTypes.OPEN_BRACKETS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ",", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "&&", LexemeTypes.LOGICAL_OPERATORS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "]", LexemeTypes.CLOSE_BRACKETS));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        Statement statement = expressionStatement.analyze(tokensFlow,tokensFlow.getCurrentToken());
        
        assertEquals(true, (statement.toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT) 
                && statement.getChildCount() == 5));
    }
    
}
