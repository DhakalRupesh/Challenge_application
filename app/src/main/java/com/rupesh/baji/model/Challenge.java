package com.rupesh.baji.model;

public class Challenge {
    private String _id;
    private String chBy;
    private String chAcceptedby;
    private String chGame;
    private String chAmt;
    private String chDate;
    private String chTime;
    private String chDesc;
    private String chImage;
    private String status;

    public Challenge(String _id, String chBy, String chAcceptedby, String chGame, String chAmt, String chDate, String chTime, String chDesc, String chImage, String status) {
        this._id = _id;
        this.chBy = chBy;
        this.chAcceptedby = chAcceptedby;
        this.chGame = chGame;
        this.chAmt = chAmt;
        this.chDate = chDate;
        this.chTime = chTime;
        this.chDesc = chDesc;
        this.chImage = chImage;
        this.status = status;
    }

    public Challenge(String chBy, String chGame, String chAmt, String chDate, String chTime, String chDesc, String chImage, String status) {
        this.chBy = chBy;
        this.chGame = chGame;
        this.chAmt = chAmt;
        this.chDate = chDate;
        this.chTime = chTime;
        this.chDesc = chDesc;
        this.chImage = chImage;
        this.status = status;
    }

    public Challenge(String chBy, String chGame, String chAmt, String chImage) {
        this.chBy = chBy;
        this.chGame = chGame;
        this.chAmt = chAmt;
        this.chImage = chImage;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getChBy() {
        return chBy;
    }

    public void setChBy(String chBy) {
        this.chBy = chBy;
    }

    public String getChAcceptedby() {
        return chAcceptedby;
    }

    public void setChAcceptedby(String chAcceptedby) {
        this.chAcceptedby = chAcceptedby;
    }

    public String getChGame() {
        return chGame;
    }

    public void setChGame(String chGame) {
        this.chGame = chGame;
    }

    public String getChAmt() {
        return chAmt;
    }

    public void setChAmt(String chAmt) {
        this.chAmt = chAmt;
    }

    public String getChDate() {
        return chDate;
    }

    public void setChDate(String chDate) {
        this.chDate = chDate;
    }

    public String getChTime() {
        return chTime;
    }

    public void setChTime(String chTime) {
        this.chTime = chTime;
    }

    public String getChDesc() {
        return chDesc;
    }

    public void setChDesc(String chDesc) {
        this.chDesc = chDesc;
    }

    public String getChImage() {
        return chImage;
    }

    public void setChImage(String chImage) {
        this.chImage = chImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
