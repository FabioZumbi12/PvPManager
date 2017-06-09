package me.NoChance.PvPManager;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.net.URLDecoder;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

public class PluginTest {

	private PvPManager plugin;
	private final Logger logger = Logger.getLogger("PvPManager-Test");
	private String filePath;
	private static PluginTest instance;

	public PluginTest() {
	}

	public final void setup() throws Exception {
		filePath = URLDecoder.decode(PluginTest.class.getClassLoader().getResource("").getPath(), "UTF-8");
		final String decoded = filePath + "TestServer/plugins/PvPManager";
		final File pluginDirectory = new File(decoded);
		pluginDirectory.mkdirs();
		final Server server = mock(Server.class, Mockito.RETURNS_MOCKS);
		Mockito.when(server.getPluginManager()).thenReturn(mock(PluginManager.class));
		Bukkit.setServer(server);
		plugin = PowerMockito.mock(PvPManager.class, Mockito.CALLS_REAL_METHODS);
		final PluginDescriptionFile pdf = new PluginDescriptionFile(PluginTest.class.getClassLoader().getResource("plugin.yml").openStream());
		Whitebox.invokeMethod(plugin, "init", (Object) null, server, pdf, pluginDirectory, new File(filePath), PluginTest.class.getClassLoader());
		Mockito.doReturn(logger).when(plugin).getLogger();
		Mockito.doReturn(mock(PluginCommand.class)).when(plugin).getCommand(Matchers.anyString());
		plugin.onEnable();
		System.out.println(pdf.getVersion());
	}

	public final void tearDown() {
		deleteDir(new File(filePath + "TestServer"));
	}

	private boolean deleteDir(final File file) {
		if (file.isDirectory()) {
			final String[] children = file.list();
			if (children != null) {
				for (final String aChildren : children) {
					final boolean success = deleteDir(new File(file, aChildren));
					if (!success)
						return false;
				}
			}
		}
		return file.delete();
	}

	public static PluginTest getInstance() {
		return instance;
	}

	public final PvPManager getPlugin() {
		return plugin;
	}

}
