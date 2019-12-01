package trolio.gen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import trolio.Main;

public class Island 
{
	private Player owner;
	
	public Island (Player player)
	{
		this.owner = player;
		
		if (!Main.config.isConfigurationSection("Islands." + player.getUniqueId()))
		{
			Location newLocation = getNewLocation();
			ConfigurationSection section = Main.config.createSection("Islands." + player.getUniqueId());
			section.set("x", newLocation.getX());
			section.set("y", newLocation.getY());
			section.set("z", newLocation.getZ());
			
			IslandGen();
		}
	}
	
	private Location getNewLocation()
	{
		//generate a spawn location for the player
		int radius = 1000;
		int minRadius = Bukkit.getSpawnRadius() * 3;
		int maxRadius = radius * Main.config.getConfigurationSection("Islands").getKeys(false).size();
		
		return new Location (
				Main.skyWorld, minRadius + Math.random() * maxRadius * 2 - maxRadius, 70, 
				minRadius + Math.random() * maxRadius * 2 - maxRadius);
	}
	
	//generates the island based on the randomly generated location.
	public void IslandGen()
	{
		Bukkit.getLogger().info("Creating " + this.owner.getName());
		this.owner.sendMessage(ChatColor.GOLD + "Generating your island!");
		
		Block spawner = getIslandBase().getBlock();
		
		for (int x = spawner.getX(); x <= spawner.getX() + 7; x++)
		{
			for (int z = spawner.getZ(); z <= spawner.getZ() + 4; z++)
			{
				for (int y = spawner.getY(); y <= spawner.getY() + 15; y++)
				{
					int height = y - spawner.getY();
					
					if (height >= 0 && height <= 1)
					{
						int diceRoll = (int) Math.round(Math.random() * 5);
						
						switch (diceRoll)
						{
						case 0:
						case 1:
							Main.skyWorld.getBlockAt(x, y, z).setType(Material.DIRT);
							break;
						case 2:
						case 3:
						case 4:
							Main.skyWorld.getBlockAt(x, y, z).setType(Material.STONE);
							break;
						case 5:
							Main.skyWorld.getBlockAt(x, y, z).setType(Material.IRON_ORE);
							break;
						}
					}
					else if (height == 2)
					{
						Main.skyWorld.getBlockAt(x, y, z).setType(Material.SAND);
					}
					else if (height == 3)
					{
						Main.skyWorld.getBlockAt(x, y, z).setType(Material.GRASS);
					}
					else
					{
						Main.skyWorld.getBlockAt(x, y, z).setType(Material.AIR);
					}
				}
			}
			
			//spawn chest generation.
			Block chestBlock = getPlayerSpawn().getBlock().getRelative(4, 0, 2);
			chestBlock.setType(Material.CHEST);
			Chest chest = (Chest) chestBlock.getState();
			ItemStack seeds = new ItemStack(Material.SEEDS);
			seeds.setAmount(3);
			chest.getBlockInventory().addItem(
					new ItemStack(Material.LAVA_BUCKET),
					new ItemStack(Material.ICE),
					new ItemStack(Material.SUGAR_CANE),
					seeds);
			
			if (!Main.skyWorld.generateTree(getPlayerSpawn().add(3, 0, 0), TreeType.TREE))
				this.owner.sendMessage(ChatColor.RED + "Could not generate spawn tree!");
			
			//generate a piece of bedrock to lock in spawn location
			getPlayerSpawn().getBlock().getRelative(0, -3, 0).setType(Material.BEDROCK);
		}
	}
		
		private Location getIslandBase() 
		{
			return new Location(
				Main.skyWorld,
				Main.config.getDouble("Islands." + this.owner.getUniqueId() + ".x"),
				Main.config.getDouble("Islands." + this.owner.getUniqueId() + ".y"),
				Main.config.getDouble("Islands." + this.owner.getUniqueId() + ".z"));
		}
		
		private Location getPlayerSpawn()
		{
			return getIslandBase().add(2, 4, 2);
		}
		
		public void teleportIsland()
		{
			this.owner.sendMessage("Sending you to island!");
			this.owner.teleport(getPlayerSpawn());
		}
	
	}
