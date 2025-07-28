package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.PictoSystem.PictoSystem;

public class E33UHC extends JavaPlugin {

	
	@SuppressWarnings("unused")
	private PictoSystem pictoSystem;
	
 	@Override
    public void onEnable() {
 		pictoSystem = new PictoSystem(this);
        getLogger().info("MonPlugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
	
}
