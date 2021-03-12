package hhc.br.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
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

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                int cx = showAnswerBt.getWidth() / 2;
                int cy = showAnswerBt.getHeight() / 2;
                float rad = showAnswerBt.getWidth();
                Animator anim = ViewAnimationUtils
                        .createCircularReveal(showAnswerBt, cx, cy, rad, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        showAnswerBt.setVisibility(View.INVISIBLE);
                    }
                });
                anim.start();
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}