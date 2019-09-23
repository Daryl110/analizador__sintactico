/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Statement.CompilationUnitStatement;
import Model.Statement.ExpressionStatement;
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

    public Statement analyze() {
        Statement statement = new CompilationUnitStatement();

        for (int i = 0; i < this.lexemes.size(); i++) {
            Lexeme lexeme = this.lexemes.get(i);
            if (ExpressionStatement.lexemesIs(lexeme.getType())) {
                Statement expression = new ExpressionStatement(statement);
                if (expression.analyze(this.lexemes.get(i))) {
                    Statement exp = null;
                    for (int j = i + 1; j < this.lexemes.size(); j++) {
                        if (expression.analyze(this.lexemes.get(j))) {
                            exp = expression.getStatement();
                            if (exp != null) {
                                statement.addChild(exp);
                                i = j;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (exp != null) {
                        continue;
                    }
                }
            }
        }

        return statement;
    }
}
