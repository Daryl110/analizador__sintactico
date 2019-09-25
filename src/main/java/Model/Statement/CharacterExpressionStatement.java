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
public class CharacterExpressionStatement extends Statement {

    private int state;
    private final int STATE_TOTAL;

    public static String[] typesLexemes = {
        LexemeTypes.CHAR
    };

    public CharacterExpressionStatement(Statement root) {
        this.root = root;
        this.state = 0;
        this.STATE_TOTAL = 1;
        this.childs = new ArrayList<>();
    }

    @Override
    public boolean analyze(Lexeme lexeme) {
        if (this.state == 0 && (lexeme.getType().equals(LexemeTypes.CHAR)
                || lexeme.getType().equals(LexemeTypes.IDENTIFIERS))) {
            this.childs.add(lexeme);
            this.state = 1;
            return true;
        }
        return false;
    }

    @Override
    public Statement getStatement() {
        if (this.state == this.STATE_TOTAL) {
            return this;
        }
        return null;
    }

    public static boolean lexemesIs(String type) {
        for (String typeLexeme : CharacterExpressionStatement.typesLexemes) {
            if (type.equals(typeLexeme)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return SyntacticTypes.CHAR_EXPRESSION;
    }
}
