package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.StratGame;
import com.mygdx.game.personnage.Perso;

/**
 * Created by damien on 19/03/2017.
 */

public class Barre extends Actor {
    private Image pv;
    private Image pm;
    private Image pa;
    private Perso perso;
    private Label label,label1,label2;
    public Barre(final Perso perso){
        this.perso =perso;
        BitmapFont font = new BitmapFont(Gdx.files.internal("statfont.fnt"));
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor= Color.WHITE;
        pv = new Image(StratGame.textures.get("pv"));
        pv.setPosition(Gdx.graphics.getWidth()/2, 20);

        this.label = new Label(""+perso.getPvEnCours(),style);
        this.label.setPosition(pv.getX()+pv.getWidth()/2-5,pv.getY()+pv.getHeight()/4);

        pm = new Image(StratGame.textures.get("pm"));
        pm.setPosition(pv.getX() - 10 - pm.getWidth()  ,20);
        this.label1 = new Label(""+perso.getPmEnCours(),style);
        this.label1.setPosition(pm.getX()+pm.getWidth()/2-5,pm.getY()+pm.getHeight()/4);

        pa = new Image(StratGame.textures.get("pa"));
        pa.setPosition(pv.getX() +10 + pa.getWidth(), 20);
        this.label2 = new Label(""+perso.getPaEnCours(),style);
        this.label2.setPosition(pa.getX()+pa.getWidth()/2-5,pa.getY()+pa.getHeight()/4);

    }

    public void setPerso(Perso perso) {
        this.perso = perso;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.label.setText(""+perso.getPvEnCours());
        this.label1.setText(""+perso.getPmEnCours());
        this.label2.setText(""+perso.getPaEnCours());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        pa.draw(batch,parentAlpha);
        pm.draw(batch,parentAlpha);
        pv.draw(batch,parentAlpha);
        label2.draw(batch,parentAlpha);
        label1.draw(batch,parentAlpha);
        label.draw(batch,parentAlpha);
    }
}
