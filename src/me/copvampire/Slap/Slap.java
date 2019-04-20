package me.copvampire.Slap;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Slap extends JavaPlugin implements Listener {

	protected SlapUpdateChecker updateChecker;

	public Slap plugin = this;

	private boolean citizensEnabled;
	
	private PlayerData playerData;

	public Map<String, Long> cooldowns = new HashMap<>();

	public void onEnable(){
		
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		Bukkit.getPluginManager().registerEvents(this, this);
		this.playerData = new PlayerData(this);

		this.saveDefaultConfig();

		this.plugin.getServer().getLogger().info("[Slap-Pro] has been enabled!");

		SlapUpdateChecker checker = new SlapUpdateChecker(this, 7150);
		if (checker.queryUpdateCheck() && checker.requiresUpdate()){
			this.plugin.getServer().getLogger().warning("[Slap-Pro] A Newer version has been released!");
			this.plugin.getServer().getLogger().warning("[Slap-Pro] https://www.spigotmc.org/resources/slap-pro.7150/");

		}else{

			this.plugin.getServer().getLogger().info("[Slap-Pro] No new version found");

		}

		this.getCommand("slap").setExecutor(new SlapCmd(this));
		this.getCommand("rocket").setExecutor(new RocketCmd(this));
		this.getCommand("hurt").setExecutor(new HurtCmd(this));
		this.getServer().getPluginManager().registerEvents(new SlapFish(this), this);
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		FreezeCmd freeze = new FreezeCmd(this);
		this.getCommand("freeze").setExecutor(freeze);
		this.getServer().getPluginManager().registerEvents(freeze, this);
		

		SlapLang.loadMessages(this);
		
	    this.citizensEnabled = Bukkit.getPluginManager().getPlugin("Citizens") != null;
	}

	public void onDisable(){

		this.plugin.getServer().getLogger().info("[Slap-Pro] has been disabled!");
		
		playerData.save();
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		
		playerData.newPlayer(event.getPlayer());
		
		
		if(event.getPlayer().hasPermission("slap.reload")) {
			SlapUpdateChecker checker = new SlapUpdateChecker(this, 7150);
			if (checker.queryUpdateCheck() && checker.requiresUpdate()){
				event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "Slap-Pro" + ChatColor.GRAY + "]" + ChatColor.GREEN + " A Newer version has been released!");
				event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "Slap-Pro" + ChatColor.GRAY + "]" + ChatColor.GREEN + " spigotmc.org/resources/7150/");
			}
		}
	}

	public boolean isCitizensEnabled(){
	    return citizensEnabled;
	}
}
