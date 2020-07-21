import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class CommandTest implements CommandExecutor {

    Main main;

    CommandTest(Main main) {
        this.main=main;
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {


        return true;
    }
}
