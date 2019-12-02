package trolio;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import trolio.commands.CreateIsland;
import trolio.commands.IslandHome;
import trolio.commands.IslandVisit;
import trolio.gen.ChunkGen;
import trolio.handlers.Chat;
import trolio.handlers.CommandHandler;
import trolio.handlers.Save;

/**
 * 
 * @author trolio
 *
 */

public class Main extends JavaPlugin
{
	public static World skyWorld;
	String worldName = "skyworld";
	public static YamlConfiguration config;
	String configName = "config.yml";
	public static File cfgFile;
	
	@Override
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage("Skyblock by: trolio");
		Bukkit.getConsoleSender().sendMessage("Skyblock Version: 1.0");
		
		//generate / load in the skyworld file.
		WorldCreator creator = new WorldCreator(worldName);
		creator.generator(new ChunkGen());
		creator.environment(Environment.NORMAL);
		skyWorld = creator.createWorld();
		
		//register events
		getServer().getPluginManager().registerEvents(new Save(),this);
		getServer().getPluginManager().registerEvents(new Chat(), this);
		
		//register commands on start.
		this.registerCommands();
		
		//Initialize configuration file.
		cfgFile = new File(getDataFolder() + File.separator + configName);
		try
		{
			config = YamlConfiguration.loadConfiguration(cfgFile);
			Bukkit.getLogger().info("Config Loaded!");
		}catch (Exception e)
		{
			Bukkit.getLogger().info("Could not load config file!");
			config = new YamlConfiguration();
		}
		
		if (!config.contains("Islands"))
		{
			config.createSection("Islands");
			saveData();
		}
		
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getLogger().info("Saving Skyblock Data!");
		saveData();
	}
	
	//register commands.
	public void registerCommands()
	{
		CommandHandler handler = new CommandHandler();
		
		handler.register("create", new CreateIsland());
		handler.register("restart", new CreateIsland());
		handler.register("home", new IslandHome());
		handler.register("visit", new IslandVisit());
		
		getCommand("island").setExecutor(handler);
	}
	
	public static void saveData()
	{
		try
		{
			Main.config.save(Main.cfgFile);
		}catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
