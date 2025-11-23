package com.project.onlinevoting.dto;

public class VoteResultDTO {

    private String voterName;
    private String candidateName;
    private String time;

    public VoteResultDTO() {}

    public VoteResultDTO(String voterName, String candidateName, String time) {
        this.voterName = voterName;
        this.candidateName = candidateName;
        this.time = time;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
