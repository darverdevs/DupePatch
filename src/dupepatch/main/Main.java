package dupepatch.main;

import dupepatch.runnables.TNTDupe;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new TNTDupe(), 1, 2);
    }

    @EventHandler
    public void onPortalEnter(EntityPortalEnterEvent event) {
        if (event.getEntityType() == EntityType.MINECART_CHEST || event.getEntityType() == EntityType.MINECART_HOPPER) {
            event.getEntity().getNearbyEntities(15, 15, 15).forEach((entity) -> {
                if (entity.getType() == EntityType.PLAYER) {
                    Player player = (Player) entity;
                    if (player.getOpenInventory() != null) player.closeInventory();
                }
            });
        }
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        if (event.getVehicle().getType() == EntityType.MINECART_CHEST || event.getVehicle().getType() == EntityType.MINECART_HOPPER) {
            event.getVehicle().getNearbyEntities(5, 5, 5).forEach((entity) -> {
                if (entity.getType() == EntityType.PLAYER) {
                    Player player = (Player) entity;
                    if (player.getOpenInventory() != null) player.closeInventory();
                }
            });
        }
    }



}
