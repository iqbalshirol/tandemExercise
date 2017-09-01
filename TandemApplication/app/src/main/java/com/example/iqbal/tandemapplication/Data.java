package com.example.iqbal.tandemapplication;

import java.io.Serializable;

/**
 * Created by Iqbal on 9/1/17.
 */
public class Data implements Serializable {
    private String flight_number;
    private String launch_year;
    private String land_success;
    private Links links;

    public String getFlight_number() {
        return flight_number;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public String getLand_success() {
        return land_success;
    }

    public String getLinks() {
        return links.toString();
    }
}
