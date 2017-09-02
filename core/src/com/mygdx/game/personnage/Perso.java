package com.mygdx.game.personnage;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Action.Attack;
import com.mygdx.game.Action.PersoArret;
import com.mygdx.game.cells.Case;
import com.mygdx.game.StratGame;
import com.mygdx.game.screen.stage.World;
import com.mygdx.game.cells.CellWorkable;
import com.mygdx.game.competences.Competence;

import java.util.List;

/**
 * Created by damien on 13/03/2017.
 */

public abstract class Perso extends Actor {

    protected int pa, pm, pv, force;
    protected int pvEnCours, paEnCours, pmEnCours;
    private Sprite sprite;
    private Image portrait;
    private Case caseDuPerso;
    private Camera camera;
    protected boolean running, attacking, selected;

    protected Competence competence;

    protected Perso(int pa, int pm, int pv, int force, Case caseDuPerso, final World world, Camera camera, boolean selected, Sprite sprite, Image miniportrait) {
        super();
        this.selected = selected;
        this.camera = camera;
        this.pa = pa;
        this.pm = pm;
        this.pv = pv;
        this.pvEnCours = pv;
        this.paEnCours = pa;
        this.pmEnCours = pm;
        this.force = force;
        this.caseDuPerso = caseDuPerso;
        this.sprite = sprite;
        this.running = false;
        setX(caseDuPerso.getX());
        setY(caseDuPerso.getY());
        competence = new Competence(4, 1, 2);
        this.portrait = miniportrait;
        this.addListener(new ClickListener() {
                             @Override
                             public void clicked(InputEvent event, float x, float y) {
                                 world.persoSelectionne(perso());
                             }
                         }
        );
    }

    protected Perso(int pa, int pm, int pv, int force, Image miniPortrait) {
        super();
        this.pa = pa;
        this.pm = pm;
        this.pv = pv;
        this.pvEnCours = pv;
        this.paEnCours = pa;
        this.pmEnCours = pm;
        this.force = force;
        this.running = false;
        this.portrait = miniPortrait;
        this.competence = new Competence(4, 1, 2);
        this.sprite = new Sprite(StratGame.textures.get("perso"));
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

    public boolean usePa(int paUsed) {
        int buffer = paEnCours - paUsed;
        if (buffer <= 0) {
            return false;
        }
        paEnCours = buffer;
        return true;
    }

    public void changeCells() {
        competence.changeCells(this);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void seFaitSoigner(int soin) {
        pvEnCours += soin;
        if (pvEnCours > pv) {
            pvEnCours = pv;
        }
    }

    public void updatePortraitPosition(int x, int y) {
        this.portrait.setX(x);
        this.portrait.setY(y);
    }

    public void affichePortrait(Batch batch, float parentAlpha) {
        this.portrait.draw(batch, parentAlpha);
        if (isDead())
            batch.draw(StratGame.textures.get("miniportraitdead"), portrait.getX(), portrait.getY());
    }

    public void placement(final World world, Case caseDuPerso) {
        setCaseDuPerso(caseDuPerso);
        this.camera = world.getCamera();
        this.running = false;
        this.addListener(new ClickListener() {
                             @Override
                             public void clicked(InputEvent event, float x, float y) {
                                 world.persoSelectionne(perso());
                             }
                         }
        );
    }

    public boolean isDead() {
        return pvEnCours <= 0;
    }

    public float getPortraitX() {
        return portrait.getX() - 5;
    }

    public float getPortraitY() {
        return portrait.getY() - 5;
    }

    public int getCaseX() {
        return caseDuPerso.getCaseX();
    }

    public int getCaseY() {
        return caseDuPerso.getCaseY();
    }

    public void mouvement(List<CellWorkable> toCases) {
        if (toCases != null && !toCases.isEmpty() && toCases.size() - 1 <= pmEnCours) {
            int nb = toCases.size() - 1;
            this.pmEnCours -= nb;
            this.running = true;
            Action[] actions = new Action[toCases.size() + 1];
            int i = 0;
            for (CellWorkable caseSelected : toCases) {
                System.out.println(caseSelected.toString());
                actions[i] = Actions.sequence(Actions.moveTo(caseSelected.getX(), caseSelected.getY(), 0.5f));
                i++;
            }
            actions[toCases.size()] = new PersoArret((Case) toCases.get(toCases.size() - 1));
            this.addAction(Actions.sequence(actions));
            this.caseDuPerso = (Case) toCases.get(toCases.size() - 1);
        }
    }

    public Action[] buildActions(List<CellWorkable> toCases, Attack attack) {
        if (toCases != null && !toCases.isEmpty() && toCases.size() - 1 <= pmEnCours) {
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
        Action[] actions = new Action[1];
        actions[0] = attack;
        return actions;
    }

    public Case getCase() {
        return caseDuPerso;
    }

    public void setCaseDuPerso(Case caseDuPerso) {
        this.caseDuPerso = caseDuPerso;
        setX(caseDuPerso.getX());
        setY(caseDuPerso.getY());
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public int getPaEnCours() {
        return paEnCours;
    }

    public int getPv() {
        return pv;
    }

    public int getPvEnCours() {
        return pvEnCours;
    }

    public int getPmEnCours() {
        return pmEnCours;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public int getForce() {
        return force;
    }

    public void setAttacking(boolean attacking) {
        System.out.println("changement de statut");
        this.attacking = attacking;
    }


    public void initPmEtPa() {
        this.pmEnCours = pm;
        this.paEnCours = pa;
    }

    public void initCasesPossibles() {
        competence.changeCells(this);
    }

    public abstract void attaque(Perso perso);

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!isDead()) {
            if (selected) {
                this.camera.position.set(getX(), getY(), 0);
            }
            batch.draw(sprite, getX(), getY());
            if (attacking) {
                competence.affichePossibilite(batch, parentAlpha);
            }
        }

    }

    public void afficheCompetence(Batch batch, float parentAlpha) {
        batch.begin();
        competence.draw(batch, parentAlpha);
        batch.end();
    }

    public Image getPortrait() {
        return this.portrait;
    }
}
