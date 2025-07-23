package fr.jinxss.e33.e33system.roles;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.Roles;

public class Sciel extends Roles {

    // Cartes placées par Sciel : <victime UUID, nombre de cartes>
    private final Map<UUID, Integer> cards = new HashMap<>();
    private final ItemStack deck;
    private final Random random = new Random();

    public Sciel(UUID uuid, String name) {
        super(uuid, name);

        deck = new ItemStack(Material.PAPER);
        ItemMeta meta = deck.getItemMeta();
        meta.setDisplayName("§dDeck de Sciel");
        deck.setItemMeta(meta);
        roleItems.add(deck);
    }

    @Override
    public void onAssign() {
        Player p = getPlayer();
        if (p == null) return;

        p.getInventory().addItem(deck);
        p.sendMessage("§dTu es Sciel ! Frappe les ennemis pour placer des cartes et utilise ton deck pour les faire exploser !");
    }

    @Override
    public void onPowerUse() {
        Player sciel = getPlayer();
        if (sciel == null) return;

        if (cards.isEmpty()) {
            sciel.sendMessage("§7Aucune carte placée.");
            return;
        }

        for (Map.Entry<UUID, Integer> entry : cards.entrySet()) {
            Player target = Bukkit.getPlayer(entry.getKey());
            if (target != null && target.isOnline() && !target.isDead()) {
                int amount = entry.getValue();
                double damage = amount * 0.2; // 0.1 coeur = 0.2 point de dégâts
                target.damage(damage, sciel);
                target.sendMessage("§cTu as reçu " + amount + " cartes infligeant §c" + damage + " §cdégâts !");
            }
        }

        sciel.sendMessage("§dTu as déchaîné toutes tes cartes !");
        sciel.playSound(sciel.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 1.2f);
        cards.clear();
    }

    // Appelé quand Sciel frappe un joueur
    public void onAttack(Player victim) {
        UUID victimId = victim.getUniqueId();
        int current = cards.getOrDefault(victimId, 0);
        int add = random.nextInt(5) + 1;
        int total = Math.min(current + add, 30);
        cards.put(victimId, total);

        getPlayer().sendMessage("§a+" + add + " cartes sur " + victim.getName() + " (§e" + total + "/30§a)");
    }

    // Utilisé par le listener plus tard
    public boolean isDeck(ItemStack item) {
        return item != null && item.isSimilar(deck);
    }
}