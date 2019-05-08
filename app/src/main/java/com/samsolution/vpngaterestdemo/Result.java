package com.samsolution.vpngaterestdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("HostName")
    @Expose
    private String hostName;
    @SerializedName("IP")
    @Expose
    private String iP;
    @SerializedName("Score")
    @Expose
    private String score;
    @SerializedName("Latency")
    @Expose
    private String latency;
    @SerializedName("Speed")
    @Expose
    private String speed;
    @SerializedName("CountryLong")
    @Expose
    private String countryLong;
    @SerializedName("CountryShort")
    @Expose
    private String countryShort;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIP() {
        return iP;
    }

    public void setIP(String iP) {
        this.iP = iP;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLatency() {
        return latency;
    }

    public void setLatency(String latency) {
        this.latency = latency;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCountryLong() {
        return countryLong;
    }

    public void setCountryLong(String countryLong) {
        this.countryLong = countryLong;
    }

    public String getCountryShort() {
        return countryShort;
    }

    public void setCountryShort(String countryShort) {
        this.countryShort = countryShort;
    }
}
