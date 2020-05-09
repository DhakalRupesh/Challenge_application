package com.rupesh.baji.model;

public class Result {
    private User WonBy;
    private Challenge ChallengeWon;
    private String confirmation;
    private String proofing;

    public Result(User wonBy, Challenge challengeWon, String confirmation, String proofing) {
        WonBy = wonBy;
        ChallengeWon = challengeWon;
        this.confirmation = confirmation;
        this.proofing = proofing;
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
