package me.copvampire.Slap;

import org.bukkit.entity.Player;

public class PlayerData extends YMLHandler{

	public PlayerData(Slap main) {
		super(main, "playerdata.yml");
		
	}
	
	public void newPlayer(Player player) {
		config.set(player.getUniqueId().toString(), "true");
	}




}
