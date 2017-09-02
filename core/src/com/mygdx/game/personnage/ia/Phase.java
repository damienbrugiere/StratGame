package com.mygdx.game.personnage.ia;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.Action.Attack;
import com.mygdx.game.Terrain;
import com.mygdx.game.cells.CellWorkable;
import com.mygdx.game.pathfinding.PathFinding;
import com.mygdx.game.personnage.Monstre;
import com.mygdx.game.personnage.Perso;
import com.mygdx.game.personnage.Soldat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by damien on 13/08/2017.
 */

public class Phase {

    public boolean condition(Monstre monstre){
        return true;
    }

    private void addValidElement(List<CellWorkable> element, List<List<CellWorkable>> list){
        if(element == null || element.isEmpty()){
            return;
        }
        list.add(element);
    }

    public void action(Monstre monstre, PathFinding pathFinding, List<Soldat> adversaires, List<Perso> persos){
        System.out.println("Il se passe un truc");
        Map<Soldat, List<List<CellWorkable>>> possibilites = new HashMap<>();
        Soldat ennemie = null;
        List<CellWorkable> chemin=null;
        for(Soldat adversaire : adversaires){
            if(!adversaire.isDead()) {
                List<List<CellWorkable>> buffer = new ArrayList<>();
                if (Terrain.getEst(adversaire.getCase()) != null && Terrain.getEst(adversaire.getCase()) instanceof CellWorkable) {
                    addValidElement(pathFinding.astar(monstre, (CellWorkable) Terrain.getEst(adversaire.getCase()), persos), buffer);
                }
                if (Terrain.getNord(adversaire.getCase()) != null && Terrain.getNord(adversaire.getCase()) instanceof CellWorkable) {
                    addValidElement(pathFinding.astar(monstre, (CellWorkable) Terrain.getNord(adversaire.getCase()), persos), buffer);
                }
                if (Terrain.getOuest(adversaire.getCase()) != null && Terrain.getOuest(adversaire.getCase()) instanceof CellWorkable) {
                    addValidElement(pathFinding.astar(monstre, (CellWorkable) Terrain.getOuest(adversaire.getCase()), persos), buffer);
                }
                if (Terrain.getSud(adversaire.getCase()) != null && Terrain.getSud(adversaire.getCase()) instanceof CellWorkable) {
                    addValidElement(pathFinding.astar(monstre, (CellWorkable) Terrain.getSud(adversaire.getCase()), persos), buffer);
                }
                if (!buffer.isEmpty()) {
                    possibilites.put(adversaire, buffer);
                }
            }

        }
        System.out.println("Possibilité : " + possibilites.values().size());
        for(Map.Entry<Soldat, List<List<CellWorkable>>> entry :possibilites.entrySet()){
            if(ennemie == null){
                ennemie = entry.getKey();
                chemin = entry.getValue().get(0);
            }
            for(List<CellWorkable> cells : entry.getValue()){
                if(chemin.size()> cells.size()){
                    ennemie = entry.getKey();
                    chemin = cells;
                }
            }

        }
        if(chemin == null){
            monstre.addAction(Actions.sequence(monstre.buildActions(null,new Attack(monstre,ennemie))));
        }
        System.out.println("On avance !!!");
        if(chemin.size()> monstre.getPmEnCours()){
            List<CellWorkable> buffer = new ArrayList<>();
            for(int i = 0 ; i < monstre.getPmEnCours(); i++){
                buffer.add(chemin.get(i));
            }
            chemin = buffer;
        }
        System.out.println("ok chemin en cours");
        monstre.addAction(Actions.sequence(monstre.buildActions(chemin,new Attack(monstre,ennemie))));
    }
}
