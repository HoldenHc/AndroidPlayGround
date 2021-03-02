package hhc.br.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mBtTrue;
    private Button mBtFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mBtTrue = findViewById(R.id.bt_true);
        mBtFalse = findViewById(R.id.bt_false);

        mBtTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this,
                        "true pressed !",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mBtFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this,
                        "false pressed !",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}