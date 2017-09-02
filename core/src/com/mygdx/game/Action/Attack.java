package com.mygdx.game.Action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.game.cells.Case;
import com.mygdx.game.personnage.Monstre;
import com.mygdx.game.personnage.Perso;
import com.mygdx.game.personnage.Soldat;

/**
 * Created by damien on 20/08/2017.
 */

public class Attack extends Action {

    private Monstre monstre;
    private Soldat cible;
    public Attack(Monstre monstre,Soldat cible){
        this.monstre = monstre;
        this.cible=cible;
    }
    @Override
    public boolean act(float delta) {
        monstre.changeCells();
        monstre.setAttacking(true);
        System.out.println("Attaque en cours");
        monstre.attaque(cible);
        monstre.setAttacking(false);
        return true;
    }
}