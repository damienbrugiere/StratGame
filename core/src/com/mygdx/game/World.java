package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.cells.Cell;
import com.mygdx.game.cells.CellNotWorkable;
import com.mygdx.game.cells.CellWorkable;
import com.mygdx.game.personnage.Perso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 13/03/2017.
 */

public class World {

    private Cell[][] terrain;
    private int longueur, largeur;

    private List<CellWorkable> ouverte;
    private List<CellWorkable> ferme;
    IsometricTiledMapRenderer

    private Perso perso;


    public World(Stage stage, int longueur, int largeur) {
        Texture texture = new Texture("caseTexture.png");
        this.largeur = largeur;
        this.longueur = longueur;
        terrain = new Cell[largeur][longueur];
        for (int y = 0; y < largeur; y++) {
            for (int x = 0; x < longueur; x++) {
                Case myCase = new Case(y, x,  texture,this);
                terrain[y][x] = myCase;
                stage.addActor(myCase);
            }
        }
        perso = new Perso(0,0,0,0,(Case)terrain[0][0],this);
        stage.addActor(perso);
    }

    public World(Stage stage, int[][] croquis) {
        Texture texture1 = new Texture("caseTexture.png");
        Texture texture2 = new Texture("caseNotWorkable.png");
        this.largeur = 0;
        this.longueur = 0;
        this.largeur = croquis.length;
        this.longueur = croquis[0].length;
        terrain = new Cell[largeur][longueur];
        if (longueur != 0 && largeur != 0) {
            for (int y = 0; y < largeur; y++) {
                for (int x = 0; x < longueur; x++) {
                    Cell myCase = null;
                    if (croquis[y][x] == 0) {
                        myCase = new Case(y, x,  texture1,this);
                    } else {
                        myCase = new CellNotWorkable(y, x,texture2);
                    }
                    terrain[y][x] = myCase;
                    stage.addActor(myCase);

                }
            }
        }
        perso = new Perso(0,0,0,0,(Case)terrain[0][0],this);
        stage.addActor(perso);

    }




    public void caseSelectionnee(Case c){
        System.out.println("je touche la case x :"+c.getCaseX()+",y :"+c.getCaseY());
        //a*
        perso.mouvement(astar(perso.getCase(),c));
    }

    public void persoSelectionne(Perso perso){
        System.out.println("je touche le perso x :"+perso.getCaseX()+",y :"+perso.getCaseY());
    }

    public Cell[][] getTerrain() {
        return terrain;
    }

    // get parent
    Cell getNord(Cell c) {
        if(c.getCaseY()+1> largeur-1){
            return null;
        }
        return terrain[c.getCaseY() + 1][c.getCaseX()];
    }

    Cell getSud(Cell c) {
        if(c.getCaseY()-1<0){
            return null;
        }
        return terrain[c.getCaseY() - 1][c.getCaseX()];
    }

    Cell getEst(Cell c) {
        if(c.getCaseX()+1>longueur-1){
            return null;
        }
        return terrain[c.getCaseY()][c.getCaseX() + 1];
    }

    Cell getOuest(Cell c) {
        if(c.getCaseX()-1<0){
            return null;
        }
        return terrain[c.getCaseY()][c.getCaseX() - 1];
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public List<CellWorkable> astar(CellWorkable depart, CellWorkable arrive) {
        ouverte = new ArrayList<CellWorkable>();
        ferme = new ArrayList<CellWorkable>();

        CellWorkable current = depart;
        current.calculScore(arrive, null);
        ouverte.add(depart);

        while(!ouverte.isEmpty() && current.getH()!=0){
            current = donneLaCellAvecLeMeilleureScore();
            remplirListeOuverteAvecTouteLesPossibilites(current, arrive);
            addFerme(current);
        }
        if(current.getH()!=0){
            return null;
        }

        return this.construitChemin(current);

    }

    private void remplirListeOuverteAvecTouteLesPossibilites(CellWorkable current, CellWorkable arrive) {
        List<Cell> cells = new ArrayList<Cell>();
        addInListIfDifferentNull(getNord(current), cells);
        addInListIfDifferentNull(getSud(current), cells);
        addInListIfDifferentNull(getEst(current), cells);
        addInListIfDifferentNull(getOuest(current), cells);

        for (Cell cell : cells) {
            addInListeOuverte(cell, current, arrive);
        }
    }

    private void addInListeOuverte(Cell cell, CellWorkable parent, CellWorkable arrive) {
        if (cell.isWorkable()) {
            CellWorkable current = (CellWorkable) cell;
            if (!this.ouverte.contains(current) && !this.ferme.contains(current)) {
                current.calculScore(arrive, parent);
                this.ouverte.add(current);
            }
        }

    }

    private void addFerme(CellWorkable current) {
        this.ferme.add(current);
    }

    private void addInListIfDifferentNull(Cell cell , List<Cell> cells) {
        if(cell!=null){
            cells.add(cell);
        }
    }

    private CellWorkable donneLaCellAvecLeMeilleureScore(){
        CellWorkable cellSelected = ouverte.get(0);
        for (CellWorkable cell : ouverte) {
            if (cellSelected.getScore() > cell.getScore()) {
                cellSelected = cell;
            }
        }
        this.ouverte.remove(cellSelected);
        return cellSelected;
    }

    private List<CellWorkable> construitChemin(CellWorkable current){

        List<CellWorkable> chemin = new ArrayList<CellWorkable>();
        while(current.getCellParent()!=null){
            chemin.add(0, current);
            current = current.getCellParent();
        }
        chemin.add(0,current);
        return chemin;
    }
}
