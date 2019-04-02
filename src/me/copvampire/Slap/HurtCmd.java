package me.copvampire.Slap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HurtCmd implements CommandExecutor{

	private Slap plugin;
	public HurtCmd(Slap plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)){
			sender.sendMessage("Only players can use this command");
			return true;
		}
		
		int Hurt_Amount = plugin.getConfig().getInt("Hurt_Amount");
		
		Player player = (Player)sender;
		int hurtcooldownTime = plugin.getConfig().getInt("Hurt_Cooldown");

		if (player.hasPermission("slap.hurt.use")) {

			if (args.length > 0) {

				Player target = Bukkit.getServer().getPlayer(args[0]);

				if (target == null) {
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					return true;
				}

				String hurtbroadcast = new String(SlapLang.BROAD_HURT_MESSAGE).replace("%name%", String.valueOf(sender.getName())).replace("%player%", args[0]);

				if (plugin.getConfig().getBoolean("Broadcast_Messages")){
					Bukkit.getServer().broadcastMessage(SlapLang.HURT_PREFIX + hurtbroadcast);
				}

				if (player.hasPermission("slap.hurt.bypass")) {
					target.damage(Hurt_Amount);

					if (plugin.getConfig().getBoolean("Particles_Enabled")){
						target.getWorld().spawnParticle(Particle.REDSTONE, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
						target.getWorld().spawnParticle(Particle.REDSTONE, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
					}

					String hurtterMessage = new String(SlapLang.HURTER_MESSAGE).replace("%player%", args[0]);
					String hurttedMessage = new String(SlapLang.HURTEE_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
					player.sendMessage(SlapLang.HURT_PREFIX + hurtterMessage);
					target.sendMessage(SlapLang.HURT_PREFIX + hurttedMessage);
					return true;
				}

				if (this.plugin.cooldowns.containsKey(sender.getName())) {
					long secondsLeft = ((Long)this.plugin.cooldowns.get(sender.getName())).longValue() / 1000L + hurtcooldownTime - System.currentTimeMillis() / 1000L;
					if (secondsLeft > 0L) {
						String Hurt_Cooldown_Massage = new String(SlapLang.HURT_COOLDOWN_MESSAGE).replace("%secondsLeft%", String.valueOf(secondsLeft));
						sender.sendMessage(Hurt_Cooldown_Massage);
						return true;
					}
				}

				if (plugin.getConfig().getBoolean("CoolDowns_Enabled")){
					plugin.cooldowns.put(sender.getName(), Long.valueOf(System.currentTimeMillis()));
					target.damage(Hurt_Amount);
				}

				if (plugin.getConfig().getBoolean("Particles_Enabled")){
					target.getWorld().spawnParticle(Particle.REDSTONE, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
					target.getWorld().spawnParticle(Particle.REDSTONE, target.getLocation(), 100, 0.45, 0.45, 0.45, 0.01);
				}

				String hurtterMessage = new String(SlapLang.HURTER_MESSAGE).replace("%player%", args[0]);
				String hurttedMessage = new String(SlapLang.HURTEE_MESSAGE).replace("%name%", String.valueOf(sender.getName()));
				player.sendMessage(SlapLang.HURT_PREFIX + hurtterMessage);
				target.sendMessage(SlapLang.HURT_PREFIX + hurttedMessage);
				return true;
			}

			player.sendMessage(SlapLang.HURT_PREFIX + ChatColor.GOLD + "Rocket somone by saying /hurt <name>");
			return true;
		}

		else {
			player.sendMessage(SlapLang.HURT_PREFIX + ChatColor.RED + "No permission!");
		}

		return false;
	}

}