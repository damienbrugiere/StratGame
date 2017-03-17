package com.mygdx.game.personnage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Case;
import com.mygdx.game.World;
import com.mygdx.game.cells.CellWorkable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by damien on 13/03/2017.
 */

public class Perso extends Actor {

    private int pa, pm, pv, force;
    private int pvEnCours, paEnCours, pmEnCours;
    private Sprite sprite;
    private Case caseDuPerso;


    public Perso(int pa, int pm, int pv, int force, Case caseDuPerso, final World world) {
        super();
        this.pa = pa;
        this.pm = pm;
        this.pv = pv;
        this.pvEnCours = pv;
        this.paEnCours = pa;
        this.pmEnCours = pm;
        this.force = force;
        this.caseDuPerso = caseDuPerso;
        this.sprite = new Sprite(new Texture("perso.png"));
        setX(caseDuPerso.getX());
        setY(caseDuPerso.getY());
        this.addListener(new ClickListener() {
                             @Override
                             public void clicked(InputEvent event, float x, float y) {
                                 world.persoSelectionne(perso());
                             }
                         }
        );
    }


    private Perso perso() {
        return this;
    }

    public void prendDesDegats(int force) {
        pvEnCours -= force;
        if (pv <= 0) {
            pv = 0;
        }
    }

    public void seFaitSoigner(int soin) {
        pvEnCours += soin;
        if (pvEnCours > pv) {
            pvEnCours = pv;
        }
    }

    public int getCaseX() {
        return caseDuPerso.getCaseX();
    }

    public int getCaseY() {
        return caseDuPerso.getCaseY();
    }

    public void mouvement(List<CellWorkable> toCases) {
        if (toCases != null && !toCases.isEmpty()) {
            Action[] actions = new Action[toCases.size()];
            int i = 0;
            for (CellWorkable caseSelected : toCases) {
                System.out.println(caseSelected.toString());
                actions[i] = Actions.moveTo(caseSelected.getX(), caseSelected.getY(), 0.5f);
                i++;
            }
            this.addAction(Actions.sequence(actions));
            this.caseDuPerso = (Case) toCases.get(toCases.size() - 1);
        }
    }

    public Case getCase() {
        return caseDuPerso;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY());
    }
}
