/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement;

import Model.Statement.Functions.InvokeFunctionStatement;
import Model.Statement.Assignment.SimpleAssignmentStatement;
import Model.Lexeme;
import Model.LexemeTypes;
import Model.Statement.Assignment.IncrementalDecrementalOperationStatement;
import Model.Statement.Assignment.OthersAssignmentsStatement;
import Model.Statement.Functions.FunctionStatement;
import Model.Statement.IF.IfStatement;
import Model.Statement.Iterators.ForEachStatement;
import Model.Statement.Iterators.ForStatement;
import Model.Statement.Iterators.WhileStatement;
import Model.Statement.Structure.Statement;
import Model.Statement.Switch.SwitchStatement;
import Model.TokensFlow;
import Model.exceptions.SyntaxError;

/**
 *
 * @author Daryl Ospina
 */
public class BlockStatement extends Statement {

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
        this.statement = new FunctionStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new SwitchStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new IfStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new WhileStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new ForStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new ForEachStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
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
                tokensFlow.move();
                return this.statement;
            }
            if (this.positionBack != -1) {
                tokensFlow.moveTo(this.positionBack);
            } else {
                tokensFlow.backTrack();
            }
            return null;
        }
        this.statement = new OthersAssignmentsStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new IncrementalDecrementalOperationStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            lexeme = tokensFlow.getCurrentToken();

            if (lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.statement.addChild(lexeme);
                tokensFlow.move();
                return this.statement;
            }
            if (this.positionBack != -1) {
                tokensFlow.moveTo(this.positionBack);
            } else {
                tokensFlow.backTrack();
            }
            return null;
        }
        //Las demas sentencias van debajo o arriba de la sentencia de arriba de este comentario
        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        throw new SyntaxError(lexeme.toString());
    }
}
