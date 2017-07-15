package com.mygdx.game.personnage.builder;

import java.io.Serializable;

/**
 * Created by damien on 06/07/2017.
 */

public class PersoRecrutement extends PersoBuilder implements Serializable {
    private String name;
    private int prix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
