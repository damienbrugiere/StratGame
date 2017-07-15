package com.mygdx.game.competences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.StratGame;
import com.mygdx.game.Terrain;
import com.mygdx.game.cells.Cell;
import com.mygdx.game.personnage.Perso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 30/04/2017.
 */

public class Competence {

    protected int nbCase;
    protected int degat;
    protected int pa;

    protected Label label;
    protected List<Cell> cells;
    public Competence(int nbCase,int degat,int pa){
        this.nbCase=nbCase;
        this.degat=degat;
        this.pa = pa;
        BitmapFont font = new BitmapFont(Gdx.files.internal("statfont.fnt"));
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor= Color.WHITE;
        label=new Label("",style);
        cells = new ArrayList<Cell>();
    }


    public void changeCells(Perso perso){
        cells.clear();
        Cell bufferCase = perso.getCase();
        for(int i = 0; i<nbCase;i++){
            bufferCase = Terrain.getNord(bufferCase);
            if(bufferCase != null && bufferCase.isWorkable()) {
                cells.add(bufferCase);
            }else{
                break;
            }
        }
        bufferCase = perso.getCase();
        for(int i = 0; i<nbCase;i++){
            bufferCase = Terrain.getSud(bufferCase);
            if(bufferCase != null  && bufferCase.isWorkable()) {
                cells.add(bufferCase);
            }else{
                break;
            }
        }
        bufferCase = perso.getCase();
        for(int i = 0; i<nbCase;i++){
            bufferCase = Terrain.getEst(bufferCase);
            if(bufferCase != null && bufferCase.isWorkable()) {
                cells.add(bufferCase);
            }else{
                break;
            }
        }
        bufferCase = perso.getCase();
        for(int i = 0; i<nbCase;i++){
            bufferCase = Terrain.getOuest(bufferCase);
            if(bufferCase != null  && bufferCase.isWorkable()) {
                cells.add(bufferCase);
            }else{
                break;
            }
        }
    }

    public int action(Perso perso,Perso cible){
        if(!cells.contains(cible.getCase())){
            System.out.println("et non");
            return 0;
        }
        if(perso.usePa(pa)){
            System.out.println("x : "+cible.getX()+cible.getWidth()/2+", y: "+cible.getY()+cible.getHeight()/2);
            Label.LabelStyle style = new Label.LabelStyle();
            BitmapFont font = new BitmapFont(Gdx.files.internal("statfont.fnt"));
            style.font = font;
            style.fontColor= Color.WHITE;
            int attaque = perso.getForce()+degat;
            label= new Label(""+attaque,style);
            label.setStyle(style);
            label.setPosition(cible.getX()+70/2,cible.getY()+96/2);
            label.addAction(Actions.sequence(Actions.moveTo(cible.getX()+70/2,cible.getY()+140,10f),Actions.fadeOut(5)));
            return attaque;
        }
        return 0;
    }
    public void affichePossibilite(Batch batch, float parentAlpha) {
        for(Cell cell : cells){
            batch.draw(StratGame.textures.get("caseAttaque"),cell.getX(),cell.getY());
        }

    }

    public void draw(Batch batch, float parentAlpha){
        this.label.act(parentAlpha);
        this.label.draw(batch, parentAlpha);
    }


}
