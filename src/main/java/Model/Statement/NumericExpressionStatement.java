/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.SintacticTypes;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class NumericExpressionStatement extends Statement{

    private int state;
    private final int STATE_TOTAL;
    private int openedParenthesis;
    
    public static String[] typesLexemes = {
        LexemeTypes.NUMBERS,
        LexemeTypes.OPEN_PARENTHESIS,
        LexemeTypes.ARITHMETIC_OPERATORS,
        LexemeTypes.CLOSE_PARENTHESIS
    };

    public NumericExpressionStatement(Statement root) {
        this.root = root;
        this.state = 0;
        this.STATE_TOTAL = 3;
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
    }

    @Override
    public boolean analyze(Lexeme lexeme) {
        switch (this.state) {
            case 0:
                if (lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                    this.childs.add(lexeme);
                    this.state = 0;
                    this.openedParenthesis++;
                    return true;
                }
                if (lexeme.getType().equals(LexemeTypes.NUMBERS)) {
                    this.childs.add(lexeme);
                    this.state = 1;
                    return true;
                }
                if (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {
                    this.childs.add(lexeme);
                    this.state = 2;
                    return true;
                }
                this.state = 2;
                return this.analyze(lexeme);
            case 1:
                if (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {
                    this.childs.add(lexeme);
                    return true;
                } else {
                    this.state = 0;
                    return this.analyze(lexeme);
                }
            case 2:
                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                    this.childs.add(lexeme);
                    this.state = 2;
                    this.openedParenthesis--;
                    if (this.openedParenthesis == 0 && this.childs.size() > 2) {
                        this.state = 3;
                    }
                    return true;
                }
                if (this.openedParenthesis == 0) {
                    if (lexeme.getType().equals(LexemeTypes.NUMBERS)) {
                        this.childs.add(lexeme);
                        this.state = 3;
                        return true;
                    }
                }
                this.state = 1;
                return this.analyze(lexeme);
            default:
                this.state = 0;
                return this.analyze(lexeme);
        }
    }

    @Override
    public Statement getStatement(){
        if (this.openedParenthesis == 0 && this.state == 1) {
            this.state = 3;
        }
        if (this.state == this.STATE_TOTAL) {
            return this;
        }
        return null;
    }
    
    public static boolean lexemesIs(String type){
        for (String typeLexeme : NumericExpressionStatement.typesLexemes) {
            if (type.equals(typeLexeme)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return SintacticTypes.NUMERIC_EXPRESSION;
    }
}
