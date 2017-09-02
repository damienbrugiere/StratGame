package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.DofusLike;
import com.mygdx.game.StratGame;
import com.mygdx.game.screen.stage.PopUp;
import com.mygdx.game.screen.stage.World;
import com.mygdx.game.hud.Baniere;
import com.mygdx.game.hud.Barre;
import com.mygdx.game.hud.Liste;
import com.mygdx.game.personnage.Perso;

/**
 * Created by damien on 01/07/2017.
 */

public class GameScreen implements Screen {
    private Stage hud;
    private PopUp popUp;
    private World world;
    private String map;
    private DofusLike dofusLike;

    public GameScreen(DofusLike dofusLike) {
        this.dofusLike = dofusLike;
        hud = new Stage();
        map = "";
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public void show() {
        world = new World( map,this);
        final Baniere baniere = new Baniere();
        hud.addActor(baniere);
        final Barre barre = new Barre(world.getPerso());
        hud.addActor(barre);
        Button attackButton = new Button(new TextureRegionDrawable(new TextureRegion(StratGame.textures.get("attack button"))));
        attackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Perso perso = world.getPerso();
                if (!perso.isRunning()) {
                    perso.setAttacking(!world.getPerso().isAttacking());
                    perso.changeCells();
                }
            }
        });
        attackButton.setPosition(Gdx.graphics.getWidth() - 100 - attackButton.getWidth(), 20);
        Button findetour = new Button(new TextureRegionDrawable(new TextureRegion(StratGame.textures.get("findetour"))));
        findetour.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                barre.setPerso(world.finDeTour(baniere));
            }
        });
        findetour.setPosition(20, 400);


        Button changementperso = new Button(new TextureRegionDrawable(new TextureRegion(StratGame.textures.get("changementperso"))));
        changementperso.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                barre.setPerso(world.changePerso());

            }
        });
        changementperso.setPosition(20, 20);

        Button zoom = new Button(new TextureRegionDrawable(new TextureRegion(StratGame.textures.get("zoom"))));
        zoom.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                world.zoom();
            }
        });
        zoom.setPosition(20, 150);

        Button dezoom = new Button(new TextureRegionDrawable(new TextureRegion(StratGame.textures.get("dezoom"))));
        dezoom.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                world.dezoom();
            }
        });
        dezoom.setPosition(20, 250);
        hud.addActor(attackButton);
        hud.addActor(new Liste(world.getSoldats(), world.getMonstres()));
        hud.addActor(findetour);
        hud.addActor(zoom);
        hud.addActor(dezoom);
        hud.addActor(changementperso);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(hud);
        multiplexer.addProcessor(world);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void setPopUp(Texture texture){
        popUp = new PopUp(texture,dofusLike);
        Gdx.input.setInputProcessor(popUp);
    }

    @Override
    public void render(float delta) {
        world.act();
        world.draw();
        hud.act();
        hud.draw();
        if(popUp!=null){
            popUp.act();
            popUp.draw();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        hud.dispose();
    }
}
