package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.StratGame;

/**
 * Created by damien on 18/03/2017.
 */

public class Baniere extends Actor {
    private Texture texture;
    private int number;
    private Label label;
    public Baniere(){
        texture = StratGame.textures.get("tour");
        this.number = 1;
        BitmapFont font = new BitmapFont(Gdx.files.internal("myFont.fnt"));
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor= Color.WHITE;
        this.label = new Label("TOUR "+number,style);
        this.setX(-texture.getWidth());
        this.setY(Gdx.graphics.getHeight()-100);
        this.addAction(Actions.moveTo(0,getY(),0.5f));
        this.setHeight(texture.getHeight());
        this.setWidth(texture.getWidth());
        label.setPosition(getX()+ (this.getWidth()-label.getWidth()),getY()+(this.getHeight()-label.getHeight()));
        System.out.println("width : "+getWidth()+" height : " +getHeight());
    }

    public void changementTour(){
        number++;
        label.setText("TOUR " + number);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = getX()+ (this.getWidth()/1.5f-label.getWidth());
        float y = getY()+(this.getHeight()/1.5f-label.getHeight());
        label.setPosition(x,y);
        batch.draw(texture,getX(),getY());
        label.draw(batch,parentAlpha);
    }
}
