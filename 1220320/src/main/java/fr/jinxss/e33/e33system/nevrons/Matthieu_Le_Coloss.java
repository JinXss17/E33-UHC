package fr.jinxss.e33.e33system.nevrons;

import java.util.UUID;

import fr.jinxss.e33.Roles;

public class Matthieu_Le_Coloss extends Roles {
	
    public Matthieu_Le_Coloss(UUID uuid, String name) {
        super(uuid, name);
        this.force = 1;
        this.resi = 1;
    }

	@Override
	public void onAssign() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPowerUse() {
		// TODO Auto-generated method stub

	}

}
