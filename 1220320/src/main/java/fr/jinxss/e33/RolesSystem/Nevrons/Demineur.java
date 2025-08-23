package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RolesSystem.Roles;

public class Demineur extends Roles {

    private boolean canUse = true;
    @SuppressWarnings("unused")
	private final long COOLDOWN = 5 * 60 * 20L; // 5 minutes in ticks
    private final ItemStack explosiveArrowItem; // ✅ champ global ajouté ici

    public Demineur(UUID uuid, String name) {
        super(uuid, name);

        explosiveArrowItem = new ItemStack(Material.STICK); // ✅ affecté ici
        var meta = explosiveArrowItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§cFlèche explosive");
            explosiveArrowItem.setItemMeta(meta);
        }

        roleItems.add(explosiveArrowItem);
    }

    @Override
    public void onAssign() {
        Player player = getPlayer();
        if (player != null) {
            player.sendMessage("§6Vous avez reçu le rôle Nevron : §cDémineur§6 !");
            player.getInventory().addItem(roleItems.get(0));
        }
    }

    @Override
    public void onPowerUse() {
        if (!canUse) {
            getPlayer().sendMessage("§cPouvoir en recharge...");
            return;
        }

        Player player = getPlayer();
        if (player == null) return;

        // On tire une flèche vers l'avant
        var arrow = player.launchProjectile(org.bukkit.entity.Arrow.class);
        arrow.setVelocity(player.getLocation().getDirection().multiply(2));
        arrow.setShooter(player);
        arrow.setCritical(true);
        arrow.setCustomName("ExplosiveArrow");

        player.sendMessage("§eFlèche explosive tirée !");
        canUse = true;
    }
        // Recharge (oublie le temps de mettre un coldown)
        /*
        new BukkitRunnable() {
            @Override
            public void run() {
                canUse = true;
                Player p = getPlayer();
                if (p != null) p.sendMessage("§aVotre flèche explosive est rechargée !");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("E33-UHC"), COOLDOWN);
    }*/
    
    public boolean isDemineurItem(ItemStack item) {
        return item != null && item.isSimilar(explosiveArrowItem);
    }
}
