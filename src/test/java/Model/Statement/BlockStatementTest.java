/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Assignment.OthersAssignmentsStatement;
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
public class BlockStatementTest {
    
    private final Logger log = Logger.getLogger(BlockStatementTest.class.getName());
    
    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        OthersAssignmentsStatement expressionStatement = new OthersAssignmentsStatement(null);
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "array", LexemeTypes.IDENTIFIERS));
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "+=", LexemeTypes.ASSIGNMENT_OPERATORS));
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "+", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ";", LexemeTypes.DELIMITERS));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        Statement statmennt = expressionStatement.analyze(tokensFlow,tokensFlow.getCurrentToken());
        
        assertEquals(true, statmennt.getChildCount() == 4 && statmennt.toString().equals(SyntacticTypes.OTHERS_ASSIGNMENTS_STATMENT));
    }
    
}
