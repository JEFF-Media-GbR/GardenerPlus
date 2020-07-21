package de.jeff_media.Gardener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTest implements CommandExecutor {

    Main main;

    CommandTest(Main main) {
        this.main=main;
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {


        return true;
    }
}
