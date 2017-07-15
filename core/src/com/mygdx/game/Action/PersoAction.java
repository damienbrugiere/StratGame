package com.mygdx.game.Action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.game.cells.Case;
import com.mygdx.game.personnage.Perso;

/**
 * Created by damien on 24/04/2017.
 */

public class PersoAction extends Action {

    private Case caseSelected;

    public PersoAction(Case caseSelected){
        this.caseSelected = caseSelected;
    }

    @Override
    public boolean act(float delta) {
        ((Perso) this.actor).setRunning(true);
        ((Perso) this.actor).setCaseDuPerso(caseSelected);
        return true;
    }
}
