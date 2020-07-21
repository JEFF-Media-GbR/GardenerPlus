package de.jeff_media.Gardener;

public class Config {

    final Main main;
    final int radiusX;
    final int radiusY;
    final int requiredSneaks;

    final boolean debug;


    public Config(Main main) {

        main.saveDefaultConfig();

        this.main=main;

        radiusX = getInt("radiusX");
        radiusY = getInt("radiusY");
        requiredSneaks = getInt("required-sneaks");

        debug = main.getConfig().getBoolean("debug");
    }

    private int getInt(String path) {
        return main.getConfig().getInt(path);
    }
}
