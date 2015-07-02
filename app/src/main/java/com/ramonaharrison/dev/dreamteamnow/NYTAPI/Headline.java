package com.ramonaharrison.dev.dreamteamnow.NYTAPI;

/**
 * Created by kadeemmaragh on 7/2/15.
 */
import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Headline implements Serializable{

    @Expose
    private String main;
    @Expose
    private String kicker;

    /**
     *
     * @return
     * The main
     */
    public String getMain() {
        return main;
    }

    /**
     *
     * @param main
     * The main
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     *
     * @return
     * The kicker
     */
    public String getKicker() {
        return kicker;
    }

    /**
     *
     * @param kicker
     * The kicker
     */
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

}