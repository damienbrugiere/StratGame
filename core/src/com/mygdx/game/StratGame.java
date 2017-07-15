package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.information.Information;
import com.mygdx.game.information.InformationBuilder;

import java.util.HashMap;
import java.util.Map;

public class StratGame extends ApplicationAdapter {
    public static Map<String, Texture> textures;
    public static ImageTextButton.ImageTextButtonStyle styleButton;
    public static ImageTextButton.ImageTextButtonStyle styleButton1;
    public static Information information;
    private DofusLike dofusLike;
    public static Drawable buttonUp,buttonDown;

    @Override
    public void create() {
        information = new Information(new Json().fromJson( InformationBuilder.class,   Gdx.files.internal("info.json")));
        BitmapFont font = new BitmapFont(Gdx.files.internal("statfont.fnt"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.BLACK;

        TextButton.TextButtonStyle textButtonStyle1 = new TextButton.TextButtonStyle();
        textButtonStyle1.font = font;
        textButtonStyle1.fontColor = Color.WHITE;
        this.styleButton1 = new ImageTextButton.ImageTextButtonStyle(textButtonStyle1);
        this.styleButton1.up = new TextureRegionDrawable(new TextureRegion(new Texture("po.png")));
        this.styleButton = new ImageTextButton.ImageTextButtonStyle(textButtonStyle);
        this.styleButton.up = new TextureRegionDrawable(new TextureRegion(new Texture("bouton_jaune.png")));

        buttonUp = new TextureRegionDrawable(new TextureRegion(new Texture("bouton_jaune.png")));
        buttonDown = new TextureRegionDrawable(new TextureRegion(new Texture("button_bleu.png")));
        textures = new HashMap<String, Texture>();
        textures.put("casenotworkable", new Texture("caseNotWorkable.png"));
        textures.put("caseworkable", new Texture("caseTexture.png"));
        textures.put("perso", new Texture("perso.png"));
        textures.put("attack button", new Texture("button_attaque.png"));
        textures.put("tour", new Texture("image.png"));
        textures.put("pv", new Texture("pv.png"));
        textures.put("pm", new Texture("pm.png"));
        textures.put("pa", new Texture("pa.png"));
        textures.put("caseAttaque", new Texture("caseAttaque.png"));
        textures.put("miniportrait", new Texture("miniportrait.png"));
        textures.put("selection", new Texture("selection.png"));
        textures.put("findetour", new Texture("findetour.png"));
        textures.put("zoom", new Texture("zoom.png"));
        textures.put("dezoom", new Texture("dezoom.png"));
        textures.put("changementperso", new Texture("changementperso.png"));
        textures.put("miniportraitenemie", new Texture("miniportraitenemi.png"));
        textures.put("enemie", new Texture("enemi.png"));
        textures.put("miniportraitdead", new Texture("miniportraitdead.png"));
        textures.put("po", new Texture("po.png"));
        dofusLike = new DofusLike();
        dofusLike.create();


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        dofusLike.render();

    }

    public void changeMenu(String map) {

    }

    @Override
    public void dispose() {
        dofusLike.dispose();
    }

    @Override
    public void resize(int width, int height) {
        dofusLike.resize(width, height);
    }
}
