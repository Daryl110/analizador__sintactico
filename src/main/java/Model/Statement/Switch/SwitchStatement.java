/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Switch;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Expression.ExpressionStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class SwitchStatement extends Statement {

    public SwitchStatement(Statement root) {
        super(root);
    }

    public SwitchStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.SWITCH_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                && lexeme.getWord().equals("switch"))) {

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

                this.childs.add(lexeme);

                Statement expression = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                expression = expression.analyze(tokensFlow, tokensFlow.move());
                if (expression != null) {
                    this.childs.add(expression);
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACES)) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();

                            while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {

                                Statement caseStatement = new CaseStatement(this, tokensFlow.getPositionCurrent());
                                caseStatement = caseStatement.analyze(tokensFlow, lexeme);
                                if (caseStatement != null) {
                                    this.childs.add(caseStatement);
                                    lexeme = tokensFlow.move();
                                } else {
                                    if (this.positionBack != -1) {
                                        tokensFlow.moveTo(this.positionBack);
                                    } else {
                                        tokensFlow.backTrack();
                                    }
                                    return null;
                                }
                            }

                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {
                                this.childs.add(lexeme);
                                tokensFlow.move();
                                return this;
                            }
                        }
                    }
                }
            }
        }
        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        return null;
    }

}
