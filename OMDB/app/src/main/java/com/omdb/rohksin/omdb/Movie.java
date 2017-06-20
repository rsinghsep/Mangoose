package com.omdb.rohksin.omdb;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Illuminati on 5/7/2017.
 */
public class Movie implements Serializable{

    private String name;
    private String releaseYear;
    private String PosterThumbnail;
    private String movieId;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPosterThumbnail() {
        return PosterThumbnail;
    }

    public void setPosterThumbnail(String posterThumbnail) {
        PosterThumbnail = posterThumbnail;
    }




}
