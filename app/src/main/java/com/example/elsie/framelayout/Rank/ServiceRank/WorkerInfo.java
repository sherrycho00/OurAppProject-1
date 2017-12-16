package com.example.elsie.framelayout.Rank.ServiceRank;

import java.io.Serializable;

/**
 * Created by Elsie on 2017/12/13.
 */

public class WorkerInfo implements Serializable {

    private String   workerImg;
    private int    window;
    private String    workerID;
    private String    workerName;
    private int   score;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkerImg() {
        return workerImg;
    }

    public void setWorkerImg(String workerImg) {
        this.workerImg = workerImg;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
