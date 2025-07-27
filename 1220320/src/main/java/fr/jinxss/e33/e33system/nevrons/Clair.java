package fr.jinxss.e33.e33system.nevrons;

import java.util.UUID;

import fr.jinxss.e33.Roles;

public class Clair extends Roles {
    
	public Clair(UUID uuid, String name) {
        super(uuid, name);
        this.resi = 1;
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