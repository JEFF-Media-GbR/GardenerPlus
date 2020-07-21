package de.jeff_media.Gardener;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    HashMap<UUID,PlayerData> playerData;
    PlayerListener playerListener;
    CommandTest commandTest;
    Schinken schinken;
    TreeUtils treeUtils;
    Config conf;

    PlayerData getPlayerData(Player p) {
        if(playerData.containsKey(p.getUniqueId()))  return playerData.get(p.getUniqueId());
        PlayerData data = new PlayerData(conf.requiredSneaks);
        playerData.put(p.getUniqueId(),data);
        return data;
    }

    @Override
    public void onEnable() {

        conf = new Config(this);
        playerListener=new PlayerListener(this);
        commandTest=new CommandTest(this);
        treeUtils=new TreeUtils(this);
        schinken=new Schinken(this);
        playerData = new HashMap<>();

        //getCommand("test").setExecutor(commandTest);
        getServer().getPluginManager().registerEvents(playerListener,this);
    }

    void debug(String text) {
        if(conf.debug) getLogger().warning("[DEBUG] "+text);
    }
}
