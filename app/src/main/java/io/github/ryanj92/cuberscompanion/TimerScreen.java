package io.github.ryanj92.cuberscompanion;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TimerScreen extends AppCompatActivity {

    ImageButton mLeftTimerButton;
    ImageButton mRightTimerButton;
    TextView mTimeDisplay;
    TextView mScrambleDisplay;

    Handler handler;

    long StartTime = 0L;
    long MillisecondTime = 0L;
    int Seconds,Minutes,MilliSeconds;

    boolean timerRunning = false;

    RandomMoveScrambler testScrambler = new RandomMoveScrambler("555");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);

        mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);
        mScrambleDisplay = (TextView) findViewById(R.id.scrambleDisplay);
        mLeftTimerButton = (ImageButton) findViewById(R.id.leftTimerButton);
        mRightTimerButton = (ImageButton) findViewById(R.id.rightTimerButton);

        handler = new Handler();

        mScrambleDisplay.setText(testScrambler.generateScramble());

        mLeftTimerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!timerRunning) {

                    MillisecondTime = 0L ;
                    StartTime = 0L ;
                    Seconds = 0 ;
                    Minutes = 0 ;
                    MilliSeconds = 0 ;

                    mTimeDisplay.setText(R.string.sampleTime);

                    StartTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    timerRunning = true;

                } else {

                    handler.removeCallbacks(runnable);
                    timerRunning = false;
                    mScrambleDisplay.setText(testScrambler.generateScramble());

                }

            }

        });

    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            Seconds = (int) (MillisecondTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (MillisecondTime % 1000);

            mTimeDisplay.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}
