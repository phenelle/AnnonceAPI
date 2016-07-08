package com.cubitux.model.annonce;

import org.json.simple.JSONObject;

/**
 * Created by pierre on 2016-07-07.
 */
public class MovieAnnonce extends Annonce {

    private String movieType;

    public MovieAnnonce() {
        super();
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("movieType", this.movieType);
        return json;
    }

}
