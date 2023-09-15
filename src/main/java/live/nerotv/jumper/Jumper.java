package live.nerotv.jumper;

import live.nerotv.Main;

public class Jumper {

    private static Main instance;

    public Jumper(Main main) {
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