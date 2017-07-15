package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

/**
 * Created by damien on 05/07/2017.
 */

public class ButtonApplication extends ImageTextButton {

    public ButtonApplication(String texte, int height, int width, int x, int y){
        super(texte,new ImageTextButtonStyle(StratGame.styleButton));
        this.setBounds(x,y,width,height);
    }

    public void isDown(){
        this.getStyle().up = StratGame.buttonDown;
    }
    public void isUp(){
        this.getStyle().up = StratGame.buttonUp;
    }
}
