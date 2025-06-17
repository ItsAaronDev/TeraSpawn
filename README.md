
# TeraSpawn

**TeraSpawn** is a powerful and modern spawn management plugin for Minecraft servers.  
It allows you to set **two separate spawn points**:

- A **regular spawn** for all players
- A **first-join spawn** for new players joining the server for the first time

This is ideal for welcoming new players with a unique introduction area before joining the regular gameplay world.

TeraSpawn requires the following plugins to function:
- ✅ **TeraCore** – for modular command, message, and permission handling
- ✅ **PlaceholderAPI** – for dynamic placeholders in messages

---

## 🔧 Features

- Set two independent spawn locations (`/setspawn` and `/setfirstspawn`)
- Automatically teleport new players to a custom first-join spawn
- Customizable messages using placeholders
- Modern, modular, and easy to configure

🎉 **TeraSpawn – Make your server's first impression count!**

---

## 💡 Command Overview

| Command         | Permission                  | Description                                      |
|------------------|------------------------------|--------------------------------------------------|
| `spawn`          | `none`                      | Teleports the player to the main spawn location  |
| `setspawn`       | `teraspawn.setspawn`        | Sets the default server spawn                    |
| `setfirstspawn`  | `teraspawn.setfirstspawn`   | Sets the spawn for first-time players            |

---

# ✅ TeraSpawn APIs

## SpawnManager – Teleport to Spawn

```java
public void teleportSpawn(Player player) {
    SpawnManager spawnManager = new SpawnManager(SpawnManager.Type.ORIGINAL);
    if (!spawnManager.exist()) {
        player.sendMessage("Spawn not found!");
        return;
    }
    player.teleport(spawnManager.getLocation());
}
```

---

## SpawnManager – Set Spawn

```java
public void setSpawn(Location location) {
    SpawnManager spawnManager = new SpawnManager(SpawnManager.Type.ORIGINAL);
    spawnManager.setLocation(location);
}
```

---
