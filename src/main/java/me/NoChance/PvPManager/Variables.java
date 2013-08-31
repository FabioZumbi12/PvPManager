package me.NoChance.PvPManager;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Variables {

	private PvPManager plugin;

	public static boolean inCombatEnabled;
	public static int timeInCombat;
	public static boolean stopCommands;
	public static boolean punishmentsEnabled;
	public static boolean keepItems;
	public static boolean keepExp;
	public static boolean killOnLogout;
	public static List<String> worldsExcluded;
	public static boolean disableFly;

	public Variables(PvPManager plugin) {
		this.plugin = plugin;
		InitialiseVariables();
	}

	public void InitialiseVariables() {
		inCombatEnabled = getBoolean("In Combat.Enabled");
		timeInCombat = getInt("In Combat.Time(seconds)");
		stopCommands = getBoolean("In Combat.Stop Commands");
		punishmentsEnabled = getBoolean("In Combat.Punishments.Enabled");
		keepItems = getBoolean("In Combat.Punishments.Kill on Logout.Keep Items");
		keepExp = getBoolean("In Combat.Punishments.Kill on Logout.Keep Exp");
		killOnLogout = getBoolean("In Combat.Punishments.Kill on Logout.Enabled");
		worldsExcluded = getStringList("World Exclusions");
		disableFly = getBoolean("In Combat.Disable Fly");
	}

	public boolean getBoolean(String a) {
		return plugin.getConfig().getBoolean(a);
	}

	public int getInt(String a) {
		return plugin.getConfig().getInt(a);
	}
	
	public List<String> getStringList(String a){
		return plugin.getConfig().getStringList(a);
	}
	
	public void helpMenu(Player player){
		player.sendMessage(ChatColor.GOLD
				+ "===== PvPManager Help Page =====");
		player.sendMessage(ChatColor.GOLD + "/pm "
				+ ChatColor.DARK_AQUA + "| Shows This Help Page");
		player.sendMessage(ChatColor.GOLD + "/pm reload "
				+ ChatColor.DARK_AQUA + "| Reloads PvPManager");
		player.sendMessage(ChatColor.GOLD + "/pm pvp <on/off> "
				+ ChatColor.DARK_AQUA + "| Sets PvP Enabled or Disabled.");
		player.sendMessage(ChatColor.GOLD + "/pm pvp status "
				+ ChatColor.DARK_AQUA + "| Checks if Your PvP is Enabled or Disabled.");
		player.sendMessage(ChatColor.GOLD
				+ "================================");
	}
	
}