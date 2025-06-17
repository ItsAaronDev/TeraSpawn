package me.aaron.teraSpawn;

import me.aaron.TeraCore.color.PlaceHolder;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("spawn")) {
                SpawnManager spawnManager = new SpawnManager(SpawnManager.Type.ORGINAL);
                if(spawnManager.exist()){
                    Location location = spawnManager.getLocation();
                    player.teleport(location);
                    if(SpawnMain.getPlugin().getConfig().getBoolean("commands.spawn.message.enabled")){
                        player.sendMessage(PlaceHolder.replacePlaceholder(SpawnMain.getPlugin().getConfig().getString("commands.spawn.message.text")));
                    }
                }
                return true;
            }
            if (command.getName().equalsIgnoreCase("setspawn")) {
                if(player.hasPermission(SpawnMain.getPlugin().getConfig().getString("commands.setspawn.permission"))){
                    Location location = player.getLocation();
                    SpawnManager spawnManager = new SpawnManager(SpawnManager.Type.ORGINAL);
                    spawnManager.setLocation(location);
                    player.sendMessage(PlaceHolder.replacePlaceholder(SpawnMain.getPlugin().getConfig().getString("commands.setspawn.message")));
                }
                return true;
            }
            if (command.getName().equalsIgnoreCase("setfirstspawn")) {
                if(player.hasPermission(SpawnMain.getPlugin().getConfig().getString("commands.setfirstspawn.permission"))){
                    Location location = player.getLocation();
                    SpawnManager spawnManager = new SpawnManager(SpawnManager.Type.FIST);
                    spawnManager.setLocation(location);
                    player.sendMessage(PlaceHolder.replacePlaceholder(SpawnMain.getPlugin().getConfig().getString("commands.setfirstspawn.message")));
                }
                return true;
            }
        }
        return false;
    }
}
