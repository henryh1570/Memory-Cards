package holay.team.memorycards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wire components
        mPlayButton = (Button) findViewById(R.id.play_button);

        // Switch over to SelectionActivity when clicked
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                Intent i = SelectionActivity.newIntent(MainActivity.this);
                startActivity(i);
            }
        });

    }
}