/***************************************************************
 * file: PlayActivity.java
 * author: Luis Cortes, Oscar Hernandez, Henry Hu, Y-Uyen La, and An Le
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Swing Project v1.0
 * date last modified: 2/5/2017
 *
 * purpose: This class handles the main screen. It allows you to see the high scores, or start a game.
 *
 ****************************************************************/

package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;
    private Button mHighscoresButton;

    //method: onCreate
    //purpose: This method takes care of initializations and onclick listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Wire components
        mPlayButton = (Button) findViewById(R.id.play_button);
        mHighscoresButton = (Button) findViewById(R.id.highScore_button);

        // Switch over to SelectionActivity when clicked
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = SelectionActivity.newIntent(MainActivity.this);
                startActivity(i);
            }
        });

        // Switch over to SelectionActivity when clicked
        mHighscoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = HighscoreActivity.newIntent(MainActivity.this);
                startActivity(i);
            }
        });
    }

    //method: onCreate
    //purpose: This method allows you to change to a new activity from another one.
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, MainActivity.class);
        return i;
    }
}