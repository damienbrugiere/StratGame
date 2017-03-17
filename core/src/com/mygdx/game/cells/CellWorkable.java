package com.mygdx.game.cells;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by damien on 17/03/2017.
 */

public class CellWorkable extends Cell{

    private int score;
    private int g,h;
    private CellWorkable parent;

    public CellWorkable(int y, int x, Texture texture) {
        super(y, x,texture);
    }

    @Override
    public boolean isWorkable() {
        return true;
    }

    public void calculScore(CellWorkable arrive, CellWorkable parent){
        this.parent = parent;
        this.h = Math.abs(this.x - arrive.x) + Math.abs(this.y - arrive.y);
        this.g = parent==null ? 0 :parent.g+1;
        this.score = h+g;
    }

    public CellWorkable getCellParent() {
        return parent;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }
}

