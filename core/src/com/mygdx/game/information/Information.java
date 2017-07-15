package com.mygdx.game.information;

import com.mygdx.game.personnage.Soldat;
import com.mygdx.game.personnage.builder.PersoBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 06/07/2017.
 */

public class Information {
    private List<Soldat> team;
    private int po;

    public Information(InformationBuilder builder) {
        team = new ArrayList<>();
        if (builder.getTeam() != null) {

            for (PersoBuilder persoBuilder : builder.getTeam()) {
                team.add((Soldat) persoBuilder.build());
            }
        }
        po = builder.getPo();
    }

    public int getPo() {
        return po;
    }

    public void ajoutPo(int montant) {
        po += montant;
    }

    public void depensePo(int montant) {
        po -= montant;
    }

    public void addSoldat(Soldat soldat) {
        team.add(soldat);
    }

    public void removeSoldat(Soldat soldat) {
        team.remove(soldat);
    }

}
