package trolio.handlers;

import trolio.Main;

public class LockHandler 
{
	
	public static void LockIsland()
	{
		Main.configIslands.addDefault("IslandLocked", true);
	}
	
	public static void UnlockIsland()
	{
		Main.configIslands.getBoolean("IslandLocked", false);
	}
	
}
