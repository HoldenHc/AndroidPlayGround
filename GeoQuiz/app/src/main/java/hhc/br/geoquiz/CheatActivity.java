package hhc.br.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "hhc.br.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_IS_SHOWN =
            "hhc.br.geoquiz.answer_is_shown";

    public static Intent newIntent(Context pkgContext, boolean answerIsTrue) {
        Intent intent = new Intent(pkgContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        boolean answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        Button showAnswerBt = findViewById(R.id.bt_show_answer);
        TextView answerTV = findViewById(R.id.tv_answer);

        showAnswerBt.setOnClickListener(v -> {
            answerTV.setText(answerIsTrue ? R.string.bt_true : R.string.bt_false);
            setAnswerShownResult(true);
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}