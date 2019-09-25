/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.Statement.Structure.SyntacticTypes;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class ExpressionStatement extends Statement {

    private boolean numeric, character, relational;
    private int state;
    private Statement numericExpression, characterExpression, relationalExpression;

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
    public boolean analyze(Lexeme lexeme) throws Exception {
        if (NumericExpressionStatement.lexemesIs(lexeme.getType()) && !this.character && !this.relational) {
            if (this.numericExpression.analyze(lexeme)) {
                this.characterExpression = null;
                this.numeric = true;
                if (this.numericExpression.getStatement() != null) {
                    this.state = 1;
                }
                return true;
            }
        }
        if (CharacterExpressionStatement.lexemesIs(lexeme.getType()) && !this.numeric && !this.relational) {
            if (this.characterExpression.analyze(lexeme)) {
                this.numericExpression = null;
                this.character = true;
                if (this.characterExpression.getStatement() != null) {
                    this.state = 2;
                }
                return true;
            }
        }
        if (RelationalExpressionStatement.lexemesIs(lexeme.getType()) || this.relational) {
            if (!this.relational) {
                this.relationalExpression = new RelationalExpressionStatement(this.root, 1);
                this.relational = true;
                if (this.numericExpression != null) {
                    this.numericExpression.setParent(this.relationalExpression);
                    this.relationalExpression.addChild(this.numericExpression.getStatement());
                } else if (this.characterExpression != null) {
                    this.characterExpression.setParent(this.relationalExpression);
                    this.relationalExpression.addChild(this.characterExpression.getStatement());
                } else {
                    return false;
                }
                return this.relationalExpression.analyze(lexeme);
            } else {
                if (this.relationalExpression.analyze(lexeme)) {
                    if (this.relationalExpression.getStatement() != null) {
                        this.state = 3;
                        return true;
                    }
                    return true;
                }
                return false;
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
            case 3:
                return this.relationalExpression.getStatement();
            default:
                return null;
        }
    }

    public static boolean lexemeIs(String type) {
        return NumericExpressionStatement.lexemesIs(type) || CharacterExpressionStatement.lexemesIs(type);
    }
    
    public static boolean lexemeIsRelational(String type) {
        return RelationalExpressionStatement.lexemesIs(type);
    }

    @Override
    public String toString() {
        return SyntacticTypes.EXPRESSION;
    }
}
