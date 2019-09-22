/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Statement.CompilationUnitStatement;
import Model.Statement.NumericExpressionStatement;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class SyntacticAnalizer {

    private ArrayList<Lexeme> lexemes;

    public SyntacticAnalizer(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Statement analize() {
        Statement statement = new CompilationUnitStatement();

        for (int i = 0; i < this.lexemes.size(); i++) {
            Lexeme lexeme = this.lexemes.get(i);
            if (NumericExpressionStatement.lexemesIs(lexeme.getType())) {
                Statement numericExpression = new NumericExpressionStatement(statement);
                if (numericExpression.analize(this.lexemes.get(i))) {
                    for (int j = i + 1; j < this.lexemes.size(); j++) {
                        if (numericExpression.analize(this.lexemes.get(j))) {
                            if (numericExpression.getStatement() != null) {
                                statement.addChild(numericExpression);
                                i = j;
                            }
                        }
                    }
                }
            }
        }

        return statement;
    }
}
