package com.example.eggeuret;

import android.os.CountDownTimer;
import android.os.Handler;

import java.io.Console;
import java.util.List;
import java.util.Timer;

public class EggTimer extends Thread {

    private Boolean timerStarted = false;

    public Boolean getTimerStarted() {
        return timerStarted;
    }

    public void setTimerStarted(Boolean newTimerStarted) {
        this.timerStarted = newTimerStarted;
    }

    private Integer chosenTime;

    public int getChosenTime() {
        return chosenTime;
    }

    public void setChosenTime(int newChosenTime) {
        this.chosenTime = newChosenTime;
    }

    String timerText;

    public String getTimerText() {
        return timerText;
    }

    public void setTimerText(String newTimerText) {
        this.timerText = newTimerText;
    }

    List<EggTimerListener> listeners;

    public EggTimer(int timer) {
        setChosenTime(timer);
    }

    @Override
    public void run() {

        
        if (timerStarted) {

            timerText = computeTime();

            if (chosenTime <= 0) {
                onTimerStop();
            } else {


            }
        } else {
            onTimerStop();
        }
    }



    //Computes the time left on the timer
    //TODO check return type
    public String computeTime() {
        int minute = chosenTime / 60000;
        int second = (chosenTime % 60000) / 1000;

        return getTimerTime(minute, second);
    }

    //When the timer stops
    public void onTimerStop() {
        //TODO make this method
        timerStarted = false;
        chosenTime = 0;
        timerText = "00:00";
    }

    //Gets the timer time in mm:ss format
    private String getTimerTime(int minute, int second) {
        String time = "";
        if (second < 10) {
            time = "0" + second;
        } else {
            time = "" + second;
        }

        if (minute < 10) {
            return ("0" + minute + ":").concat(time);
        } else {
            return ("" + minute + ":").concat(time);
        }
    }

    public void addListener(EggTimerListener listener) {
        listeners.add(listener);
    }


    public void removeListener(EggTimerListener listener) {
        listeners.remove(listener);
    }
}



