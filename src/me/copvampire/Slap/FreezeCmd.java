package me.copvampire.Slap;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class FreezeCmd implements Listener, CommandExecutor{

	private Slap plugin;
	public FreezeCmd(Slap plugin){
		this.plugin = plugin;
	}
	
	ArrayList<String> frozen = new ArrayList<String>();
	
	  @EventHandler
	  public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (this.frozen.contains(p.getName().toString())) {
	      e.setTo(e.getFrom());
			p.sendMessage(SlapLang.FREEZE_PREFIX + SlapLang.FROZEN_MESSAGE);
		}
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)){
			sender.sendMessage("Only players can use this command");
			return true;
		}

		Player player = (Player)sender;

		if (player.hasPermission("slap.freeze.use")) {

			String freezerMessage = new String(SlapLang.FREEZER_MESSAGE).replace("%player%", args[0]);
			String frozenMessage = new String(SlapLang.FREEZED_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
			String unfreezerMessage = new String(SlapLang.UNFREEZER_MESSAGE).replace("%player%", args[0]);
			String unfrozenMessage = new String(SlapLang.UNFROZEN_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
			
			String frozenbroadcast = new String(SlapLang.BROAD_FROZEN_MESSAGE).replace("%name%", String.valueOf(sender.getName())).replace("%player%", args[0]);
			String unfrozenbroadcast = new String(SlapLang.BROAD_UNFROZEN_MESSAGE).replace("%name%", String.valueOf(sender.getName())).replace("%player%", args[0]);
			
			if (args.length > 0) {

				Player target = Bukkit.getServer().getPlayer(args[0]);

				if (target == null) {
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					return true;
				}
				
				if (this.frozen.contains(target.getName().toString())) {
					frozen.remove(target.getName().toString());
					
					if (plugin.getConfig().getBoolean("Broadcast_Messages")){
						Bukkit.getServer().broadcastMessage(SlapLang.FREEZE_PREFIX + unfrozenbroadcast);
					}
					player.sendMessage(SlapLang.FREEZE_PREFIX + unfreezerMessage);
					target.sendMessage(SlapLang.FREEZE_PREFIX + unfrozenMessage);
					return true;
				}
				
				if(frozen.add(target.getName().toString())) {
					if (plugin.getConfig().getBoolean("Broadcast_Messages")){
						Bukkit.getServer().broadcastMessage(SlapLang.FREEZE_PREFIX + frozenbroadcast);
					}
					player.sendMessage(SlapLang.FREEZE_PREFIX + freezerMessage);
					target.sendMessage(SlapLang.FREEZE_PREFIX + frozenMessage);
					return true;
				}
				
			}

			player.sendMessage(SlapLang.FREEZE_PREFIX + ChatColor.GOLD + "Freeze somone by saying /freeze <name>");
			return true;
		}

		else {
			player.sendMessage(SlapLang.FREEZE_PREFIX + ChatColor.RED + "No permission!");
		}

		return false;
	}

}