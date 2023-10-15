package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questiontextView;

    private Question[] questions=new Question[]{
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        questiontextView=findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(true);
                if(currentIndex==questions.length-1 )
                {
                    Toast.makeText(view.getContext(), "Liczba poprawnych odpowiedzi: " + howmany, Toast.LENGTH_SHORT).show();
                    howmany=0;
                }
            }

        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
                if(currentIndex==questions.length-1 )
                {
                    Toast.makeText(view.getContext(), "Liczba poprawnych odpowiedzi: " + howmany, Toast.LENGTH_SHORT).show();
                    howmany=0;
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex=(currentIndex+1)%questions.length;
                setNextQuestion();

            }
        });
        setNextQuestion();

    }
    private int currentIndex=0, howmany = 0;
    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer=questions[currentIndex].isTrueAnswer();
        int resultMessageId=0;
        if(userAnswer==correctAnswer){
            {resultMessageId=R.string.correct_answer;
                howmany++;
            }
        }else{
            resultMessageId=R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();

    }
    private void setNextQuestion()
    {
        questiontextView.setText(questions[currentIndex].getQuestionId());
    }
}