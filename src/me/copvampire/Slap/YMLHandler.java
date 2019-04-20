package me.copvampire.Slap;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class YMLHandler {

	protected Slap main;
	private File file;
	protected static FileConfiguration config;
	
	public YMLHandler(Slap main, String filename) throws IOException {
		this.main = main;
		this.file = new File(main.getDataFolder(), filename);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		YMLHandler.config = YamlConfiguration.loadConfiguration(file);
		
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
