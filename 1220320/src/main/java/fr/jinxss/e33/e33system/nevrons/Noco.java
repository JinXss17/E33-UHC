package fr.jinxss.e33.e33system.nevrons;

import java.util.UUID;

import fr.jinxss.e33.Roles;

public class Noco extends Roles {
	
    public Noco(UUID uuid, String name) {
        super(uuid, name);
        this.speed =21;
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
