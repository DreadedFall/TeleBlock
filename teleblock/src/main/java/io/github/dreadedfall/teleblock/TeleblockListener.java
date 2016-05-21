package io.github.dreadedfall.teleblock;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TeleblockListener implements Listener {

	private final Teleblock main;

	public TeleblockListener(Teleblock t) {
		this.main = t;
	}

	private boolean findBlock(Player p) {
		for(int x = -10; x <= 10; x++) {
			for(int y = -10; y <= 10; y++) {
				for(int z = -10; z <= 10; z++) {
					if(p.getLocation().add(x, y, z).getBlock().getType().toString().equals(main.getBlock())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onTeleword(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().equalsIgnoreCase(main.getTeleword()) && findBlock(p)) {
			p.sendMessage("You will be randomly teleported in 5 seconds");
			p.sendMessage("Say " + main.getCancelWord() + " to cancel");
			this.teleportStart(p);
			e.setCancelled(true);
		}
		if (e.getMessage().equalsIgnoreCase(main.getCancelWord())) {
			main.getScheduler().cancelAllTasks();
			e.setCancelled(true);
		}
	}

	public void teleportStart(final Player p) {
		main.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			public void run() {
				if(!(findBlock(p))) {
					main.getScheduler().cancelAllTasks();
				} else {
					p.sendMessage("5");
				}
			}
		}, 0L);
		main.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			public void run() {
				if(!(findBlock(p))) {
					main.getScheduler().cancelAllTasks();
				} else {
					p.sendMessage("4");
				}
			}
		}, 20L);
		main.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			public void run() {
				if(!(findBlock(p))) {
					main.getScheduler().cancelAllTasks();
				} else {
					p.sendMessage("3");
				}
			}
		}, 40L);
		main.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			public void run() {
				if(!(findBlock(p))) {
					main.getScheduler().cancelAllTasks();
				} else {
					p.sendMessage("2");
				}
			}
		}, 60L);
		main.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			public void run() {
				if(!(findBlock(p))) {
					main.getScheduler().cancelAllTasks();
				} else {
					p.sendMessage("1");
				}
			}
		}, 80L);
		main.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			public void run() {
				Random r = new Random();
				double x, z;
				double posX = r.nextInt(101);
				double posZ = r.nextInt(101);
				double negX = posX * -1;
				double negZ = posZ * -1;
				
				if(r.nextInt(2) == 0) {
					x = posX;
				} else {
					x = negX;
				}
				
				if(r.nextInt(2) == 0) {
					z = posZ;
				} else {
					z = negZ;
				}
				if(!(findBlock(p))) {
					main.getScheduler().cancelAllTasks();
				} else {
					p.teleport(p.getLocation().add(x, 0, z));
				}
			}
		}, 100L);
	}
}
