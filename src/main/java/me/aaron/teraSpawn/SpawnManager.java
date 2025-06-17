package me.aaron.teraSpawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SpawnManager {

    private File file;
    private FileConfiguration config;

    public enum Type {
        ORGINAL, FIST
    }

    private Type type;

    public SpawnManager(Type type){
        this.type = type;
        String datafolder = "plugins/" + SpawnMain.getPlugin().getName();
        file = new File(datafolder,  "spawn.yml");
        config = YamlConfiguration.loadConfiguration(file);

    }

    public boolean exist(){
        if(config.getString( type.toString() + ".location.world") != null){
            return true;
        }
        return false;
    }

    public Type getType(){
        return this.type;
    }

    public void setLocation(Location location){
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        String world = location.getWorld().getName();
        double yaw = location.getYaw();
        double pitch = location.getPitch();

        config.set(type.toString() + ".location.x", x);
        config.set(type.toString() + ".location.y", y);
        config.set(type.toString() + ".location.z", z);
        config.set(type.toString() + ".location.world", world);
        config.set(type.toString() + ".location.yaw", yaw);
        config.set(type.toString() + ".location.pitch", pitch);
        try{
            config.save(file);
        }catch (Exception ex){
        }
    }

    public Location getLocation(){
        if(exist()) {
            double x = config.getDouble(type.toString() + ".location.x");
            double y = config.getDouble(type.toString() + ".location.y");
            double z = config.getDouble(type.toString() + ".location.z");
            double yaw = config.getDouble(type.toString() + ".location.yaw");
            double pitch = config.getDouble(type.toString() + ".location.pitch");
            String world = config.getString(type.toString() + ".location.world");
            World bukkitWorld = Bukkit.getWorld(world);
            Location location = new Location(bukkitWorld, x, y, z);
            location.setYaw((float) yaw);
            location.setPitch((float) pitch);
            return location;
        }
        return null;
    }

}
