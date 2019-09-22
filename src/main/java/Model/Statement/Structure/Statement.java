/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Statement.Structure;

import Model.Lexeme;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Daryl Ospina
 */
public abstract class Statement implements TreeNode{
    
    /**
     * Sentencia dentro de la que se encuentra esta sentencia.
     */
    protected Statement root;

    /**
     * Hijos de la raiz de derivacion.
     */
    protected List<Statement> childs;
    
    public abstract boolean analize(Lexeme lexeme);
    public abstract Statement getStatement();
    
    public void addChild(Statement child){
        this.childs.add(child);
    }
    
    @Override
    public TreeNode getChildAt(int childIndex) {
        return this.childs.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return this.childs.size();
    }

    @Override
    public TreeNode getParent() {
        return root;
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.childs.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        if (this.childs == null) {
            return true;
        }
        return this.childs.isEmpty();
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(this.childs);
    }

    /**
     * @param childs the hijos to set
     */
    public void setHijos(List<Statement> childs) {
        this.childs = childs;
    }

}
