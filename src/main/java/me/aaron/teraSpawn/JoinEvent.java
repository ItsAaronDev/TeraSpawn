package me.aaron.teraSpawn;

import me.aaron.TeraCore.color.PlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
       if(SpawnMain.getPlugin().isFist(player)){
            SpawnMain.getPlugin().setFirstJoin(player, true);
            int delay = SpawnMain.getPlugin().getConfig().getInt("fist_join.delay");
            Bukkit.getScheduler().runTaskLater(SpawnMain.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    SpawnManager spawnManager = new SpawnManager(SpawnManager.Type.FIST);
                    if(spawnManager.exist()){
                        Location location = spawnManager.getLocation();
                        player.teleport(location);
                    }

                    boolean broadcast = SpawnMain.getPlugin().getConfig().getBoolean("fist_join.message.broadcast.enabled");
                    if(broadcast){
                        ArrayList<String> text = (ArrayList<String>) SpawnMain.getPlugin().getConfig().getList("fist_join.message.broadcast.text");
                        for(String string : text){
                            String message = PlaceHolder.replacePlaceholder_Player(player, string);
                            for(Player online : Bukkit.getOnlinePlayers()){
                                online.sendMessage(message);
                            }
                        }
                    }

                    boolean self = SpawnMain.getPlugin().getConfig().getBoolean("fist_join.message.self.enabled");
                    if(self){
                        ArrayList<String> text = (ArrayList<String>) SpawnMain.getPlugin().getConfig().getList("fist_join.message.self.text");
                        for(String string : text){
                            String message = PlaceHolder.replacePlaceholder_Player(player, string);
                                player.sendMessage(message);
                        }
                    }


                }
            }, delay);
        }
    }

}
