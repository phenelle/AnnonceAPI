package com.cubitux.model.annonce;

import org.json.simple.JSONObject;

/**
 * Created by pierre on 2016-07-07.
 */
public class VehicleAnnonce extends Annonce {

    private String make;

    private String model;

    private Long year;

    public VehicleAnnonce() {
        super();
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("year", this.getYear());
        json.put("model", this.getModel());
        json.put("make", this.getMake());
        return json;
    }

}
