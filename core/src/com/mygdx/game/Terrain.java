package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.cells.Case;
import com.mygdx.game.cells.Cell;
import com.mygdx.game.cells.CellNotWorkable;

import java.io.Serializable;

/**
 * Created by damien on 27/06/2017.
 */

public class Terrain implements Serializable{

    private static Cell[][] terrain;
    private static int longueur, largeur;

    public Terrain(com.mygdx.game.screen.stage.World world, String map){
        Integer[][] pattern = new Json().fromJson(Integer[][].class, Gdx.files.internal("map/"+map+"/map.json"));
        this.largeur = 0;
        this.longueur = 0;
        this.largeur = pattern.length;
        this.longueur = pattern[0].length;
        terrain = new Cell[largeur][longueur];
        if (longueur != 0 && largeur != 0) {
            for (int y = 0; y < largeur; y++) {
                for (int x = 0; x < longueur; x++) {
                    Cell myCase = null;
                    if (pattern[y][x] == 0) {
                        myCase = new Case(y, x, StratGame.textures.get("caseworkable"),world);
                    } else {
                        myCase = new CellNotWorkable(y, x,StratGame.textures.get("casenotworkable"));
                    }
                    terrain[y][x] = myCase;
                    world.addActor(myCase);
                }
            }
        }
    }

    // get parent
    public static Cell getNord(Cell c) {
        if(c.getCaseY()+1> largeur-1){
            return null;
        }
        return terrain[c.getCaseY() + 1][c.getCaseX()];
    }

    public static Cell getSud(Cell c) {
        if(c.getCaseY()-1<0){
            return null;
        }
        return terrain[c.getCaseY() - 1][c.getCaseX()];
    }

    public static Cell getEst(Cell c) {
        if(c.getCaseX()+1>longueur-1){
            return null;
        }
        return terrain[c.getCaseY()][c.getCaseX() + 1];
    }

    public static Cell getOuest(Cell c) {
        if(c.getCaseX()-1<0){
            return null;
        }
        return terrain[c.getCaseY()][c.getCaseX() - 1];
    }

    public Case getCase(int x,int y){
        if(terrain[y][x] instanceof Case){
            return (Case) terrain[y][x];
        }
        throw new IllegalArgumentException("ce n'est pas une case possible pour se possitionner");
    }

}
