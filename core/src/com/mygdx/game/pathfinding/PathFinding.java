package com.mygdx.game.pathfinding;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Terrain;
import com.mygdx.game.cells.Cell;
import com.mygdx.game.cells.CellWorkable;
import com.mygdx.game.personnage.Perso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 27/06/2017.
 */

public class PathFinding {
    private List<CellWorkable> ouverte;
    private List<CellWorkable> ferme;
    private List<Cell> casesDesPersos;

    public PathFinding() {
    }

    public List<CellWorkable> astar(Perso perso, CellWorkable arrive, List<Perso> persos) {
        casesDesPersos = new ArrayList<>();
        for(Perso iterator : persos){
            if(!perso.equals(iterator) && !iterator.isDead())
                casesDesPersos.add(iterator.getCase());
        }
        ouverte = new ArrayList<>();
        ferme = new ArrayList<>();
        CellWorkable current = perso.getCase();
        current.calculScore(arrive, null);
        ouverte.add(current);

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
        List<Cell> cells = new ArrayList<>();
        addInListIfDifferentNull(Terrain.getNord(current), cells);
        addInListIfDifferentNull(Terrain.getSud(current), cells);
        addInListIfDifferentNull(Terrain.getEst(current), cells);
        addInListIfDifferentNull(Terrain.getOuest(current), cells);

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
        if((cell != null) && !casesDesPersos.contains(cell)){
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

        List<CellWorkable> chemin = new ArrayList<>();
        while(current.getCellParent()!=null){
            chemin.add(0, current);
            current = current.getCellParent();
        }
        chemin.add(0,current);
        return chemin;
    }
}
