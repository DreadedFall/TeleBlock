package io.github.dreadedfall.teleblock;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Teleblock extends JavaPlugin {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	
	private String teleblock = "ENDER_STONE";
	private String teleword = "POOF";
	private String cancelword = "cancel";
	
	private PluginManager pm;
	
	private BukkitScheduler scheduler;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.setBlock(this.getConfig().getString("Teleblock").toUpperCase());
		this.setTeleword(this.getConfig().getString("Teleword"));
		this.setCancelWord(this.getConfig().getString("Cancelword"));
		this.pm = this.getServer().getPluginManager();
		this.setScheduler(this.getServer().getScheduler());
		pm.registerEvents(new TeleblockListener(this), this);
		log.info(String.format("Teleblock - Enabled"));
	}
	
	@Override
	public void onDisable() {
		log.info(String.format("Teleblock - Disabled"));
	}
	
	private void setBlock(String b) {
		this.teleblock = b;
	}
	
	public String getBlock() {
		return this.teleblock.toUpperCase();
	}
	
	private void setTeleword(String t) {
		this.teleword = t;
	}
	
	public String getTeleword() {
		return this.teleword;
	}
	
	private void setCancelWord(String c) {
		this.cancelword = c;
	}
	
	public String getCancelWord() {
		return this.cancelword;
	}

	public BukkitScheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(BukkitScheduler scheduler) {
		this.scheduler = scheduler;
	}
}
