/***************************************************************
 * file: PlayActivity.java
 * author: Luis Cortes, Oscar Hernandez, Henry Hu, Y-Uyen La, and An Le
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Swing Project v1.0
 * date last modified: 2/5/2017
 *
 * purpose: This class allows you select the number of cards you want in a game.
 *
 ****************************************************************/

package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity {

    private Button mButton4, mButton6, mButton8, mButton10, mButton12;
    private Button mButton14, mButton16, mButton18, mButton20;
    private Button continueButton;
    private Button returnButton;
    private TextView title;
    private ImageView mSelectionView;
    private int numOfCards;


    //method: onCreate
    //purpose: This is like the main method and this is where it calls all of the other functions. It's where gameplay happens. It also sets the onClick
    //listeners so the functions happen.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().hide();
        }

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
        returnButton = (Button) findViewById(R.id.return_button);
        continueButton = (Button) findViewById(R.id.continue_button);
        mSelectionView = (ImageView) findViewById(R.id.selection_view);
        continueButton.setEnabled(false);

//        title = (TextView) findViewById(R.id.title_text);

        // Start play activity
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = PlayActivity.newIntent(getApplicationContext());
                i.putExtra("num", numOfCards);
                startActivity(i);
            }
        });

        // Return to Menu Screen
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MainActivity.newIntent(getApplicationContext());
                startActivity(i);
            }
        });
    }


    //method: onCreate
    //purpose: Store all values as Key-Pair to save on rotation
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("num", numOfCards);
        savedInstanceState.putBoolean("isEnabled", continueButton.isEnabled());
        super.onSaveInstanceState(savedInstanceState);
    }
    //method: onCreate
    //purpose: Retrieve all values needed to store the rotated state. This saves the info for when you rotate, so that everything doesn't get destroyed.
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numOfCards = savedInstanceState.getInt("num");
        title.setText(numOfCards + " Card Game");
        continueButton.setEnabled(savedInstanceState.getBoolean("isEnabled"));
    }
    //method: onCreate
    //purposes: Modularized onclick for game selection buttons
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
        showCardChoice();

//        title.setText(numOfCards + " Card Game");
    }

    //method: showCardChoice()
    //purpose: Represent the number chosen with an image of the number
    public void showCardChoice() {
        switch(numOfCards) {
            case 4:
                mSelectionView.setImageResource(R.drawable.four);
                break;
            case 6:
                mSelectionView.setImageResource(R.drawable.six);
                break;
            case 8:
                mSelectionView.setImageResource(R.drawable.eight);
                break;
            case 10:
                mSelectionView.setImageResource(R.drawable.ten);
                break;
            case 12:
                mSelectionView.setImageResource(R.drawable.twelve);
                break;
            case 14:
                mSelectionView.setImageResource(R.drawable.fourteen);
                break;
            case 16:
                mSelectionView.setImageResource(R.drawable.sixteen);
                break;
            case 18:
                mSelectionView.setImageResource(R.drawable.eighteen);
                break;
            case 20:
                mSelectionView.setImageResource(R.drawable.twenty);
                break;
        }
    }
    //method: onCreate
    //purpose: Method accepts a activity where it is coming from and sends them to this activity

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, SelectionActivity.class);
        return i;
    }

    //method: onCreate
    //purpose: Bind ancestral navigation up to the keyboard input 'up arrow'
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }
}
