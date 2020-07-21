import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Sapling;

import java.util.ArrayList;

public class PlantUtils {


    static boolean isAgeable(Block block) {
        return block.getBlockData() instanceof Ageable;
    }

    static boolean isGrown(Ageable ageable) {
        return ageable.getAge() == ageable.getMaximumAge();
    }

    static boolean isSapling(Block block) {
        return block.getBlockData() instanceof Sapling;
    }

    static void growAgeable(Block block) {
        if(!isAgeable(block)) return;
        Ageable ageable = (Ageable) block.getBlockData();
        ageable.setAge(ageable.getAge()+1);
        block.setBlockData(ageable);
    }

    /* Does not work, have to manually grow the tree
    static void growSapling(Block block) {
        if(!isSapling(block)) return;
        Sapling sapling = (Sapling) block.getBlockData();
        sapling.setStage(sapling.getStage()+1);
        block.setBlockData(sapling);
    }*/

    static GardenersRecap getGrowablesInRadius(Location loc, int radius, int radiusY) {

        GardenersRecap recap = new GardenersRecap();

        for (int x = (loc.getBlockX()-radius); x <= (loc.getBlockX()+radius); x++) {
            for (int y = (loc.getBlockY()-radiusY); y <= (loc.getBlockY()+radiusY); y++) {
                for (int z = (loc.getBlockZ()-radius); z <= (loc.getBlockZ()+radius); z++) {

                    Location l = new Location(loc.getWorld(), x, y, z);
                    if (l.distance(loc) > radius) continue;

                    Block block = l.getBlock();

                    if(PlantUtils.isSapling(block)) {
                        recap.saplings.add(block);
                    }

                    else if(PlantUtils.isAgeable(block)) {
                        if (!PlantUtils.isGrown((Ageable) block.getBlockData())) {
                            recap.ageables.add(block);
                        }
                    }
                }
            }
        }

        return recap;

    }
}
