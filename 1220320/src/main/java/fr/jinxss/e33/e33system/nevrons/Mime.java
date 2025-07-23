package fr.jinxss.e33.e33system.nevrons;

import fr.jinxss.e33.Roles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public class Mime extends Roles {

    private Roles copiedRole;
    private boolean canCopy = true;
    private Set<UUID> alreadyCopied = new HashSet<>();

    public Mime(UUID uuid, String name) {
        super(uuid, name);
    }

    @Override
    public void onAssign() {
        Player player = getPlayer();
        if (player != null) {
            player.sendMessage("§dVous êtes Mime ! Utilisez §f/copie <joueur> §dpour copier son rôle pendant §c5 minutes§d.");
        }
    }

    @Override
    public void onPowerUse() {
        if (copiedRole != null) {
            copiedRole.onPowerUse();
        } else {
            getPlayer().sendMessage("§cVous n’avez copié aucun rôle pour l’instant !");
        }
    }

    public boolean copyRole(Player target) {
        if (!canCopy) {
            getPlayer().sendMessage("§cVous devez attendre la fin de la copie actuelle !");
            return false;
        }

        if (alreadyCopied.contains(target.getUniqueId())) {
            getPlayer().sendMessage("§cVous avez déjà copié ce joueur !");
            return false;
        }

        Roles targetRole = /* ici, accéder au rôle du joueur cible depuis votre système de gestion des rôles */;
        if (targetRole == null) {
            getPlayer().sendMessage("§cCe joueur n’a pas encore de rôle assigné !");
            return false;
        }

        this.copiedRole = targetRole;
        this.canCopy = false;
        alreadyCopied.add(target.getUniqueId());

        getPlayer().sendMessage("§aVous avez copié le rôle de §e" + target.getName() + "§a pour 5 minutes !");

        // Timer de 5 minutes
        new BukkitRunnable() {
            @Override
            public void run() {
                copiedRole = null;
                canCopy = true;
                getPlayer().sendMessage("§aVous pouvez copier un nouveau joueur !");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("E33-UHC"), 5 * 60 * 20L);

        return true;
    }
}
