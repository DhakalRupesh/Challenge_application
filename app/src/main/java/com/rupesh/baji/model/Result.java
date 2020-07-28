package com.rupesh.baji.model;

public class Result {

//    private String _id;
//    private String WonBy;
//    private String ChallengeWon;
//    private String ChBy;
//    private String cHacceptedBy;
//    private String confirmationSendBy;
//    private String confirmation;
//    private String proofing;

    private String _id;
    private User WonBy;
    private Challenge ChallengeWon;
    private User ChBy;
    private User cHacceptedBy;
    private User confirmationSendBy;
    private String confirmation;
    private String proofing;

    public Result(User wonBy, Challenge challengeWon, User chBy, User cHacceptedBy, User confirmationSendBy, String confirmation, String proofing) {
        WonBy = wonBy;
        ChallengeWon = challengeWon;
        ChBy = chBy;
        this.cHacceptedBy = cHacceptedBy;
        this.confirmationSendBy = confirmationSendBy;
        this.confirmation = confirmation;
        this.proofing = proofing;
    }

    public Result(User wonBy, User cHacceptedBy, User confirmationSendBy, String confirmation, String proofing) {
        WonBy = wonBy;
        this.cHacceptedBy = cHacceptedBy;
        this.confirmationSendBy = confirmationSendBy;
        this.confirmation = confirmation;
        this.proofing = proofing;
    }

    public Result(User wonBy, Challenge challengeWon, User cHacceptedBy, User confirmationSendBy, String confirmation, String proofing) {
        WonBy = wonBy;
        ChallengeWon = challengeWon;
        this.cHacceptedBy = cHacceptedBy;
        this.confirmationSendBy = confirmationSendBy;
        this.confirmation = confirmation;
        this.proofing = proofing;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getWonBy() {
        return WonBy;
    }

    public void setWonBy(User wonBy) {
        WonBy = wonBy;
    }

    public Challenge getChallengeWon() {
        return ChallengeWon;
    }

    public void setChallengeWon(Challenge challengeWon) {
        ChallengeWon = challengeWon;
    }

    public User getChBy() {
        return ChBy;
    }

    public void setChBy(User chBy) {
        ChBy = chBy;
    }

    public User getcHacceptedBy() {
        return cHacceptedBy;
    }

    public void setcHacceptedBy(User cHacceptedBy) {
        this.cHacceptedBy = cHacceptedBy;
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

    public String getProofing() {
        return proofing;
    }

    public void setProofing(String proofing) {
        this.proofing = proofing;
    }

    //    public Result(String wonBy, String challengeWon, String chBy, String cHacceptedBy, String confirmationSendBy, String confirmation, String proofing) {
//        WonBy = wonBy;
//        ChallengeWon = challengeWon;
//        ChBy = chBy;
//        this.cHacceptedBy = cHacceptedBy;
//        this.confirmationSendBy = confirmationSendBy;
//        this.confirmation = confirmation;
//        this.proofing = proofing;
//    }
//
//    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }
//
//    public String getWonBy() {
//        return WonBy;
//    }
//
//    public void setWonBy(String wonBy) {
//        WonBy = wonBy;
//    }
//
//    public String getChallengeWon() {
//        return ChallengeWon;
//    }
//
//    public void setChallengeWon(String challengeWon) {
//        ChallengeWon = challengeWon;
//    }
//
//    public String getChBy() {
//        return ChBy;
//    }
//
//    public void setChBy(String chBy) {
//        ChBy = chBy;
//    }
//
//    public String getcHacceptedBy() {
//        return cHacceptedBy;
//    }
//
//    public void setcHacceptedBy(String cHacceptedBy) {
//        this.cHacceptedBy = cHacceptedBy;
//    }
//
//    public String getConfirmationSendBy() {
//        return confirmationSendBy;
//    }
//
//    public void setConfirmationSendBy(String confirmationSendBy) {
//        this.confirmationSendBy = confirmationSendBy;
//    }
//
//    public String getConfirmation() {
//        return confirmation;
//    }
//
//    public void setConfirmation(String confirmation) {
//        this.confirmation = confirmation;
//    }
//
//    public String getProofing() {
//        return proofing;
//    }
//
//    public void setProofing(String proofing) {
//        this.proofing = proofing;
//    }
}
