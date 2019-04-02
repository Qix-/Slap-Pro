package me.copvampire.Slap;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SlapCmd implements CommandExecutor {

	private Slap plugin;

	public SlapCmd(Slap plugin){
		this.plugin = plugin;
	}

	private static final Random random = new Random();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command");
			return true;
		}

		Player player = (Player)sender;
		int slapcooldownTime = this.plugin.getConfig().getInt("Slap_Cooldown");

		if (player.hasPermission("slap.slap.use")){

			if (args.length > 0){

				if (args[0].equalsIgnoreCase("reload")) {
					if (player.hasPermission("slap.reload")) {
						this.plugin.reloadConfig();
						SlapLang.loadMessages(this.plugin);
						player.sendMessage(SlapLang.SLAP_PREFIX + ChatColor.GOLD + "Config has been reloaded!");
						return true;
					}

					player.sendMessage(SlapLang.SLAP_PREFIX + ChatColor.RED + "No permission!");

				}else{

					Player target = Bukkit.getServer().getPlayer(args[0]);

					if (target == null) {
						sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
						return true;
					}
					

					String slapbroadcast = new String(SlapLang.BROAD_SLAP_MESSAGE).replace("%name%", String.valueOf(sender.getName())).replace("%player%", args[0]);
					
					if (plugin.getConfig().getBoolean("Broadcast_Messages")){
						Bukkit.getServer().broadcastMessage(SlapLang.SLAP_PREFIX + slapbroadcast);
					}

					if (player.hasPermission("slap.slap.bypass")) {

						Vector slapY = target.getVelocity().setY(random.nextDouble()).setX(random.nextDouble() * 2 - 1).setZ(random.nextDouble() * 2 - 1);
						

						if (plugin.getConfig().getBoolean("Slap_Push_Enabled")){
							target.setVelocity(slapY);
						}

						if (plugin.getConfig().getBoolean("Particles_Enabled")){
							target.getWorld().spawnParticle(Particle.CLOUD, target.getLocation(), 45, 0.45, 0.45, 0.45, 0.15);
						}

						String slapperMessage = new String(SlapLang.SLAPPER_MESSAGE).replace("%player%", args[0]);
						String slappedMessage = new String(SlapLang.SLAPPED_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
						player.sendMessage(SlapLang.SLAP_PREFIX + slapperMessage);
						target.sendMessage(SlapLang.SLAP_PREFIX + slappedMessage);
						return true;
					}

					if (this.plugin.cooldowns.containsKey(sender.getName())) {
						long secondsLeft = ((Long)this.plugin.cooldowns.get(sender.getName())).longValue() / 1000L + slapcooldownTime - System.currentTimeMillis() / 1000L;
						if (secondsLeft > 0L) {
							String Slap_Cooldown_Massage = new String(SlapLang.SLAP_COOLDOWN_MESSAGE).replace("%secondsLeft%", String.valueOf(secondsLeft));
							sender.sendMessage(Slap_Cooldown_Massage);
							return true;
						}
					}

					if (plugin.getConfig().getBoolean("CoolDowns_Enabled")){
						this.plugin.cooldowns.put(sender.getName(), Long.valueOf(System.currentTimeMillis()));
						Vector slapY = target.getVelocity().setY(random.nextDouble()).setX(random.nextDouble() * 2 - 1).setZ(random.nextDouble() * 2 - 1);
						
						if (plugin.getConfig().getBoolean("Slap_Push_Enabled")){
							target.setVelocity(slapY);
						}
					}

					if (plugin.getConfig().getBoolean("Particles_Enabled")){
						target.getWorld().spawnParticle(Particle.CLOUD, target.getLocation(), 45, 0.45, 0.45, 0.45, 0.15);
					}

					String slapperMessage = new String(SlapLang.SLAPPER_MESSAGE).replace("%player%", args[0]);
					String slappedMessage = new String(SlapLang.SLAPPED_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
					player.sendMessage(SlapLang.SLAP_PREFIX + slapperMessage);
					target.sendMessage(SlapLang.SLAP_PREFIX + slappedMessage);
					return true;
				}
			}

			player.sendMessage(SlapLang.SLAP_PREFIX + ChatColor.GOLD + "Slap somone by saying /slap <name>");
			return true;
		}

		player.sendMessage(SlapLang.SLAP_PREFIX + ChatColor.RED + "No permission!");

		return false;
	}
}