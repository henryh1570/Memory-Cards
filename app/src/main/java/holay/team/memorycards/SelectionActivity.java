package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity {

    private Button mButton4, mButton6, mButton8, mButton10, mButton12;
    private Button mButton14, mButton16, mButton18, mButton20;
    private Button continueButton;
    private TextView title;
    private int numOfCards;

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
        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setEnabled(false);

        title = (TextView) findViewById(R.id.title_text);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = PlayActivity.newIntent(getApplicationContext());
                i.putExtra("num", numOfCards);
                startActivity(i);
            }
        });
    }

    // Modularized onclick for game selection buttons
    public void buttonOnClick(View view) {
        Button b = null;
        switch (view.getId()) {
            case R.id.four_button:
                b = mButton4;
                break;
            case R.id.six_button:
                b = mButton6;
                break;
            case R.id.eight_button:
                b = mButton8;
                break;
            case R.id.ten_button:
                b = mButton10;
                break;
            case R.id.twelve_button:
                b = mButton12;
                break;
            case R.id.fourteen_button:
                b = mButton14;
                break;
            case R.id.sixteen_button:
                b = mButton16;
                break;
            case R.id.eighteen_button:
                b = mButton18;
                break;
            case R.id.twenty_button:
                b = mButton20;
                break;
        }

        numOfCards = Integer.parseInt(b.getText().toString());
        continueButton.setEnabled(true);
        title.setText(numOfCards + " Card Game");
    }

    /*
     *  Method accepts a activity where it is coming from and sends them to this activity
     */
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, SelectionActivity.class);
        return i;
    }
}
