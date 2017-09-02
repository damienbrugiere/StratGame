package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

/**
 * Created by damien on 05/07/2017.
 */

public class ButtonPopUpApplication extends ImageTextButton {

    public ButtonPopUpApplication(String texte, float x, float y){
        super(texte,new ImageTextButtonStyle(StratGame.styleButton2));
        this.setY(y);
        this.setX(x-35);
    }

    public void isDown(){
        this.getStyle().up = StratGame.buttonDown;
    }
    public void isUp(){
        this.getStyle().up = StratGame.buttonUp;
    }
}
