package com.mygdx.game.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.StratGame;
import com.mygdx.game.personnage.builder.PersoRecrutement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 06/07/2017.
 */

public class RecrutementSelect extends Stage {
    public RecrutementSelect() {
        super();
        List<PersoRecrutement> recrutements = new Json().fromJson(ArrayList.class, PersoRecrutement.class, Gdx.files.internal("recrutement.json"));
        Table scrollTable = new Table();
        for (final PersoRecrutement persoRecrutement : recrutements) {
            ImageTextButton button = new ImageTextButton(persoRecrutement.getName()+"\n prix : " + persoRecrutement.getPrix(), StratGame.styleButton);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    StratGame.information.acheter(persoRecrutement);
                }
            });
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
