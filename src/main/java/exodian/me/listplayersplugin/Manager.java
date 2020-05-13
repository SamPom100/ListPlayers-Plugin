package exodian.me.listplayersplugin;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

public class Manager implements Listener {

    private Plugin plugin;

    public Manager(Plugin p) {
        plugin = p;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                try {
                    final Team bros = player.getServer().getScoreboardManager().getMainScoreboard().getTeam("Brothers");
                    final Team leader = player.getServer().getScoreboardManager().getMainScoreboard().getTeam("Leader");
                    final Team acolyte = player.getServer().getScoreboardManager().getMainScoreboard().getTeam("Acolyte");
                    final String name = player.getName();

                    if (bros.hasEntry(name) && !plugin.getConfig().contains(name)) {
                        plugin.getConfig().set("alivePlayers.Brother.name", player.getName());
                    } else if (leader.hasEntry(name) && !plugin.getConfig().contains(name)) {
                        plugin.getConfig().set("alivePlayers.Leader.name", player.getName());
                    } else if (acolyte.hasEntry(name) && !plugin.getConfig().contains(name)) {
                        plugin.getConfig().set("alivePlayers.Acolyte.name", player.getName());
                    } else {
                        plugin.getConfig().set("alivePlayers.Serf.name", player.getName());
                    }
                    plugin.saveConfig();
                } catch (Exception e) {
                    System.out.print("Error adding new player");
                }
            }
        }, 150);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SPECTATOR) {
            try {
                final Team bros = player.getServer().getScoreboardManager().getMainScoreboard().getTeam("Brothers");
                final Team leader = player.getServer().getScoreboardManager().getMainScoreboard().getTeam("Leader");
                final Team acolyte = player.getServer().getScoreboardManager().getMainScoreboard().getTeam("Acolyte");
                final String name = player.getName();

                if (bros.hasEntry(name)) {
                    plugin.getConfig().set("alivePlayers.Brother.name" + player.getName(), null);
                } else if (leader.hasEntry(name)) {
                    plugin.getConfig().set("alivePlayers.Leader.name" + player.getName(), null);
                } else if (acolyte.hasEntry(name)) {
                    plugin.getConfig().set("alivePlayers.Acolyte.name" + player.getName(), null);
                } else {
                    plugin.getConfig().set("alivePlayers.Serf.name" + player.getName(), null);
                }
                plugin.saveConfig();

            } catch (Exception e) {
                System.out.print("Error removing player");
            }
        }
    }
}
