/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.Statement.Structure.SintacticTypes;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class ExpressionStatement extends Statement {

    private boolean numeric, character;
    private int state;
    private Statement numericExpression, characterExpression;

    public static ArrayList<String> typesLexemes = new ArrayList<>();

    public ExpressionStatement(Statement root) {
        this.root = root;
        this.state = 0;
        this.childs = new ArrayList<>();
        this.numericExpression = new NumericExpressionStatement(this.root);
        this.characterExpression = new CharacterExpressionStatement(this.root);
        this.numeric = false;
        this.character = false;
    }

    @Override
    public boolean analyze(Lexeme lexeme) {
        if (NumericExpressionStatement.lexemesIs(lexeme.getType()) && !this.character) {
            if (this.numericExpression.analyze(lexeme)) {
                this.characterExpression = null;
                this.numeric = true;
                if (this.numericExpression.getStatement() != null) {
                    this.state = 1;
                }
                return true;
            }
        }
        if (CharacterExpressionStatement.lexemesIs(lexeme.getType()) && !this.numeric) {
            if (this.characterExpression.analyze(lexeme)) {
                this.numericExpression = null;
                this.character = true;
                if (this.characterExpression.getStatement() != null) {
                    this.state = 2;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Statement getStatement() {
        switch (this.state) {
            case 1:
                return this.numericExpression.getStatement();
            case 2:
                return this.characterExpression.getStatement();
            default:
                return null;
        }
    }

    public static boolean lexemeIsNumeric(String type) {
        for (String typeLexeme : NumericExpressionStatement.typesLexemes) {
            ExpressionStatement.typesLexemes.add(typeLexeme);
        }
        for (String typeLexeme : ExpressionStatement.typesLexemes) {
            if (type.equals(typeLexeme)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean lexemeIsCharacter(String type){
        for (String typeLexeme : CharacterExpressionStatement.typesLexemes) {
            ExpressionStatement.typesLexemes.add(typeLexeme);
        }
        for (String typeLexeme : ExpressionStatement.typesLexemes) {
            if (type.equals(typeLexeme)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return SintacticTypes.EXPRESSION;
    }
}
