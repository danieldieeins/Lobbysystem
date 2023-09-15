package live.nerotv.pixels;

import live.nerotv.Main;

public class Pixels {

    private static Main instance;

    public Pixels(Main main) {
        instance = main;
    }

    public void onLoad() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}