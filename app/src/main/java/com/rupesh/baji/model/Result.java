package com.rupesh.baji.model;

public class Result {

    private String WonBy;
    private String ChallengeWon;
    private String OpponentOfChallenge;
    private String confirmationSendBy;

//    private User WonBy;
//    private Challenge ChallengeWon;
//    private User OpponentOfChallenge;
//    private User confirmationSendBy;
    private String confirmation;
    private String proofing;

//    public Result(User wonBy, Challenge challengeWon, User opponentOfChallenge, User confirmationSendBy, String confirmation, String proofing) {
//        WonBy = wonBy;
//        ChallengeWon = challengeWon;
//        OpponentOfChallenge = opponentOfChallenge;
//        this.confirmationSendBy = confirmationSendBy;
//        this.confirmation = confirmation;
//        this.proofing = proofing;
//    }

//    public User getWonBy() {
//        return WonBy;
//    }
//
//    public void setWonBy(User wonBy) {
//        WonBy = wonBy;
//    }
//
//    public Challenge getChallengeWon() {
//        return ChallengeWon;
//    }
//
//    public void setChallengeWon(Challenge challengeWon) {
//        ChallengeWon = challengeWon;
//    }
//
//    public User getOpponentOfChallenge() {
//        return OpponentOfChallenge;
//    }
//
//    public void setOpponentOfChallenge(User opponentOfChallenge) {
//        OpponentOfChallenge = opponentOfChallenge;
//    }
//
//    public User getConfirmationSendBy() {
//        return confirmationSendBy;
//    }
//
//    public void setConfirmationSendBy(User confirmationSendBy) {
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

//    string session


    public Result(String wonBy, String challengeWon, String opponentOfChallenge, String confirmationSendBy, String confirmation, String proofing) {
        WonBy = wonBy;
        ChallengeWon = challengeWon;
        OpponentOfChallenge = opponentOfChallenge;
        this.confirmationSendBy = confirmationSendBy;
        this.confirmation = confirmation;
        this.proofing = proofing;
    }

    public String getWonBy() {
        return WonBy;
    }

    public void setWonBy(String wonBy) {
        WonBy = wonBy;
    }

    public String getChallengeWon() {
        return ChallengeWon;
    }

    public void setChallengeWon(String challengeWon) {
        ChallengeWon = challengeWon;
    }

    public String getOpponentOfChallenge() {
        return OpponentOfChallenge;
    }

    public void setOpponentOfChallenge(String opponentOfChallenge) {
        OpponentOfChallenge = opponentOfChallenge;
    }

    public String getConfirmationSendBy() {
        return confirmationSendBy;
    }

    public void setConfirmationSendBy(String confirmationSendBy) {
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
}
