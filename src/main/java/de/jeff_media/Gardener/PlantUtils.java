package de.jeff_media.Gardener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Sapling;

public class PlantUtils {

    Main main;

    PlantUtils(Main main) {
        this.main = main;
    }

    static GardenersRecap getGrowablesInRadius(Location loc, int radius, int radiusY) {

        GardenersRecap recap = new GardenersRecap();

        for (int x = (loc.getBlockX() - radius); x <= (loc.getBlockX() + radius); x++) {
            for (int y = (loc.getBlockY() - radiusY); y <= (loc.getBlockY() + radiusY); y++) {
                for (int z = (loc.getBlockZ() - radius); z <= (loc.getBlockZ() + radius); z++) {

                    Location l = new Location(loc.getWorld(), x, y, z);
                    if (l.distance(loc) > radius) continue;

                    Block block = l.getBlock();

                    // Cacti have to be checked before Ageables
                    if (isCactus(block)) {
                        recap.cacti.add(block);
                    }

                    if (PlantUtils.isSapling(block)) {
                        recap.saplings.add(block);
                    } else if (PlantUtils.isAgeable(block)) {
                        if (!PlantUtils.isGrown((Ageable) block.getBlockData())) {
                            recap.ageables.add(block);
                        }
                    }
                }
            }
        }

        return recap;

    }

    static void growAgeable(Block block) {
        if (!isAgeable(block)) return;
        Ageable ageable = (Ageable) block.getBlockData();
        ageable.setAge(ageable.getAge() + 1);
        block.setBlockData(ageable);
    }

    static void growCactus(Block block) {
        if (block.getType() != Material.CACTUS) return;
        if (block.getRelative(BlockFace.UP).getType() != Material.AIR) return;
        block.getRelative(BlockFace.UP).setType(Material.CACTUS);
    }

    static boolean isAgeable(Block block) {
        return block.getBlockData() instanceof Ageable;
    }

    static boolean isCactus(Block block) {
        if (block.getType() != Material.CACTUS) return false;
        if (block.getRelative(BlockFace.UP).getType() != Material.AIR) return false;
        return block.getRelative(BlockFace.DOWN).getType() != Material.CACTUS
                || block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() != Material.CACTUS;
    }

    static boolean isGrown(Ageable ageable) {
        return ageable.getAge() == ageable.getMaximumAge();
    }

    static boolean isSapling(Block block) {
        return block.getBlockData() instanceof Sapling;
    }
}
