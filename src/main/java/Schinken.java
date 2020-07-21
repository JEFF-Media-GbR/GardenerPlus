import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;

import java.util.ArrayList;

public class Schinken {

    Main main;

    Schinken(Main main) {
        this.main=main;
    }

    void twerk(Location loc) {
        GardenersRecap recap = PlantUtils.getGrowablesInRadius(loc,main.conf.radiusX,main.conf.radiusY);

        for(Block ageable : recap.ageables) {
            PlantUtils.growAgeable(ageable);
        }

        /* Doesn't work, have to manually grow tree
        for(Block sapling : recap.saplings) {
            PlantUtils.growSapling(sapling);
        }*/
    }

}
