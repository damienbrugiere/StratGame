package com.mygdx.game.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.ButtonApplication;
import com.mygdx.game.DofusLike;
import com.mygdx.game.StratGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 29/06/2017.
 */

public class SelectLevel extends Stage {


    public SelectLevel(final DofusLike stratGame){
        super();
        List<ButtonSelected> buttons = new Json().fromJson(ArrayList.class, ButtonSelected.class,   Gdx.files.internal("buttons.json"));
        BitmapFont font = new BitmapFont(Gdx.files.internal("statfont.fnt"));
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor= Color.WHITE;
        Table scrollTable= new Table();
        for(final ButtonSelected buttonSelected : buttons) {
            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = font;
            textButtonStyle.fontColor = Color.BLACK;
            ImageTextButton.ImageTextButtonStyle stylebutton = new ImageTextButton.ImageTextButtonStyle(textButtonStyle);
            stylebutton.up = new TextureRegionDrawable(new TextureRegion(new Texture("button_bleu.png")));
            ImageTextButton button = new ImageTextButton(buttonSelected.getLabel(), stylebutton);
            if(!buttonSelected.isBlock()) {
                stylebutton.up = new TextureRegionDrawable(new TextureRegion(new Texture("bouton_jaune.png")));
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        stratGame.changeMenu(buttonSelected.getMap());
                    }
                });
            }
            scrollTable.add(button).padBottom(50);
            scrollTable.row();
        }
        final ScrollPane scroller = new ScrollPane(scrollTable);

        final Table table = new Table();
        table.setFillParent(true);
        table.add(scroller).fill().expand();
        this.addActor(table);
    }

}
