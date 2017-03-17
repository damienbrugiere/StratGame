package com.mygdx.game.cells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.World;

/**
 * Created by damien on 17/03/2017.
 */

public abstract class Cell extends Actor {
    protected int x;
    protected int y;
    protected Texture textute;

    protected Cell(int y, int x, Texture texture) {
        this.setY(y * texture.getHeight());
        this.setX(x * texture.getWidth());
        this.setHeight(texture.getHeight());
        this.setWidth(texture.getWidth());
        this.setBounds(x * texture.getWidth(), y * texture.getHeight(), getWidth(), getHeight());
        this.setTouchable(Touchable.enabled);
        this.textute = texture;
        this.x = x;
        this.y = y;
    }


    public abstract boolean isWorkable();

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().isAssignableFrom(getClass())) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textute, getX(), getY(), getWidth(), getHeight());
    }

    public int getCaseX() {
        return x;
    }

    public int getCaseY() {
        return y;
    }

    @Override
    public String toString() {
        return "case x :"+x+" , y: "+y;
    }
}

