package com.zhangjiebo.grouppurchase.bean;

/**
 * Created by hehe on 2015/11/16.
 */
public class FilmInfo {

    /**
     * filmName : 复仇者联盟2：奥创纪元
     * imageUrl : http://f1.lashouimg.com/cinema/film/150/201503/25/55753-29018.jpg
     * showCinemasCount : 6
     * status : 0
     * dimensional : 1
     * posterUrl : http://f1.lashouimg.com/cinema/film/480_app/201503/25/55753-29018.jpg
     * short_brief : 让复联群侠绝望的敌人出现了
     * showSchedulesCount : 124
     * duration : 142
     * releaseDate : 2015-05-12
     * starCode : 1
     * have_schedule : 1
     * grade : 8.8
     * imax : 1
     * brief :
     * filmId : 55753
     */

    private String filmName;
    private String imageUrl;
    private int showCinemasCount;
    private int status;
    private String dimensional;
    private String posterUrl;
    private String short_brief;
    private int showSchedulesCount;
    private String duration;
    private String releaseDate;
    private String starCode;
    private int have_schedule;
    private String grade;
    private String imax;
    private String brief;
    private String filmId;

    public FilmInfo(String filmName, String imageUrl, String grade) {
        this.filmName = filmName;
        this.imageUrl = imageUrl;
        this.grade = grade;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setShowCinemasCount(int showCinemasCount) {
        this.showCinemasCount = showCinemasCount;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDimensional(String dimensional) {
        this.dimensional = dimensional;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setShort_brief(String short_brief) {
        this.short_brief = short_brief;
    }

    public void setShowSchedulesCount(int showSchedulesCount) {
        this.showSchedulesCount = showSchedulesCount;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setStarCode(String starCode) {
        this.starCode = starCode;
    }

    public void setHave_schedule(int have_schedule) {
        this.have_schedule = have_schedule;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setImax(String imax) {
        this.imax = imax;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getShowCinemasCount() {
        return showCinemasCount;
    }

    public int getStatus() {
        return status;
    }

    public String getDimensional() {
        return dimensional;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getShort_brief() {
        return short_brief;
    }

    public int getShowSchedulesCount() {
        return showSchedulesCount;
    }

    public String getDuration() {
        return duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getStarCode() {
        return starCode;
    }

    public int getHave_schedule() {
        return have_schedule;
    }

    public String getGrade() {
        return grade;
    }

    public String getImax() {
        return imax;
    }

    public String getBrief() {
        return brief;
    }

    public String getFilmId() {
        return filmId;
    }
}
