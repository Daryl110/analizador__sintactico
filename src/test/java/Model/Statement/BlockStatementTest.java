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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daryl Ospina
 */
public class BlockStatementTest {
    
    @Test
    public void testAnalyze() {
        System.out.println("analyze");
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        OthersAssignmentsStatement expressionStatement = new OthersAssignmentsStatement(null);
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "array", LexemeTypes.IDENTIFIERS));
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "+=", LexemeTypes.ASSIGNMENT_OPERATORS));
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "+", LexemeTypes.ARITHMETIC_OPERATORS));
        lexemes.add(new Lexeme(expressionStatement, 0, 0, "5", LexemeTypes.NUMBERS));
        
        lexemes.add(new Lexeme(expressionStatement, 0, 0, ";", LexemeTypes.DELIMITERS));
        
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        Statement statmennt = expressionStatement.analyze(tokensFlow,tokensFlow.getCurrentToken());
        
        assertEquals(true, statmennt.getChildCount() == 4 && statmennt.toString().equals(SyntacticTypes.OTHERS_ASSIGNMENTS_STATMENT));
    }
    
}
