package hhc.br.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "rat.QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mBtTrue;
    private Button mBtFalse;
    private ImageButton mBtNext;
    private ImageButton mBtPrev;
    private TextView mTvQuestion;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, -1);
        }

        mBtTrue = findViewById(R.id.bt_true);
        mBtFalse = findViewById(R.id.bt_false);
        mBtNext = findViewById(R.id.bt_next);
        mBtPrev = findViewById(R.id.bt_prev);
        mTvQuestion = findViewById(R.id.tv_question);

        updateQuestion();

        mBtTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mBtFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
        mBtPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevQuestion();
            }
        });
        mTvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "rat: onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void checkAnswer(boolean userPressedTrue){
        if(mQuestionBank[mCurrentIndex].isAnswerTrue() == userPressedTrue){
            Toast.makeText(this, getString(R.string.right_toast), Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, getString(R.string.wrong_toast), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void updateQuestion(){
        mCurrentIndex = (++mCurrentIndex) % mQuestionBank.length;
        mTvQuestion.setText(mQuestionBank[mCurrentIndex].getTextResId());
    }
    private void prevQuestion(){
        mCurrentIndex = --mCurrentIndex >= 0 ? mCurrentIndex : mQuestionBank.length - 1;
        mTvQuestion.setText(mQuestionBank[mCurrentIndex].getTextResId());
    }
}