package me.copvampire.Slap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class RocketCmd implements CommandExecutor{

	private Slap plugin;

	public RocketCmd(Slap plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)){
			sender.sendMessage("Only players can use this command");
			return true;
		}

		Player player = (Player)sender;
		int rocketcooldownTime = plugin.getConfig().getInt("Rocket_Cooldown");

		if (player.hasPermission("slap.rocket.use")) {
			if (args.length > 0) {
				Player target = Bukkit.getServer().getPlayer(args[0]);

				if (target == null) {
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					return true;
				}

				String rocketbroadcast = new String(SlapLang.BROAD_ROCKET_MESSAGE).replace("%name%", String.valueOf(sender.getName())).replace("%player%", args[0]);
				
				if (plugin.getConfig().getBoolean("Broadcast_Messages")){
					Bukkit.getServer().broadcastMessage(SlapLang.ROCKET_PREFIX + rocketbroadcast);
				}

				if (player.hasPermission("slap.rocket.bypass")) {
					Vector rocketY = target.getVelocity().setY(10.0D);
					
					if (plugin.getConfig().getBoolean("Rocket_Push_Enabled")){
						target.setVelocity(rocketY);
					}

					if (plugin.getConfig().getBoolean("Particles_Enabled")){
						target.getWorld().spawnParticle(Particle.SMOKE_LARGE, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
						target.getWorld().spawnParticle(Particle.FLAME, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
					}
					

					String rocketterMessage = new String(SlapLang.ROCKETTER_MESSAGE).replace("%player%", args[0]);
					String rockettedMessage = new String(SlapLang.ROCKETTED_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
					player.sendMessage(SlapLang.ROCKET_PREFIX + rocketterMessage);
					target.sendMessage(SlapLang.ROCKET_PREFIX + rockettedMessage);
					return true;
				}

				if (this.plugin.cooldowns.containsKey(sender.getName())) {
					long secondsLeft = ((Long)this.plugin.cooldowns.get(sender.getName())).longValue() / 1000L + rocketcooldownTime - System.currentTimeMillis() / 1000L;
					if (secondsLeft > 0L) {
						String Rocket_Cooldown_Massage = new String(SlapLang.ROCKET_COOLDOWN_MESSAGE).replace("%secondsLeft%", String.valueOf(secondsLeft));
						sender.sendMessage(Rocket_Cooldown_Massage);
						return true;
					}
				}

				if (plugin.getConfig().getBoolean("CoolDowns_Enabled")){
					plugin.cooldowns.put(sender.getName(), Long.valueOf(System.currentTimeMillis()));
					Vector rocketY = target.getVelocity().setY(10.0D);
					
					if (plugin.getConfig().getBoolean("Rocket_Push_Enabled")){
						target.setVelocity(rocketY);
					}
				}

				if (plugin.getConfig().getBoolean("Particles_Enabled")){
					target.getWorld().spawnParticle(Particle.SMOKE_LARGE, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
					target.getWorld().spawnParticle(Particle.FLAME, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
				}

				String rocketterMessage = new String(SlapLang.ROCKETTER_MESSAGE).replace("%player%", args[0]);
				String rockettedMessage = new String(SlapLang.ROCKETTED_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
				player.sendMessage(SlapLang.ROCKET_PREFIX + rocketterMessage);
				target.sendMessage(SlapLang.ROCKET_PREFIX + rockettedMessage);
				return true;
			}

			player.sendMessage(SlapLang.ROCKET_PREFIX + ChatColor.GOLD + "Rocket somone by saying /rocket <name>");
			return true;
		} else {
			player.sendMessage(SlapLang.ROCKET_PREFIX + ChatColor.RED + "No permission!");
		}

		return false;
	}

}
