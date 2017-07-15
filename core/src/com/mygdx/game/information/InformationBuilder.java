package com.mygdx.game.information;

import com.mygdx.game.personnage.Soldat;
import com.mygdx.game.personnage.builder.PersoBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by damien on 06/07/2017.
 */

public class InformationBuilder implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<PersoBuilder> team;
    private int po;


    public List<PersoBuilder> getTeam() {
        return team;
    }

    public void setTeam(List<PersoBuilder> team) {
        this.team = team;
    }

    public int getPo() {
        return po;
    }

    public void setPo(int po) {
        this.po = po;
    }
}
