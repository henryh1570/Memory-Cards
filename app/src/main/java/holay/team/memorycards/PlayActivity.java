package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends AppCompatActivity {

    private int numOfCards;
    private Button debugButton;

    public PlayActivity () {
        super();
    }

    public PlayActivity(int numOfCards) {
        super();
        this.numOfCards = numOfCards;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        debugButton = (Button) findViewById(R.id.debugButton);

        debugButton.setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                Intent i = MainActivity.newIntent(PlayActivity.this);
                startActivity(i);
            }
        });
    }

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, PlayActivity.class);
        return i;
    }
}
