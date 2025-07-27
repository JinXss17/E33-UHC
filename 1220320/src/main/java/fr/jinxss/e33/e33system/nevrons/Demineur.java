package fr.jinxss.e33.e33system.nevrons;

import java.util.UUID;

import fr.jinxss.e33.Roles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class Demineur extends Roles {

    private boolean canUse = true;
    private final long COOLDOWN = 5 * 60 * 20L; // 5 minutes in ticks

    public Demineur(UUID uuid, String name) {
        super(uuid, name);

        // Objet spécial : bâton nommé "Flèche explosive"
        ItemStack explosiveArrowItem = new ItemStack(Material.STICK); 
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
        canUse = false;

        // Recharge
        new BukkitRunnable() {
            @Override
            public void run() {
                canUse = true;
                Player p = getPlayer();
                if (p != null) p.sendMessage("§aVotre flèche explosive est rechargée !");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("E33-UHC"), COOLDOWN);
    }
}
