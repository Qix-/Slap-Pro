package me.copvampire.Slap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ThanosCmd implements CommandExecutor{

	private Slap plugin;
	public ThanosCmd(Slap plugin){
		this.plugin = plugin;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)){
			sender.sendMessage("Only players can use this command");
			return true;
		}

		Player player = (Player)sender;

		if (player.hasPermission("slap.thanos.use")) {

			
			
			
		}

		else {
			player.sendMessage(SlapLang.ROCKET_PREFIX + ChatColor.RED + "No permission!");
		}

		return false;
	}

}