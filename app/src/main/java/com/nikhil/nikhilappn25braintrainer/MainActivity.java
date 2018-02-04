package com.nikhil.nikhilappn25braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    TextView resultTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView pointsTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;



    public void playAgain(View view){

        score = 0;
        numberOfQuestions = 0;

        timerTextView .setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();


        new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();


    }


    public void generateQuestion(){



        Random rand = new Random();
        int a = rand.nextInt(21);  //first number
        int b = rand.nextInt(21);  //second number

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4); // 0 to 3

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i<4; i++){

            if (i==locationOfCorrectAnswer)
                answers.add(a+b); //correct answer

            else {

                incorrectAnswer = rand.nextInt(41);



                while (incorrectAnswer == (a+b) )  //to prevent the random num equal to the correct answer
                    incorrectAnswer = rand.nextInt(41);

                answers.add(incorrectAnswer);
            }

        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));




    }


    public void chooseAnswer(View view){

        //Log.i("Tag", (String) view.getTag());

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) { //if correct answer
            //Log.i("Correct", "c");

            score++;
            resultTextView.setText("Correct");

        }
        else {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();


    }



    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton)); //it needs to be sent a view (any view)


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        resultTextView = (TextView)  findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);




    }
}
