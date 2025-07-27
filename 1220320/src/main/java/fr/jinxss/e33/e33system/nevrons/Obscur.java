package fr.jinxss.e33.e33system.nevrons;

import java.util.UUID;

import fr.jinxss.e33.Roles;

public class Obscur extends Roles {

    public Obscur(UUID uuid, String name) {
        super(uuid, name);
        this.force = 1;
    }

    @Override
    public void onAssign() {
        // Application des effets via les attributs génériques ou un system de gestion des effets
        // Cela sera traité plus tard dans la gestion centrale des effets
    }

    @Override
    public void onPowerUse() {
        // Aucun pouvoir actif
    }
}