package exodian.me.listplayersplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


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


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("listplayers")) {
            Player p = (Player) sender;
            ConfigurationSection bro = getConfig().getConfigurationSection("Brother");
            ConfigurationSection leader = getConfig().getConfigurationSection("Leader");
            ConfigurationSection acolyte = getConfig().getConfigurationSection("Acolyte");
            ConfigurationSection serf = getConfig().getConfigurationSection("Serf");
            ArrayList<String> toPrint = new ArrayList<String>();

            List<String> broNames = new ArrayList<>(bro.getKeys(false));
            for (int i = 0; i < broNames.size(); i++) {
                String pName = broNames.get(i);
                ConfigurationSection pSect = bro.getConfigurationSection(pName);
                toPrint.add("Brother "+pSect.getString("name") + " ");
            }

            List<String> leaderNames = new ArrayList<>(leader.getKeys(false));
            for (int i = 0; i < leaderNames.size(); i++) {
                String pName = leaderNames.get(i);
                ConfigurationSection pSect = leader.getConfigurationSection(pName);
                toPrint.add("Leader "+pSect.getString("name")+ " ");
            }

            List<String> acolyteNames = new ArrayList<>(acolyte.getKeys(false));
            for (int i = 0; i < acolyteNames.size(); i++) {
                String pName = acolyteNames.get(i);
                ConfigurationSection pSect = acolyte.getConfigurationSection(pName);
                toPrint.add("Acolyte "+pSect.getString("name")+ " ");
            }

            List<String> serfNames = new ArrayList<>(serf.getKeys(false));
            for (int i = 0; i < serfNames.size(); i++) {
                String pName = serfNames.get(i);
                ConfigurationSection pSect = serf.getConfigurationSection(pName);
                toPrint.add("Serf "+pSect.getString("name")+ " ");
            }

            p.sendMessage(toPrint.toString());

            return true;
        }
        return false;
    }



}
