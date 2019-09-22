/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Statement.Structure.Statement;

/**
 *
 * @author Daryl Ospina
 */
public class Lexeme extends Statement{
    
    private int row;
    private int column;
    private String word;
    private String type;

    public Lexeme(int row, int column, String word, String type) {
        this.row = row;
        this.column = column;
        this.word = word;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumns(int column) {
        this.column = column;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{ token: "+this.word+", tipo: "+this.type+", posicion: { row: "+this.row+", column: "+this.column+"}}"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean analize(Lexeme lexeme) {
        return lexeme.getType().equals(this.type) && lexeme.word.equals(this.word);
    }
    
}
