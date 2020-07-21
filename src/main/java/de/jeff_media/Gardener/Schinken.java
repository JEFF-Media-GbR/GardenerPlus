package de.jeff_media.Gardener;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Schinken {

    Main main;

    Schinken(Main main) {
        this.main=main;
    }

    void twerk(Location loc) {
        GardenersRecap recap = PlantUtils.getGrowablesInRadius(loc,main.conf.radiusX,main.conf.radiusY);

        for(Block ageable : recap.ageables) {
            main.debug("Schinken: growing crop");
            PlantUtils.growAgeable(ageable);
        }

        /* Doesn't work, have to manually grow tree */
        for(Block sapling : recap.saplings) {
            main.debug("Schinken: growing sapling");
            main.treeUtils.growTree(sapling);
        }
    }

}
