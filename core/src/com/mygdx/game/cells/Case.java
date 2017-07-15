package com.mygdx.game.cells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.screen.stage.World;

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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
