package com.mygdx.game.personnage.builder;

import com.mygdx.game.Terrain;
import com.mygdx.game.personnage.Monstre;
import com.mygdx.game.personnage.Perso;
import com.mygdx.game.personnage.Soldat;
import com.mygdx.game.screen.stage.World;

import java.io.Serializable;


/**
 * Created by damien on 26/06/2017.
 */

public class PersoBuilder implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private int pa, pm, pv, force, x, y;

    public PersoBuilder() {
    }

    public Perso build(World world, Terrain terrain) {
        switch (type) {
            case "soldat":
                return new Soldat(pa, pm, pv, force, terrain.getCase(x, y), world, world.getCamera(), false);

            case "monstre":
                return new Monstre(pa, pm, pv, force, terrain.getCase(x, y), world, world.getCamera(), false);
            default:
                break;
        }
        throw new IllegalArgumentException("Pas le bon type dans le json");
    }

    public Perso build() {
        switch (type) {
            case "soldat":
                return new Soldat(pa, pm, pv, force, false);

            case "monstre":
                return new Monstre(pa, pm, pv, force, false);
            default:
                break;
        }
        throw new IllegalArgumentException("Pas le bon type dans le json");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
