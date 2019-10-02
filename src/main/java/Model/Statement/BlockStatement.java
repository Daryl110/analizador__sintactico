/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Structure.Statement;
import Model.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class BlockStatement extends Statement{
    
    private Statement statement;
    
    public BlockStatement(Statement root) {
        super(root);
    }
    
    public BlockStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }
    
    @Override
    public String toString() {
        return this.statement.toString();
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        this.statement = new SimpleAssignmentStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new InvokeFunctionStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            lexeme = tokensFlow.getCurrentToken();
            
            if (lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.statement.addChild(lexeme);
                return this.statement;
            }
            return null;
        }
        //Las demas sentencias van debajo o arriba de la sentencia de arriba de este comentario
        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        return null;
    }
}