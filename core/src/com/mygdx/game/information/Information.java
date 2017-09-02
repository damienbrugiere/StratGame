package com.mygdx.game.information;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.personnage.Soldat;
import com.mygdx.game.personnage.builder.PersoBuilder;
import com.mygdx.game.personnage.builder.PersoRecrutement;

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

    public void acheter(PersoRecrutement perso) {
        if (po - perso.getPrix() < 0) {
            return;
        }
        depensePo(perso.getPrix());
        addSoldat(perso.toSoldat());
    }

    public List<Image> getImages() {
        List<Image> images = new ArrayList<>();
        for (Soldat soldat : this.team) {
            images.add(soldat.getPortrait());
        }
        return images;
    }

    public List<Soldat> getTeam() {
        return team;
    }

    public void checkTeam() {
        List<Soldat> buffer = new ArrayList<>();
        for (Soldat soldat : team) {
            if (soldat.isDead()) {
                buffer.add(soldat);
            }
        }
        team.removeAll(buffer);
    }
}
