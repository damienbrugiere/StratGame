package com.mygdx.game.personnage;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Action.Attack;
import com.mygdx.game.Action.PersoArret;
import com.mygdx.game.StratGame;
import com.mygdx.game.cells.CellWorkable;
import com.mygdx.game.pathfinding.PathFinding;
import com.mygdx.game.personnage.ia.Phase;
import com.mygdx.game.screen.stage.World;
import com.mygdx.game.cells.Case;

import java.util.List;

/**
 * Created by damien on 05/05/2017.
 */

public class Monstre extends Perso {
    public Monstre(int pa, int pm, int pv, int force, Case caseDuPerso, World world, Camera camera, boolean selected) {
        super(pa, pm, pv, force, caseDuPerso, world, camera, selected, new Sprite(StratGame.textures.get("enemie")), new Image(StratGame.textures.get("miniportraitenemie")));
    }

    public Monstre(int pa, int pm, int pv, int force, boolean selected) {
        super(pa, pm, pv, force, null);
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

    public void action() {
        Phase phase = new Phase();
        System.out.println("ia search");
        PathFinding pathFinding = new PathFinding();
        System.out.println("" + this.getPmEnCours());
        phase.action(this, pathFinding, World.soldats, World.persos);

    }


    public Action[] buildActions(List<CellWorkable> toCases, Attack attack) {
        if (toCases != null && !toCases.isEmpty() && toCases.size() - 1 <= pmEnCours) {

            System.out.println("mouvement + attack");
            int nb = toCases.size() - 1;
            this.pmEnCours -= nb;
            this.running = true;
            Action[] actions = new Action[toCases.size() + 2];
            int i = 0;
            for (CellWorkable caseSelected : toCases) {
                System.out.println(caseSelected.toString());
                actions[i] = Actions.sequence(Actions.moveTo(caseSelected.getX(), caseSelected.getY(), 0.5f));
                i++;
            }
            actions[toCases.size()] = new PersoArret((Case) toCases.get(toCases.size() - 1));
            actions[toCases.size() + 1] = attack;
            return actions;
        }
        System.out.println("attack simple");
        Action[] actions = new Action[1];

        actions[0] = attack;
        return actions;
    }
}
