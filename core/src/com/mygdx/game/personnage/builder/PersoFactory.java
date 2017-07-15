package com.mygdx.game.personnage.builder;

import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.Terrain;
import com.mygdx.game.screen.stage.World;
import com.mygdx.game.personnage.Perso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 26/06/2017.
 */

public final class PersoFactory {

    public static List<Perso> build(List<PersoBuilder> persoBuilders, World world, Terrain terrain){
        List<Perso> persos = new ArrayList<>();
        for(PersoBuilder perso : persoBuilders){
            persos.add(perso.build(world,terrain));
        }
        return persos;
    }

}
