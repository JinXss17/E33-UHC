package fr.jinxss.e33.e33system.roles;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.e33system.Roles;

public class Renoir extends Roles {

    private ItemStack invocationItem;
    private long lastUse = 0;

    public Renoir(UUID uuid, String name) {
        super(uuid, name);
    }

    @Override
    public void onAssign() {
        Player p = getPlayer();
        if (p == null) return;

        invocationItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = invocationItem.getItemMeta();
        meta.setDisplayName("§5Catalyseur du Néant");
        invocationItem.setItemMeta(meta);

        roleItems.add(invocationItem);
        p.getInventory().addItem(invocationItem);

        p.sendMessage("§5Tu es Renoir ! Utilise ton catalyseur pour invoquer des monstres.");
    }

    @Override
    public void onPowerUse() {
        Player p = getPlayer();
        if (p == null) return;

        long now = System.currentTimeMillis();
        if (now - lastUse < 60 * 1000) {
            p.sendMessage("§cPouvoir en recharge...");
            return;
        }

        lastUse = now;

        p.sendMessage("§dTu invoques les créatures du Néant !");
        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 0.5f);

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target == p) continue;

            if (target.getLocation().distance(p.getLocation()) <= 10) {
                for (int i = 0; i < 3; i++) {
                    Zombie mob = target.getWorld().spawn(target.getLocation(), Zombie.class);
                    mob.setTarget(target);
                    mob.setCustomName("§cCréature du Néant");
                    mob.setCustomNameVisible(false);
                    // Ligne a erreur (elle sert a modifier la vie des entité de renoir
                    //mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(8.0); // 4 cœurs
                    mob.setHealth(8.0);
                    mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 20, 1));
                }
                target.sendMessage("§cDes créatures du Néant ont été invoquées près de toi !");
            }
        }
    }

    public boolean isInvocationItem(ItemStack item) {
        return item != null && item.isSimilar(invocationItem);
    }
}
