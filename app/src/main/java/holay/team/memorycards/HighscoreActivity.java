package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

public class HighscoreActivity extends AppCompatActivity {

    private Context context;
    private TextView highscore1;
    private TextView highscore2;
    private TextView highscore3;
    private Button debugButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Wire components
        highscore1 = (TextView) findViewById(R.id.high1);
        highscore2 = (TextView) findViewById(R.id.high2);
        highscore3 = (TextView) findViewById(R.id.high3);
        debugButton = (Button) findViewById(R.id.debugButton);

        loadScores();

        debugButton.setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                Intent i = MainActivity.newIntent(HighscoreActivity.this);
                startActivity(i);
            }
        });

    }

    private void loadScores() {
        try {
            // Open up the resource file.
            InputStream is = getAssets().open("highscores.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Iterate through the file and try to get the three top scores
            String[] scores = {"000 - N/A","000 - N/A","000 - N/A"};
            String next = br.readLine();
            int i = 0;
            while (next != null && scores[2].equals("000 - N/A")) {
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

        } catch (Exception e) {
            e.printStackTrace();
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
