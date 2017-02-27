package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;

// A class used to load the 3 highest scores and display them.
public class HighscoreActivity extends AppCompatActivity {

    private TextView highscore1;
    private TextView highscore2;
    private TextView highscore3;
    private Button debugButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().hide();
        }
        // Wire components
        highscore1 = (TextView) findViewById(R.id.high1);
        highscore2 = (TextView) findViewById(R.id.high2);
        highscore3 = (TextView) findViewById(R.id.high3);
        debugButton = (Button) findViewById(R.id.debugButton);

        loadScores();

        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MainActivity.newIntent(HighscoreActivity.this);
                startActivity(i);
            }
        });
    }

    private void loadScores() {
        try {
            // Open up the resource file.
            FileReader fr = new FileReader(getFilesDir().toString() + "/highscores.txt");
            BufferedReader br = new BufferedReader(fr);

            // Iterate through the file and try to get the three top scores
            String[] scores = {"000 - N/A", "000 - N/A", "000 - N/A"};
            String next = br.readLine();
            int i = 0;
            while (next != null && i != 3) {
                String[] parts = next.split(":");
                // Check to see if the line read was valid.
                if (parts.length == 2 && Integer.parseInt(parts[0]) > -1) {
                    scores[i++] = parts[0] + " - " + parts[1];
                }
                next = br.readLine();
            }
            //Sort and set the scores as new text on screen
            Arrays.sort(scores, Collections.reverseOrder());
            highscore1.setText(scores[0]);
            highscore2.setText(scores[1]);
            highscore3.setText(scores[2]);

            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to Bind ancestral navigation up to the keyboard input 'up arrow'
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    /*
 *  Method accepts a activity where it is coming from and sends them to this activity
 */
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, HighscoreActivity.class);
        return i;
    }
}
