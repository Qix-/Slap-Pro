package me.copvampire.Slap;

import org.bukkit.ChatColor;

public class SlapLang {

	public static String SLAP_PREFIX;
	public static String SLAPPER_MESSAGE;
	public static String SLAPPED_MESSAGE;
	public static String SLAP_COOLDOWN_MESSAGE;
	
	public static String ROCKET_PREFIX;
	public static String ROCKETTER_MESSAGE;
	public static String ROCKETTED_MESSAGE;
	public static String ROCKET_COOLDOWN_MESSAGE;
	
	public static String FSLAPPER_MESSAGE;
	public static String FSLAPPED_MESSAGE;
	public static String FSLAP_COOLDOWN_MESSAGE;
	public static String FMOBSLAPPED_MESSAGE;
	public static String FISH_ITEM_NAME;

	public static String HURT_PREFIX;
	public static String HURTER_MESSAGE;
	public static String HURTEE_MESSAGE;
	public static String HURT_COOLDOWN_MESSAGE;

	public static String FREEZE_PREFIX;
	public static String FROZEN_MESSAGE; //message that comes up when player moves
	public static String FREEZED_MESSAGE; //message to the target on freeze
	public static String FREEZER_MESSAGE; //message to the command sender on freeze
	public static String UNFREEZER_MESSAGE; //message to the command sender on unfreeze
	public static String UNFROZEN_MESSAGE;  //message to the target on unfreeze
	public static String BROAD_FROZEN_MESSAGE;
	public static String BROAD_UNFROZEN_MESSAGE;
	public static String BROAD_SLAP_MESSAGE;
	public static String BROAD_ROCKET_MESSAGE;
	public static String BROAD_HURT_MESSAGE;

	public static void loadMessages(Slap plugin) {
		SLAP_PREFIX = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Slap_Prefix"));
		SLAPPER_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Slapper_Message"));
		SLAPPED_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Slapped_Message"));
		SLAP_COOLDOWN_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Slap_Cooldown_Massage"));
		
		ROCKET_PREFIX = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Rocket_Prefix"));
		ROCKETTER_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Rocketter_Message"));
		ROCKETTED_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Rocketted_Message"));
		ROCKET_COOLDOWN_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Rocket_Cooldown_Massage"));

		FSLAPPER_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Fish_Slap_Message"));
		FSLAPPED_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Fish_Slapped_Message"));
		FMOBSLAPPED_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Mob_Fish_Slapped_Message"));
		FISH_ITEM_NAME = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Fish_Item_Name"));

		HURT_PREFIX = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Hurt_Prefix"));
		HURTER_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Hurter_Message"));
		HURTEE_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Hurtee_Message"));
		HURT_COOLDOWN_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Hurt_Cooldown_Massage"));

		FREEZE_PREFIX = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Freeze_Prefix"));
		FROZEN_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Frozen_Message"));
		FREEZED_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Freezed_Massage"));
		FREEZER_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Freezer_Message"));
		UNFROZEN_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("UnFrozen_Message"));
		UNFREEZER_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("UnFreezer_Message"));

		BROAD_FROZEN_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Broadcast_Frozen_Message"));
		BROAD_UNFROZEN_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Broadcast_UnFrozen_Message"));
		BROAD_SLAP_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Broadcast_Slap_Message"));
		BROAD_ROCKET_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Broadcast_Rocket_Message"));
		BROAD_HURT_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Broadcast_Hurt_Message"));
	}
}
