package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectionActivity extends AppCompatActivity {

    private Button mButton4, mButton6, mButton8, mButton10, mButton12;
    private Button mButton14, mButton16, mButton18, mButton20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);


        // Wire buttons
        mButton4 = (Button) findViewById(R.id.four_button);
        mButton6 = (Button) findViewById(R.id.six_button);
        mButton8 = (Button) findViewById(R.id.eight_button);
        mButton10 = (Button) findViewById(R.id.ten_button);
        mButton12 = (Button) findViewById(R.id.twelve_button);
        mButton14 = (Button) findViewById(R.id.fourteen_button);
        mButton16 = (Button) findViewById(R.id.sixteen_button);
        mButton18 = (Button) findViewById(R.id.eighteen_button);
        mButton20 = (Button) findViewById(R.id.twenty_button);

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.four_text, Toast.LENGTH_SHORT).show();

            }
        });

        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.six_text, Toast.LENGTH_SHORT).show();

            }
        });

        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.eight_text, Toast.LENGTH_SHORT).show();
            }
        });

        mButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.ten_text, Toast.LENGTH_SHORT).show();
            }
        });

        mButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.twelve_text, Toast.LENGTH_SHORT).show();
            }
        });

        mButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.fourteen_text, Toast.LENGTH_SHORT).show();
            }
        });

        mButton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.sixteen_text, Toast.LENGTH_SHORT).show();
            }
        });

        mButton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.eighteen_text, Toast.LENGTH_SHORT).show();
            }
        });

        mButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, R.string.twenty_text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*
     *  Method accepts a activity where it is coming from and sends them to this activity
     */
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, SelectionActivity.class);
        return i;
    }
}
