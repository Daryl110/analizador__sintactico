/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

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
public class CompilationUnitTest {
    
    private final Logger log = Logger.getLogger(CompilationUnitTest.class.getName());
    
    @Test
    public void testAnalyze() {
        
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        
        lexemes.add(new Lexeme(0, 0, "if", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ">", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        lexemes.add(new Lexeme(0, 0, "switch", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ">", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "case", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "true", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, ":", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "switch", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "5", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ">", LexemeTypes.RELATIONAL_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "6", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "{", LexemeTypes.OPEN_BRACES));
        lexemes.add(new Lexeme(0, 0, "case", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "true", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, ":", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "case", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "true", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, ":", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "default", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, ":", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "number", LexemeTypes.DATA_TYPE));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "=", LexemeTypes.ASSIGNMENT_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "[", LexemeTypes.OPEN_BRACKETS));
        lexemes.add(new Lexeme(0, 0, "sum", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "52", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "]", LexemeTypes.CLOSE_BRACKETS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "case", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, "true", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, ":", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "default", LexemeTypes.SELECTIVE_CONTROL_STRUCTURE));
        lexemes.add(new Lexeme(0, 0, ":", LexemeTypes.OTHERS));
        lexemes.add(new Lexeme(0, 0, "number", LexemeTypes.DATA_TYPE));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "=", LexemeTypes.ASSIGNMENT_OPERATORS));
        lexemes.add(new Lexeme(0, 0, "[", LexemeTypes.OPEN_BRACKETS));
        lexemes.add(new Lexeme(0, 0, "sum", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, "(", LexemeTypes.OPEN_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "52", LexemeTypes.NUMBERS));
        lexemes.add(new Lexeme(0, 0, ")", LexemeTypes.CLOSE_PARENTHESIS));
        lexemes.add(new Lexeme(0, 0, "]", LexemeTypes.CLOSE_BRACKETS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "return", LexemeTypes.FUNCTIONS));
        lexemes.add(new Lexeme(0, 0, "a", LexemeTypes.IDENTIFIERS));
        lexemes.add(new Lexeme(0, 0, ";", LexemeTypes.DELIMITERS));
        lexemes.add(new Lexeme(0, 0, "}", LexemeTypes.CLOSE_BRACES));
        
        this.log.log(Level.INFO, "Analyze: {0}", lexemes.toString());
        
        CompilationUnit cases = new CompilationUnit(null);
        TokensFlow tokensFlow = new TokensFlow(lexemes);
        
        assertEquals(2, cases.analyze(tokensFlow, tokensFlow.getCurrentToken()).getChildCount());
    }
    
}
