package trolio.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;

import trolio.Main;

public class Save implements Listener
{
	@EventHandler
	public void onWorldSave (WorldSaveEvent event)
	{
		if (event.getWorld() == Main.skyWorld)
		{
			Main.saveData();
		}
	}
}
