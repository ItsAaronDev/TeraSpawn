package me.aaron.teraSpawn;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SpawnMain extends JavaPlugin {

    private static SpawnMain spawnMain;

    public static SpawnMain getPlugin(){
        return spawnMain;
    }
    String Userlog_datafolder = "plugins/" + getName();
    private File Userlog_file= new File(Userlog_datafolder,  "log.yml");
    private FileConfiguration Userlog_config = YamlConfiguration.loadConfiguration(Userlog_file);

    @Override
    public void onEnable() {
        this.spawnMain = this;
        saveDefaultConfig();
        final PluginManager mp = Bukkit.getServer().getPluginManager();
        mp.registerEvents(new JoinEvent(), spawnMain);
        getPlugin().getCommand("spawn").setExecutor((CommandExecutor) new SpawnCommand());
        getPlugin().getCommand("setspawn").setExecutor((CommandExecutor) new SpawnCommand());
        getPlugin().getCommand("setfirstspawn").setExecutor((CommandExecutor) new SpawnCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isFist(Player player){
        if(Userlog_config.get(player.getUniqueId().toString()) != null) {
            return false;
        }
        return true;
    }
    public void setFirstJoin(Player player, boolean status){
        Userlog_config.set(player.getUniqueId().toString(), status);
        try{
            Userlog_config.save(Userlog_file);
        }catch (Exception exception){}
    }
}
