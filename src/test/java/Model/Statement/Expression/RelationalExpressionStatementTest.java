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
public class RelationalExpressionStatementTest {
    
    private Logger log = Logger.getLogger(RelationalExpressionStatementTest.class.getName());

    @Test
    public void testAnalyze() {

        ArrayList<Lexeme> lexemes = new ArrayList<>();

        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.STRINGS));
        lexemes.add(new Lexeme(0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "==", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());

        RelationalExpressionStatement relationalExpressionStatement = new RelationalExpressionStatement(null);
        
        TokensFlow tokenFlow = new TokensFlow(lexemes);
        
        
        assertEquals(9, relationalExpressionStatement.analyze( tokenFlow,tokenFlow.getCurrentToken()).getChildCount());
    }

}
