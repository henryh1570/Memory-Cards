package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    private TextView exampleTextView;
    private Button debugButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Wire components
        exampleTextView = (TextView) findViewById(R.id.example);
        debugButton = (Button) findViewById(R.id.debugButton);

        debugButton.setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                Intent i = MainActivity.newIntent(HighscoreActivity.this);
                startActivity(i);
            }
        });

    }

    /*
 *  Method accepts a activity where it is coming from and sends them to this activity
 */
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, HighscoreActivity.class);
        return i;
    }
}
