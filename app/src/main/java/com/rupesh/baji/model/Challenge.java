package com.rupesh.baji.model;

public class Challenge {
    private String _id;
    private String chType;
    private User chBy;
    private User chAcceptedby;
    private String chGame;
    private String chAmt;
    private String chDate;
    private String chTime;
    private String chDesc;
    private String chImage;
    private String status;

    private User WonBy;
    private User confirmationSendBy;
    private String confirmation;
    private String proofingImage;

    public Challenge(String chType, User chBy, String chGame, String chAmt, String chDate, String chTime, String chDesc, String chImage, String status, String confirmation) {
        this.chType = chType;
        this.chBy = chBy;
        this.chGame = chGame;
        this.chAmt = chAmt;
        this.chDate = chDate;
        this.chTime = chTime;
        this.chDesc = chDesc;
        this.chImage = chImage;
        this.status = status;
        this.confirmation = confirmation;
    }

    public Challenge(User chAcceptedby, String status) {
        this.chAcceptedby = chAcceptedby;
        this.status = status;
    }

    public Challenge(User wonBy, User confirmationSendBy, String confirmation, String proofingImage) {
        WonBy = wonBy;
        this.confirmationSendBy = confirmationSendBy;
        this.confirmation = confirmation;
        this.proofingImage = proofingImage;
    }

    public Challenge(String _id){
        this._id = _id;
    }

    // GS
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getChType() {
        return chType;
    }

    public void setChType(String chType) {
        this.chType = chType;
    }

    public User getChBy() {
        return chBy;
    }

    public void setChBy(User chBy) {
        this.chBy = chBy;
    }

    public User getChAcceptedby() {
        return chAcceptedby;
    }

    public void setChAcceptedby(User chAcceptedby) {
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

    public User getWonBy() {
        return WonBy;
    }

    public void setWonBy(User wonBy) {
        WonBy = wonBy;
    }

    public User getConfirmationSendBy() {
        return confirmationSendBy;
    }

    public void setConfirmationSendBy(User confirmationSendBy) {
        this.confirmationSendBy = confirmationSendBy;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getProofingImage() {
        return proofingImage;
    }

    public void setProofingImage(String proofingImage) {
        this.proofingImage = proofingImage;
    }
}
