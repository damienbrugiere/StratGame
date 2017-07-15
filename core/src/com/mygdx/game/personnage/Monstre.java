package com.mygdx.game.personnage;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.StratGame;
import com.mygdx.game.screen.stage.World;
import com.mygdx.game.cells.Case;

/**
 * Created by damien on 05/05/2017.
 */

public class Monstre extends Perso {
    public Monstre(int pa, int pm, int pv, int force, Case caseDuPerso, World world, Camera camera, boolean selected) {
        super(pa, pm, pv, force, caseDuPerso, world, camera, selected, new Sprite(StratGame.textures.get("enemie")), new Sprite(StratGame.textures.get("miniportraitenemie")));
    }

    public Monstre(int pa, int pm, int pv, int force, boolean selected) {
        super(pa, pm, pv, force, null, null, null, selected, new Sprite(StratGame.textures.get("enemie")), new Sprite(StratGame.textures.get("miniportraitenemie")));
    }

    @Override
    public void attaque(Perso perso) {
        if (perso instanceof Monstre) {
            return;
        }
        if (perso != null && !perso.isDead()) {
            perso.prendDesDegats(competence.action(this, perso));
        }
    }
}
