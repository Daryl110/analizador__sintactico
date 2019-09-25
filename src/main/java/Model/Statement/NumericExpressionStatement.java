/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.SyntacticTypes;
import Model.Statement.Structure.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class NumericExpressionStatement extends Statement {

    private int state;
    private final int STATE_TOTAL;
    private int openedParenthesis;

    public static String[] typesLexemes = {
        LexemeTypes.NUMBERS,
        LexemeTypes.IDENTIFIERS,
        LexemeTypes.OPEN_PARENTHESIS,
        LexemeTypes.ARITHMETIC_OPERATORS,
        LexemeTypes.CLOSE_PARENTHESIS,
        LexemeTypes.DELIMITERS
    };

    public NumericExpressionStatement(Statement root) {
        this.root = root;
        this.state = 0;
        this.STATE_TOTAL = 4;
        this.childs = new ArrayList<>();
        this.openedParenthesis = 0;
    }

    @Override
    public boolean analyze(Lexeme lexeme) throws Exception{
        switch (this.state) {
            case 0:
                if (lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                    this.openedParenthesis++;
                    this.childs.add(lexeme);
                    return true;
                }
                if (lexeme.getType().equals(LexemeTypes.NUMBERS)
                        || lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
                    this.state = 1;
                    this.childs.add(lexeme);
                    return true;
                }
                if (this.openedParenthesis != 0) {
                    if (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                            || lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                        this.state = 1;
                        return this.analyze(lexeme);
                    }else{
                        return false;
                    }
                }
                return false;
            case 1:
                if (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {
                    this.state = 2;
                    this.childs.add(lexeme);
                    return true;
                }
                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                    this.openedParenthesis--;
                    if (this.openedParenthesis == 0) {
                        this.state = 3;
                    } else {
                        this.state = 1;
                    }
                    this.childs.add(lexeme);
                    return true;
                }
                return false;
            case 2:
                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                    this.openedParenthesis--;
                    if (!((Lexeme)this.childs.get(this.childs.size()-1).getStatement()).getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)) {
                        this.state = 3;
                    }else{
                        throw new Exception("[Error] : [ row: "+lexeme.getRow()+" - column: "+lexeme.getColumn());
                    }
                    this.childs.add(lexeme);
                    return true;
                }
                if (lexeme.getType().equals(LexemeTypes.NUMBERS)
                        || lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
                    if (this.openedParenthesis == 0) {
                        this.state = 3;
                    } else {
                        this.state = 1;
                    }
                    this.childs.add(lexeme);
                    return true;
                }
                if (lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                    this.openedParenthesis++;
                    this.state = 0;
                    this.childs.add(lexeme);
                    return true;
                }
                return false;
            case 3:
                if (lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                    this.state = 4;
                    this.childs.add(lexeme);
                    return true;
                }
                if (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                        || lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                    this.state = 1;
                    return this.analyze(lexeme);
                } else {
                    this.state = 0;
                    return this.analyze(lexeme);
                }
            default:
                return false;
        }
    }

    @Override
    public Statement getStatement() {
        if (this.state == this.STATE_TOTAL) {
            return this;
        }
        return null;
    }

    public static boolean lexemesIs(String type) {
        for (String typeLexeme : NumericExpressionStatement.typesLexemes) {
            if (type.equals(typeLexeme)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return SyntacticTypes.NUMERIC_EXPRESSION;
    }
}
