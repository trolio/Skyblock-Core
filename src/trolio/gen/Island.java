package trolio.gen;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import trolio.Main;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("deprecation")
public class Island extends JavaPlugin {
    private Player owner;

    public Island(Player player) {
        this.owner = player;

        if (!Main.configIslands.isConfigurationSection("Islands." + player.getUniqueId())) {
            Location newLocation = getNewLocation();
            ConfigurationSection section = Main.configIslands.createSection("Islands." + player.getUniqueId());
            section.set("x", newLocation.getX());
            section.set("y", newLocation.getY());
            section.set("z", newLocation.getZ());
        }
    }

    private Location getNewLocation() {
        int radius = 5000;
        int minRadius = Bukkit.getSpawnRadius() * 3;
        int maxRadius = radius * Main.configIslands.getConfigurationSection("Islands").getKeys(false).size();

        return new Location(Main.skyWorld, minRadius + Math.random() * maxRadius * 2 - minRadius, 70, minRadius + Math.random() * maxRadius * 2 - maxRadius);
    }

    public void islandGen() {
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schematic = new File(getDataFolder() + File.separator + "/schematics/island.schematic");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(getIslandBase().getWorld()), 10000);

        try {
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.paste(session, new Vector(getIslandBase().getX(), getIslandBase().getY(), getIslandBase().getZ()), false);
        } catch (MaxChangedBlocksException | DataException | IOException e) {
            e.printStackTrace();
        }
    }

    private Location getIslandBase() {
        return new Location(Main.skyWorld,
                Main.configIslands.getDouble("Islands." + this.owner.getUniqueId() + ".x"),
                Main.configIslands.getDouble("Islands." + this.owner.getUniqueId() + ".y"),
                Main.configIslands.getDouble("Islands." + this.owner.getUniqueId() + ".z"));
    }

    public Location getPlayerSpawn() {
        return getIslandBase().add(2, 4, 2);
    }

    public void teleportIsland()
    {
        this.owner.sendMessage(ChatColor.GOLD + "Sending you to your island");
    }
}
