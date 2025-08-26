package fr.jinxss.e33.TeamSystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TeamListeners implements Listener {

	private TeamSystem system;
	
	public TeamListeners(TeamSystem system) {
		this.system = system;
	}
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void OnDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player victim && e.getDamager() instanceof Player damager) {
			if(system.getPlayerTeam(victim) == system.getPlayerTeam(damager)) {
				e.setDamage(0);
			}
		}
	}
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void OnClickInInventory(InventoryClickEvent e) {
		
		if(e.getWhoClicked() instanceof Player p && e.getClickedInventory() == system.getInventory()) {
			e.setCancelled(true);
			int teamSlot = e.getSlot();
			if(teamSlot > system.getTeams().size()-1)return;
			Team team = system.getTeams().get(teamSlot);
			system.inversePlayerToTeam(p, team);
			p.closeInventory();
			
			Bukkit.getScheduler().runTaskLater(system.getMain(), () -> {
				system.OpenTeamInventory(p);
			}, 1);
		}
	}
}
