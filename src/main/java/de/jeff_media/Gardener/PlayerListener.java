package de.jeff_media.Gardener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerListener implements Listener {

    Main main;

    PlayerListener(Main main) {
        this.main=main;
    }

    @EventHandler
    public void onTwerk(PlayerToggleSneakEvent e) {
        //long start = System.nanoTime();
        if(e.isCancelled()) return;
        if(!main.getPlayerData(e.getPlayer()).sneak()) return;
        main.debug("Attempt growth");
        main.schinken.twerk(e.getPlayer().getLocation());
        //System.out.println(System.nanoTime()-start);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        main.playerData.remove(e.getPlayer().getUniqueId());
    }
}
