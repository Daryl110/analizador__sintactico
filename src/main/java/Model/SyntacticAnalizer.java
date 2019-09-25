/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Statement.CompilationUnitStatement;
import Model.Statement.ExpressionStatement;
import Model.Statement.Structure.Statement;
import Model.exceptions.SyntaxError;
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

    public Statement analyze() throws Exception {
        Statement statement = new CompilationUnitStatement();

        for (int i = 0; i < this.lexemes.size(); i++) {
            Lexeme lexeme = this.lexemes.get(i);
            if (ExpressionStatement.lexemeIs(lexeme.getType())) {
                Statement expression = new ExpressionStatement(statement);
                if (expression.analyze(lexeme)) {
                    if (expression.getStatement() != null) {
                        if (i < this.lexemes.size() - 1) {
                            lexeme = this.lexemes.get(i + 1);
                            if (ExpressionStatement.lexemeIsRelational(lexeme.getType())) {
                                if (expression.analyze(lexeme)) {
                                    boolean added = false;
                                    for (int j = i + 2; j < this.lexemes.size(); j++) {
                                        lexeme = this.lexemes.get(j);
                                        boolean bool = expression.analyze(lexeme);
                                        if (!bool || (bool && j == this.lexemes.size() - 1)) {
                                            if (expression.getStatement() != null) {
                                                statement.addChild(expression.getStatement());
                                                i = j - 1;
                                                added = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (added) {
                                        continue;
                                    }
                                }
                            }else{
                                statement.addChild(expression.getStatement());
                            }
                        } else {
                            statement.addChild(expression.getStatement());
                        }
                    } else {
                        boolean added = false;
                        for (int j = i + 1; j < this.lexemes.size(); j++) {
                            lexeme = this.lexemes.get(j);
                            boolean bool = expression.analyze(lexeme);
                            if (!bool || (bool && j == this.lexemes.size() - 1)) {
                                if (expression.getStatement() != null) {
                                    statement.addChild(expression.getStatement());
                                    i = j - 1;
                                    added = true;
                                    break;
                                }
                            }
                        }
                        if (added) {
                            continue;
                        }
                    }
                }
            }
        }

        return statement;
    }
}
