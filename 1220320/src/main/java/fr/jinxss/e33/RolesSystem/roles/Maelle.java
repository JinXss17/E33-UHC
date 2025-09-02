package fr.jinxss.e33.RolesSystem.roles;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.RolesSystem.Roles;

public class Maelle extends Roles {

    private enum Posture {
        NEUTRE, DEFENSIVE, OFFENSIVE, VIRTUOSE
    }

    private Posture posture = Posture.NEUTRE;
    private final ItemStack postureItem;

    // Paramètres ajustables
    private final double MODIFIER = 0.3; // 30% bonus/malus
    private final double VIRTUOSE_MODIFIER = 0.5;
    private final int KILL_COUNT_TO_VIRTUOSE = 2;
    
    private int Kill_Count = 0;
    

    public Maelle(UUID uuid, String name) {
        super(uuid, name);

        postureItem = new ItemStack(Material.FEATHER);
        ItemMeta meta = postureItem.getItemMeta();
        meta.setDisplayName("§d⇄ Changer de posture");
        postureItem.setItemMeta(meta);

        roleItems.add(postureItem);
    }

    @Override
    public void onAssign() {
        Player player = getPlayer();
        if (player != null) {
            player.sendMessage("§dTu es Maelle ! Utilise l’objet pour changer de posture.");
            player.getInventory().addItem(postureItem);
        }
    }

    @Override
    public void onPowerUse() {
        Player player = getPlayer();
        if (player == null) return;

        // Changer de posture
        switch (posture) {
            case NEUTRE:
                posture = Posture.DEFENSIVE;
                player.sendMessage("§9→ Tu es maintenant en posture §lDéfensive§r§9.");
                break;
            case DEFENSIVE:
                posture = Posture.OFFENSIVE;
                player.sendMessage("§c→ Tu es maintenant en posture §lOffensive§r§c.");
                break;
            case OFFENSIVE:
            	if(Kill_Count >= KILL_COUNT_TO_VIRTUOSE) {
            		posture = Posture.VIRTUOSE;
                    player.sendMessage("§5→ Tu es maintenant en posture §lVirtuose§r§5.");
                    break;
            	}else {
            		posture = Posture.NEUTRE;
                    player.sendMessage("§a→ Tu es maintenant en posture §lNeutre§r§a.");
                    break;
            	}
            case VIRTUOSE:
            	posture = Posture.NEUTRE;
                player.sendMessage("§a→ Tu es maintenant en posture §lNeutre§r§a.");
                break;
            	
        }

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1.2f);
    }

    // Gérer les dégâts infligés
    public void handleAttack(EntityDamageByEntityEvent event) {
        if (posture == Posture.DEFENSIVE) {
            event.setDamage(event.getDamage() * (1 - MODIFIER));
        } else if (posture == Posture.OFFENSIVE) {
            event.setDamage(event.getDamage() * (1 + MODIFIER));
        } else if(posture == Posture.VIRTUOSE) {
        	event.setDamage(event.getDamage() * (1 + VIRTUOSE_MODIFIER));
        }
    }

    // Gérer les dégâts subis
    public void handleDamaged(EntityDamageEvent event) {
        if (posture == Posture.DEFENSIVE) {
            event.setDamage(event.getDamage() * (1 - MODIFIER));
        } else if (posture == Posture.OFFENSIVE) {
            event.setDamage(event.getDamage() * (1 + MODIFIER));
        }
    }
    
    public boolean isPostureItem(ItemStack item) {
        return item != null && item.isSimilar(postureItem);
    }
}