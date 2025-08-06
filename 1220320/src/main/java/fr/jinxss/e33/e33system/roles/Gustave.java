package fr.jinxss.e33.e33system.roles;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.e33system.Roles;

public class Gustave extends Roles {

    private int jauge = 0;
    private boolean charged = false;
    private boolean onCooldown = false;

    private final int MAX_JAUGE = 10;
    @SuppressWarnings("unused")
	private final long COOLDOWN = 20 * 120; // 2 minutes en ticks (20 ticks = 1 seconde)

    private final ItemStack gustaveItem;

    public Gustave(UUID uuid, String name) {
        super(uuid, name);

        // Création de l'objet spécial
        gustaveItem = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = gustaveItem.getItemMeta();
        meta.setDisplayName("§6⛓ Coup Chargé");
        gustaveItem.setItemMeta(meta);

        roleItems.add(gustaveItem);
    }

    @Override
    public void onAssign() {
        Player player = getPlayer();
        if (player != null) {
            player.sendMessage("§6Tu es Gustave ! Frappe pour remplir ta jauge. Utilise ton Coup Chargé intelligemment !");
            player.getInventory().addItem(gustaveItem);
        }
    }

    @Override
    public void onPowerUse() {
        Player player = getPlayer();

        if (player == null) return;

        if (onCooldown) {
            player.sendMessage("§cTon pouvoir est en recharge !");
            return;
        }

        if (charged) {
            player.sendMessage("§eTu as déjà activé ton Coup Chargé !");
            return;
        }

        // Activation
        charged = true;
        
        /*En commentaire le temps que le coldown soit disponible*/
        /*
        onCooldown = true;
        player.sendMessage("§aTon prochain coup sera chargé !");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);

        // Lancer le cooldown
        new BukkitRunnable() {
            @Override
            public void run() {
                onCooldown = false;
                player.sendMessage("§aTon Coup Chargé est à nouveau disponible !");
            }
        }.runTaskLater(E33Main.getInstance(), COOLDOWN);*/
    }

    // Appelée lors d’un coup porté par ce joueur
    public void handleAttack(EntityDamageByEntityEvent event) {
        //if (!(event.getDamager() instanceof Player)) return;
        Player attacker = (Player) event.getDamager();
        if (!attacker.getUniqueId().equals(uuid)) return;

        // Augmenter la jauge si pas encore au max
        if (jauge < MAX_JAUGE) {
            jauge++;
            attacker.sendMessage("§7[Jauge de Gustave] ➤ " + jauge + "/10");
        }

        // Si coup chargé, infliger dégâts bonus
        if (charged) {
            double bonusDamage = jauge * 0.5; // Ex : 0.5 cœur par niveau de jauge
            event.setDamage(event.getDamage() + bonusDamage);
            attacker.sendMessage("§cCoup chargé ! Dégâts bonus : " + bonusDamage + " !");
            attacker.playSound(attacker.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 0.8f);

            // Réinitialiser la jauge et l’état de charge
            charged = false;
            jauge = 0;
        }
    }
	
	public boolean isGustaveItem(ItemStack item) {
	    return item != null && item.isSimilar(gustaveItem);
	}

}
