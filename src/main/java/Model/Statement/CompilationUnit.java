/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.Statement.Structure.Statement;
import Model.Statement.Structure.SyntacticTypes;
import Model.TokensFlow;
import Model.exceptions.SyntaxError;

/**
 *
 * @author Daryl Ospina
 */
public class CompilationUnit extends Statement {

    public CompilationUnit(Statement root) {
        super(root);
    }

    public CompilationUnit(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.COMPILATION_UNIT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) throws SyntaxError{
        while (lexeme != null) {
            Statement blockStatement = new BlockStatement(this, tokensFlow.getPositionCurrent());
            blockStatement = blockStatement.analyze(tokensFlow, lexeme);
            if (blockStatement != null) {
                this.childs.add(blockStatement);
                lexeme = tokensFlow.getCurrentToken();
            } else {
                lexeme = tokensFlow.getCurrentToken();
                throw new SyntaxError("[Error]: "+lexeme.toString());
            }
        }
        return this;
    }
}
