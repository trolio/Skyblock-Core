package trolio;

import org.bukkit.plugin.java.JavaPlugin;

import trolio.commands.*;
import trolio.handlers.CommandHandler;

public class Main extends JavaPlugin
{
	
	public void registerCommands()
	{
		CommandHandler handler = new CommandHandler();
		
		handler.register("island", new IslandCommand());
		
		handler.register("create", new CreateIsland());
		handler.register("delete", new DeleteIsland());
		handler.register("lock", new IslandLock());
		handler.register("unlock", new IslandUnlock());
		getCommand("island").setExecutor(handler);
	}
	
	@Override
	public void onEnable()
	{
		this.registerCommands();
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
