package com.example.eggeuret;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    Integer chosenTime = 0; //TODO move
    TextView timerText;
    Button timerBtn;
    ImageButton soft, medium, hard;
    EggTimer eggTimer;

    public void onButtonEggSelectedClicked(View view) { //TODO check this
        timerText = findViewById(R.id.timer);
        timerBtn = findViewById(R.id.timerButton);
        switch (view.getId()) {
            case R.id.softBoiledButton:
                prepareTimer(60000); //TODO change time to 5 min, when testing done
                timerText.setText("05:00");
                break;
            case R.id.mediumBoiledButton:
                prepareTimer(420000);
                timerText.setText("07:00");
                break;
            case R.id.hardBoiledButton:
                prepareTimer(600000);
                timerText.setText("10:00");
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    private void prepareTimer(int time) {
        chosenTime = time;
        timerBtn.setEnabled(true);
    }
    public void onTimerBtnClicked(final View view) {
        eggTimer = new EggTimer(chosenTime);
        if (!eggTimer.getTimerStarted()) { //Timer start
            eggTimer.setTimerStarted(true);

            changeEggBtnState();
        }
        else { //Timer stop
            eggTimer.setTimerStarted(false);
            timerBtn.setText("Start");
        }

        eggTimer.start();



    }

    public String computeTime() {
        int minute = chosenTime / 60000;
        int second = (chosenTime % 60000) / 1000;

        return getTimerTime(minute, second);
    }

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

    //Stops the timer by resetting TODO do something about this
    private void onTimerStop() {
        if (chosenTime <= 0) {
            changeEggBtnState();
        }

        timerBtn.setEnabled(false);

    }

    //Changes state on the egg buttons TODO check this
    private void changeEggBtnState() {
        soft = findViewById(R.id.softBoiledButton);
        medium = findViewById(R.id.mediumBoiledButton);
        hard = findViewById(R.id.hardBoiledButton);

        if (soft.isEnabled()) {
            soft.setEnabled(false);
            soft.setImageAlpha(50);
            medium.setEnabled(false);
            medium.setImageAlpha(50);
            hard.setEnabled(false);
            hard.setImageAlpha(50);
        }
        else {
            soft.setEnabled(true);
            soft.setImageAlpha(1000);
            medium.setEnabled(true);
            medium.setImageAlpha(1000);
            hard.setEnabled(true);
            hard.setImageAlpha(1000);
        }
    }
}

