package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.StratGame;
import com.mygdx.game.personnage.Monstre;
import com.mygdx.game.personnage.Perso;
import com.mygdx.game.personnage.Soldat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 02/05/2017.
 */

public class Liste extends Actor{

    private List<Perso> persos;
    private Texture selected;

    public Liste(List<Soldat> soldats, List<Monstre> montres){

        selected = StratGame.textures.get("selection");
        int height = Gdx.graphics.getHeight()-72;
        int width = Gdx.graphics.getWidth()-40;
        for(Soldat perso : soldats){
            width -= 40;
            perso.updatePortraitPosition(width, height);
        }
        height = Gdx.graphics.getHeight()-120;
        width = Gdx.graphics.getWidth()-40;
        for (Monstre monstre : montres){
            width -= 40;
            monstre.updatePortraitPosition(width,height);
        }
        persos = new ArrayList<Perso>();
        persos.addAll(soldats);
        persos.addAll(montres);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(Perso perso : persos){
            if(perso.isSelected()){
                batch.draw(selected,perso.getPortraitX(),perso.getPortraitY());
            }
            perso.affichePortrait(batch);
        }
    }
}
