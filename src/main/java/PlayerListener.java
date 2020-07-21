import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerListener implements Listener {

    Main main;

    PlayerListener(Main main) {
        this.main=main;
    }

    @EventHandler
    public void onTwerk(PlayerToggleSneakEvent e) {
        long start = System.nanoTime();
        if(e.isCancelled()) return;
        main.schinken.twerk(e.getPlayer().getLocation());
        System.out.println(System.nanoTime()-start);
    }
}
