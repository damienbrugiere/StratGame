package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.cells.CellWorkable;

/**
 * Created by damien on 13/03/2017.
 */

public class Case extends CellWorkable {

    public Case(int y, int x, Texture texture, final World world){

        super(y,x,texture);
        this.addListener(new ClickListener(){
                             @Override
                             public void clicked(InputEvent event, float x, float y) {
                            world.caseSelectionnee(maCase());
                             }
                         }
        );
    }

    private Case maCase(){
        return this;
    }


}
