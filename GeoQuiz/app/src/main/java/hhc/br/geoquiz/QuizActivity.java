package hhc.br.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "rat.QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQ_CODE_CHEAT = 0;

    private TextView mTvQuestion;
    private boolean mIsCheater;

    private final Question[] mQuestionBank = new Question[]{
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

        Button btCheat = findViewById(R.id.bt_cheat);
        Button btTrue = findViewById(R.id.bt_true);
        Button btFalse = findViewById(R.id.bt_false);
        ImageButton btNext = findViewById(R.id.bt_next);
        ImageButton btPrev = findViewById(R.id.bt_prev);
        mTvQuestion = findViewById(R.id.tv_question);

        updateQuestion();

        btCheat.setOnClickListener(v -> {
            Intent intent = CheatActivity.newIntent(
                    QuizActivity.this,
                    mQuestionBank[mCurrentIndex].isAnswerTrue());
                    //new Intent(QuizActivity.this, CheatActivity.class);
            startActivityForResult(intent, REQ_CODE_CHEAT);
        });
        btTrue.setOnClickListener(v -> checkAnswer(true));
        btFalse.setOnClickListener(v -> checkAnswer(false));
        btNext.setOnClickListener(v -> updateQuestion());
        btPrev.setOnClickListener(v -> prevQuestion());
        mTvQuestion.setOnClickListener(v -> updateQuestion());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "rat: onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != RESULT_OK){
            return;
        }
        if(requestCode == REQ_CODE_CHEAT){
            if(data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
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