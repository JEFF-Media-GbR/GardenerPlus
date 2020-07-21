import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    PlayerListener playerListener;
    CommandTest commandTest;
    Schinken schinken;
    Config conf;

    @Override
    public void onEnable() {

        conf = new Config(this);
        playerListener=new PlayerListener(this);
        commandTest=new CommandTest(this);
        schinken=new Schinken(this);


        //getCommand("test").setExecutor(commandTest);
        getServer().getPluginManager().registerEvents(playerListener,this);
    }
}
