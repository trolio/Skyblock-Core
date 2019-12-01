package trolio.handlers;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import trolio.Main;

public class Chat  implements Listener
{
	@EventHandler
	public void onPlayerChat (AsyncPlayerChatEvent event)
	{
		if (event.getPlayer().getWorld() == Main.skyWorld)
		{
			event.setFormat(ChatColor.UNDERLINE + "[SKYBLOCK]" + ChatColor.RESET + " %s : %s");
		}
	}
}
