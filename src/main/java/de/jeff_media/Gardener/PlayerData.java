package de.jeff_media.Gardener;

public class PlayerData {

    int sneakCount = 0;
    final int maxSneakCount;

    PlayerData(int maxSneakCount) {
        this.maxSneakCount=maxSneakCount;
    }

    boolean sneak() {
        if(++sneakCount >= maxSneakCount) {
            sneakCount=0;
            return true;
        }
        return false;
    }

}
