package com.mygdx.game.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.StratGame;
import com.mygdx.game.Terrain;
import com.mygdx.game.hud.Baniere;
import com.mygdx.game.pathfinding.PathFinding;
import com.mygdx.game.personnage.Monstre;
import com.mygdx.game.personnage.Perso;
import com.mygdx.game.personnage.Soldat;
import com.mygdx.game.personnage.builder.PersoBuilder;
import com.mygdx.game.personnage.builder.PersoFactory;
import com.mygdx.game.screen.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by damien on 13/03/2017.
 */

public class World extends Stage {

    private PathFinding pathFinding;
    private int iteratorSelected;
    public static List<Soldat> soldats;
    public static List<Monstre> monstres;
    public static List<Perso> persos;
    private Perso perso;
    private Terrain terrain;
    private boolean actif;
    private boolean end;
    private GameScreen gameScreen;

    public World(String map, GameScreen gameScreen) {
        end = false;
        this.gameScreen = gameScreen;
        actif = false;
        this.iteratorSelected = 0;
        persos = new ArrayList<>();
        monstres = new ArrayList<>();
        soldats = StratGame.information.getTeam();
        this.pathFinding = new PathFinding();
        buildWorld(map);

    }

    private void buildWorld(String map) {
        actif = true;
        terrain = new Terrain(this, map);
        List<PersoBuilder> persoBuilders = new Json().fromJson(ArrayList.class, PersoBuilder.class, Gdx.files.internal("map/" + map + "/persos.json"));
        for (Perso iterator : PersoFactory.build(persoBuilders, this, terrain)) {
            if (iterator instanceof Monstre) {
                monstres.add((Monstre) iterator);
            }
            this.addActor(iterator);
        }
        soldats = terrain.placementTeam(monstres, this);
        persos = new ArrayList<>();
        persos.addAll(monstres);
        persos.addAll(soldats);
        changePerso();
        this.getCamera().position.set(perso.getX(), perso.getY(), 0);

    }

    public void caseSelectionnee(com.mygdx.game.cells.Case c) {
        System.out.println("je touche la case x :" + c.getCaseX() + ",y :" + c.getCaseY());
        //a*
        if (!perso.isRunning() && !perso.isAttacking()) {
            perso.mouvement(pathFinding.astar(perso, c, persos));
        }
        if (perso.isAttacking()) {
            Perso cible = null;
            for (Perso myPerso : persos) {
                if (myPerso.getCase().equals(c)) {
                    cible = myPerso;
                    break;
                }
            }
            perso.attaque(cible);
        }

    }

    public void persoSelectionne(Perso perso) {
        System.out.println("je touche le perso x :" + perso.getCaseX() + ",y :" + perso.getCaseY());
    }

    public Perso finDeTour(Baniere baniere) {
        this.perso.setSelected(false);
        this.iteratorSelected = 0;
        List<Perso> buffers = new ArrayList<>();
        for (Perso perso : soldats) {
            if (!perso.isDead()) {
                buffers.add(perso);
            }
        }
        this.perso = buffers.get(this.iteratorSelected);
        this.perso.setSelected(true);
        baniere.changementTour();
        for (Perso perso : persos) {
            perso.setAttacking(false);
            perso.initPmEtPa();
        }
        for (Monstre monstre : monstres) {
            if (!monstre.isDead()) {
                monstre.action();
            }
        }
        return perso;
    }

    @Override
    public void act() {
        super.act();
        if (!actif || end) {
            return;
        }
        boolean loose = true;
        for (Soldat soldat : soldats) {
            if (!soldat.isDead()) {
                loose = false;
                break;
            }
        }
        if (loose) {
            end = true;
            gameScreen.setPopUp(StratGame.textures.get("defeat"));
            System.out.println("It's loose");
        }
        boolean win = true;
        for (Monstre monstre : monstres) {
            if (!monstre.isDead()) {
                win = false;
                break;
            }
        }
        if (win) {
            end = true;
            gameScreen.setPopUp(StratGame.textures.get("victory"));
            System.out.println("It's win");
        }
    }

    public Perso changePerso() {
        List<Perso> buffers = new ArrayList<>();
        for (Perso perso : soldats) {
            if (!perso.isDead()) {
                buffers.add(perso);
            }
        }
        if (perso != null && !buffers.isEmpty()) {
            this.perso.setAttacking(false);
            if (this.iteratorSelected == buffers.size() - 1) {
                this.iteratorSelected = 0;
            } else {
                this.iteratorSelected++;
            }
            this.perso.setSelected(false);
        }
        this.perso = buffers.get(this.iteratorSelected);
        this.perso.setSelected(true);
        return perso;
    }

    @Override
    public void draw() {
        super.draw();
        for (Perso perso : persos) {
            perso.afficheCompetence(getBatch(), 1f);
        }
    }

    public void zoom() {
        ((OrthographicCamera) getCamera()).zoom -= 0.05;
    }

    public void dezoom() {
        ((OrthographicCamera) getCamera()).zoom += 0.05;
    }

    public List<Soldat> getSoldats() {
        return soldats;
    }

    public List<Monstre> getMonstres() {
        return monstres;
    }

    public Perso getPerso() {
        return perso;
    }

    public List<Perso> getPersos() {
        return persos;
    }

}
