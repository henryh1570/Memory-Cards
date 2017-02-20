package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, PlayActivity.class);
        return i;
    }
}
