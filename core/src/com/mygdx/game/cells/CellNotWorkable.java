package com.mygdx.game.cells;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by damien on 17/03/2017.
 */

public class CellNotWorkable extends Cell {

    public CellNotWorkable(int y, int x, Texture texture) {
        super(y, x, texture);
    }


    @Override
    public boolean isWorkable() {
        return false;
    }

}
