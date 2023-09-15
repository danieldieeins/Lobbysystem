package live.nerotv.stickfight;

import live.nerotv.Main;

public class Stickfight {

    private static Main instance;

    public Stickfight(Main main) {
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