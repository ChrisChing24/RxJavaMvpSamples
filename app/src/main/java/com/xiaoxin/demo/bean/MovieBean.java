package com.xiaoxin.demo.bean;

/**
 * Created by chris on 2017/8/30.
 */

public class MovieBean {

    private String movieName;
    private String moviePoster;//电影海报
    private String classicLines;//经典台词

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getClassicLines() {
        return classicLines;
    }

    public void setClassicLines(String classicLines) {
        this.classicLines = classicLines;
    }
}
