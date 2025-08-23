package fr.jinxss.e33.RolesSystem.roles;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.RolesSystem.Roles;

public class Esquie extends Roles {

    public enum StoneType {
        URRIE,     // Aquatique
        FLORIE,    // Depth Strider
        SOARRIE,   // Dash
        DORRIE     // Onde de choc
    }

    private EnumSet<StoneType> stonesUnlocked = EnumSet.noneOf(StoneType.class);
    private Map<StoneType, ItemStack> powerItems = new HashMap<>();
    private long lastDashUse = 0;
    private long lastShockwaveUse = 0;

    public Esquie(UUID uuid, String name) {
        super(uuid, name);
        grantStone(StoneType.URRIE); // Urrie obtenue directement
    }

    @Override
    public void onAssign() {
        Player p = getPlayer();
        if (p == null) return;

        // Don initial d’Urrie (effet Respiration)
        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 2, false, false));
        p.sendMessage("§bTu es Esquie ! Trouve les 4 pierres pour débloquer tes pouvoirs...");
    }

    @Override
    public void onPowerUse() {
        // Ne rien faire ici → déclenché via items individuels
    }

    public boolean hasStone(StoneType stone) {
        return stonesUnlocked.contains(stone);
    }

    public void grantStone(StoneType stone) {
        if (stonesUnlocked.contains(stone)) return;

        stonesUnlocked.add(stone);
        Player p = getPlayer();
        if (p == null) return;

        switch (stone) {
            case FLORIE -> {
                p.sendMessage("§aTu as obtenu la pierre Florie !");
                p.getInventory().getBoots().addEnchantment(Enchantment.DEPTH_STRIDER, 3);
            }
            case SOARRIE -> {
                p.sendMessage("§cTu as obtenu la pierre Soarrie !");
                ItemStack dash = new ItemStack(Material.FEATHER);
                ItemMeta meta = dash.getItemMeta();
                meta.setDisplayName("§cCatalyseur du Vent (Dash)");
                dash.setItemMeta(meta);
                roleItems.add(dash);
                powerItems.put(StoneType.SOARRIE, dash);
                p.getInventory().addItem(dash);
            }
            case DORRIE -> {
                p.sendMessage("§6Tu as obtenu la pierre Dorrie !");
                ItemStack shock = new ItemStack(Material.BLAZE_ROD);
                ItemMeta shockMeta = shock.getItemMeta();
                shockMeta.setDisplayName("§6Catalyseur Sismique (Onde de choc)");
                shock.setItemMeta(shockMeta);
                roleItems.add(shock);
                powerItems.put(StoneType.DORRIE, shock);
                p.getInventory().addItem(shock);
            }
            case URRIE -> {
                p.sendMessage("§bEffet aquatique actif (Respiration III)");
                p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 2, false, false));
            }
        }

        p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1f, 1.5f); // 💡 Son provisoire
    }

    // Dash vers l'avant
    public void useDash() {
        Player p = getPlayer();
        if (p == null) return;

        long now = System.currentTimeMillis();
        if (now - lastDashUse < 2 * 60 * 1000) {
            p.sendMessage("§cDash en recharge !");
            return;
        }

        lastDashUse = now;
        p.setVelocity(p.getLocation().getDirection().normalize().multiply(1.8));
        p.sendMessage("§cDash activé !");
        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1f, 1f);
    }

    // Onde de choc
    public void useShockwave() {
        Player p = getPlayer();
        if (p == null) return;

        long now = System.currentTimeMillis();
        if (now - lastShockwaveUse < 60 * 1000) {
            p.sendMessage("§eOnde de choc en recharge !");
            return;
        }

        lastShockwaveUse = now;

        Bukkit.getOnlinePlayers().forEach(target -> {
            if (target == p) return;
            if (target.getLocation().distance(p.getLocation()) <= 10) {
                target.damage(4.0, p); // 2 cœurs
                target.setVelocity(target.getVelocity().setY(1.2));
                target.sendMessage("§cTu as été frappé par l’onde de choc d’Esquie !");
            }
        });

        p.sendMessage("§6Onde de choc déclenchée !");
        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1f, 1f);
    }

    public boolean isDashItem(ItemStack item) {
        return item != null && item.isSimilar(powerItems.get(StoneType.SOARRIE));
    }

    public boolean isShockwaveItem(ItemStack item) {
        return item != null && item.isSimilar(powerItems.get(StoneType.DORRIE));
    }
}