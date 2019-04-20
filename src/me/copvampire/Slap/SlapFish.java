package me.copvampire.Slap;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import net.citizensnpcs.api.CitizensAPI;

public class SlapFish implements Listener{


	private Slap plugin;

	public SlapFish(Slap plugin){
		this.plugin = plugin;
	}

	private Map<Player, Integer> hitCounter = new HashMap<>();

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {

			if (plugin.isCitizensEnabled() && CitizensAPI.getNPCRegistry().isNPC(e.getEntity())) return;

			Player damager = (Player)e.getDamager();
			Player target = (Player)e.getEntity();

			if (damager.hasPermission("slap.slapitems.fish")) {
				
				if (damager.getInventory().getItemInMainHand().getType() == Material.RAW_FISH) {

					if (!hitCounter.containsKey(damager)) hitCounter.put(damager, 1);
					

					if (plugin.getConfig().getBoolean("Fish_Name_Change_Enabled")){
					String fishitemname = new String(SlapLang.FISH_ITEM_NAME);//.replace("%counter%", String.valueOf(hitCounter.get(damager)));
					hitCounter.put(damager, hitCounter.get(damager) + 1);
					
					ItemStack fish = damager.getInventory().getItemInMainHand();
					ItemMeta fishMeta = fish.getItemMeta();
					fishMeta.setDisplayName(SlapLang.SLAP_PREFIX + fishitemname);
					fish.setItemMeta(fishMeta);
					damager.getInventory().setItemInMainHand(fish);
					}
					
					Vector product = damager.getLocation().getDirection().multiply(2);


					if (target.isOnGround() && product.getY() < 0.0)
						product.setY(0.4);

					if (plugin.getConfig().getBoolean("Fish_Push_Players_Enabled")){
						target.setVelocity(product);
					}

					if (plugin.getConfig().getBoolean("Fish_Player_Messages_Enabled")){
						String fishslapperMessage = new String(SlapLang.FSLAPPER_MESSAGE).replace("%player%", String.valueOf(target.getName()));
						String fishslappedMessage = new String(SlapLang.FSLAPPED_MESSAGE).replace("%name%", String.valueOf(damager.getName()));
						damager.sendMessage(SlapLang.SLAP_PREFIX + fishslapperMessage);
						target.sendMessage(SlapLang.SLAP_PREFIX + fishslappedMessage);

					}

					if (plugin.getConfig().getBoolean("Particles_Enabled")){
						(target.getWorld()).spawnParticle(Particle.CLOUD, target.getLocation(), 45, 0.45, 0.45, 0.45, 0.15);
					}
				}	
			}
		}


		if (e.getDamager() instanceof Player && (!(e.getEntity() instanceof Player))) {

			Player damager = (Player)e.getDamager();
			Entity target = e.getEntity();

			if (damager.hasPermission("slap.slapitems.fish")) {

				if (damager.getInventory().getItemInMainHand().getType() == Material.RAW_FISH) {

					Vector product = damager.getLocation().getDirection().multiply(2);

					if (plugin.getConfig().getBoolean("Fish_Name_Change_Enabled")){
						String fishitemname = new String(SlapLang.FISH_ITEM_NAME);//.replace("%counter%", String.valueOf(hitCounter.get(damager)));
						hitCounter.put(damager, hitCounter.get(damager) + 1);
						
						ItemStack fish = damager.getInventory().getItemInMainHand();
						ItemMeta fishMeta = fish.getItemMeta();
						fishMeta.setDisplayName(SlapLang.SLAP_PREFIX + fishitemname);
						fish.setItemMeta(fishMeta);
						damager.getInventory().setItemInMainHand(fish);
					}
					
					if (target.isOnGround() && product.getY() < 0.0)
						product.setY(0.4);

					if (plugin.getConfig().getBoolean("Fish_Push_Mobs_Enabled")){
						target.setVelocity(product);
					}

					if (plugin.getConfig().getBoolean("Fish_Mob_Messages_Enabled")){
						String fishmobslappedMessage = new String(SlapLang.FMOBSLAPPED_MESSAGE).replace("%mob%", String.valueOf(target.getName()));
						damager.sendMessage(SlapLang.SLAP_PREFIX + fishmobslappedMessage);
					}

					if (plugin.getConfig().getBoolean("Particles_Enabled")){
						(target.getWorld()).spawnParticle(Particle.CLOUD, target.getLocation(), 45, 0.45, 0.45, 0.45, 0.15);
					}
				}			
			}
		}
	}
}
