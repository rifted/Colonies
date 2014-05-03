package com.gravirift.riftes.colonies;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ColoniesMain extends JavaPlugin implements Listener{
	static Server server;
	@Override
	public void onEnable(){
		getLogger().info("Running Colonies! Probably ALL other plugins won't work with this.");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new ColoniesBuilder(), this);
		getServer().getPluginManager().registerEvents(new ColoniesEntities(), this);
		server = getServer();
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Disabling Colonies! If this was unexpected, or your server crashed, expect rollbacks!");
	}
}
