package fr.jinxss.e33.PictoSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.PictoSystem.Command.CommandGivePicto;
import fr.jinxss.e33.PictoSystem.Command.CommandPicto;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.EsquiveParfaite;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.GardeOptimal;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.Immortel;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.Inversion;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.Survivaliste;
import fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto.Berserk;
import fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto.TirPrecis;
import fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes.AgilliteFeline;
import fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes.ContreParfait;
import fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto.MineurChanceux;
import fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto.MineurDor;

public class PictoSystem {
	
	private static List<Class<? extends Picto>> BasicPicto = List.of(
			EsquiveParfaite.class, GardeOptimal.class, Immortel.class, Inversion.class, Survivaliste.class,
			Berserk.class, TirPrecis.class,
			AgilliteFeline.class, ContreParfait.class,
			MineurChanceux.class, MineurDor.class
		);
	
	private static HashMap<UUID, PlayerPictos> playerPicto = new HashMap<UUID, PlayerPictos>();

	private E33UHC plugin;
	
	public PictoSystem(E33UHC plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("givePicto").setExecutor(new CommandGivePicto(this));
		plugin.getCommand("Picto").setExecutor(new CommandPicto(this));
		this.plugin.getServer().getPluginManager().registerEvents(new PictoListener(this.plugin, this), this.plugin);

		
	}
	
	@Deprecated
	public void GiveRandomPictoToPlayer(Player p) {
		
	try {
		Random r  = new Random();
		int ChoosedPicto = r.nextInt(BasicPicto.size()-1);
		Picto picto = BasicPicto.get(ChoosedPicto).newInstance();
		boolean ValidatePicto = true;
		int boucle = 0;
		while(ValidatePicto) {
			ValidatePicto = false;
			ChoosedPicto = r.nextInt(BasicPicto.size()-1);
			picto = BasicPicto.get(ChoosedPicto).newInstance();
			for(Picto pictos : getPlayerPictos(p).getPictoList()) {
				if(picto.getClass() == pictos.getClass())ValidatePicto=true;
			}
			if(boucle >= BasicPicto.size()) {
				return;
			}
			boucle ++;
		}
		
		getPlayerPictos(p).addToPictoList(picto);
			
		p.sendMessage("Picto as Added : " + picto.toString());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Class<?extends Picto>> getDroppablePicto(){
		return BasicPicto;
	}
	
	public PlayerPictos getPlayerPictos(Player player) {
		return playerPicto.getOrDefault(player.getUniqueId(), new PlayerPictos(plugin.getLevelSystem().getPlayerLevel(player)));
	}
	public void registerPlayerPictos(Player p, PlayerPictos pictos) {
			playerPicto.put(p.getUniqueId(), pictos);
	}
	
}
