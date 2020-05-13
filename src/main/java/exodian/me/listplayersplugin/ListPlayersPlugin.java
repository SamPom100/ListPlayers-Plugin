package exodian.me.listplayersplugin;

import org.bukkit.plugin.java.JavaPlugin;


public final class ListPlayersPlugin extends JavaPlugin {


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Manager(this), this);
        getLogger().info("ListPlayers v1.0 has started.");
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
