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

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, MainActivity.class);
        return i;
    }
}