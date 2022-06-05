package dupepatch.runnables;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TNTDupe extends BukkitRunnable {

	public void run() {
		for (World world : Bukkit.getWorlds()) {
			List<Entity> entities = world.getEntities();
			entities.removeIf(it -> it.getType() != EntityType.PRIMED_TNT);
			for (Entity entity : entities) {
				List<Entity> nearbyEntities = entity.getNearbyEntities(5, 5, 5);
				nearbyEntities.removeIf(it -> it.getType() != EntityType.PLAYER);
				nearbyEntities.forEach((it) -> {
					Player player = (Player) it;
					if (player.getOpenInventory() != null
							&& player.getOpenInventory().getType().equals(InventoryType.CHEST)
							|| player.getOpenInventory().getType().equals(InventoryType.HOPPER)
							|| player.getOpenInventory().getType().equals(InventoryType.DROPPER)
							|| player.getOpenInventory().getType().equals(InventoryType.DISPENSER)) player.closeInventory();
				});
			}
		}
	}

}
