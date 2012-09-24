package net.loadingchunks.plugins.FlyingPig;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class FlyingPig extends JavaPlugin {
	
	public Logger log = Logger.getLogger("FlyingPig");
	public FlyingPigLogger handler;

	public void onEnable() {
		this.handler = new FlyingPigLogger();
		this.handler.setPlugin(this);
		this.getServer().getLogger().addHandler(this.handler);
	}
	
	public void onDisable() {
		
	}
}