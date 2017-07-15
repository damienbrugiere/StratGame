package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.HomeScreen;
import com.mygdx.game.screen.RecrutementScreen;

/**
 * Created by damien on 02/07/2017.
 */

public class DofusLike extends Game{
    private RecrutementScreen recrutementScreen;
    private GameScreen gameScreen;
    private HomeScreen homeScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen();
        homeScreen = new HomeScreen(this);
        recrutementScreen = new RecrutementScreen(this);
        setScreen(homeScreen);
    }

    public void changeMenu(String map) {
        gameScreen.setMap(map);
        setScreen(gameScreen);
    }

    public void changeScreen(String name){
        switch (name){
            case "home" :
                setScreen(homeScreen);
                break;
            case "recrutement":
                setScreen(recrutementScreen);
                break;
            default:
                setScreen(homeScreen);
                break;
        }
    }
}
