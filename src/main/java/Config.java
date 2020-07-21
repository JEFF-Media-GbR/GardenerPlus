public class Config {

    final Main main;
    final int radiusX;
    final int radiusY;


    public Config(Main main) {
        this.main=main;

        radiusX = getInt("radiusX");
        radiusY = getInt("radiusY");
    }

    private int getInt(String path) {
        return main.getConfig().getInt(path);
    }
}
