package holay.team.memorycards;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlayActivity extends AppCompatActivity {

    // Global Declarations
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;
    private Card card9;
    private Card card10;
    private Card card11;
    private Card card12;
    private Card card13;
    private Card card14;
    private Card card15;
    private Card card16;
    private Card card17;
    private Card card18;
    private Card card19;
    private Card card20;
    private Card cardPtr1 = null;
    private Card cardPtr2 = null;
    private int totalCards;
    private int remainingCards;
    private int scoreNumber = 0;
    private TextView scoreText;
    private Button endButton;
    private Button tryButton;
    private Button giveUpButton;
    private Button saveButton;
    private ImageButton soundButton;
    private ArrayList<Card> listOfCards;
    private ArrayList<Integer> listOfSolvedCards;
    private MediaPlayer bgmPlayer = new MediaPlayer();
    private AlertDialog.Builder adb;


    public PlayActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().hide();
        }

        // Instantiations here
        Intent intent = getIntent();
        totalCards = intent.getExtras().getInt("num");
        remainingCards = totalCards;
        instantiate(false);

        // Start new game by going back to main menu
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopBgm();
                Intent i = MainActivity.newIntent(PlayActivity.this);
                startActivity(i);
            }
        });

        // Reveal all listOfCards but disallow final scoreText
        giveUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Card c : listOfCards) {
                    c.getButton().setImageResource(c.image);
                    c.getButton().setEnabled(false);
                }
                cardPtr1 = null;
                cardPtr2 = null;
                giveUpButton.setEnabled(false);
                tryButton.setEnabled(false);
            }
        });

        // Try again to reset a mismatched pair
        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardPtr1 != null & cardPtr2 != null) {
                    cardPtr1.getButton().setEnabled(true);
                    cardPtr1.getButton().setImageResource(R.drawable.card);
                    cardPtr1 = null;
                    cardPtr2.getButton().setEnabled(true);
                    cardPtr2.getButton().setImageResource(R.drawable.card);
                    cardPtr2 = null;
                }
            }
        });

        // Play or mute the background music
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(bgmPlayer);
            }
        });

        // Prompt a dialog box and allow entering a alphabetic name to save scoreText.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to customize a dialog box for entering name and save later.
                final EditText editText = new EditText(PlayActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                adb = new AlertDialog.Builder(PlayActivity.this);
                adb.setTitle("Save Score");
                adb
                        .setMessage("Enter your Name!")
                        .setCancelable(false)
                        .setView(editText)
                        .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Enter and return to selection screen
                                char[] arr = editText.getText().toString().toLowerCase().toCharArray();
                                if (arr.length == 0) {
                                    return;
                                }
                                for (char c : arr) {
                                    // Disallow non-characters
                                    if (c < 'a' || c > 'z') {
                                        return;
                                    }
                                }

                                stopBgm();
                                saveScore(scoreNumber, editText.getText().toString());
                                PlayActivity.this.finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = adb.create();
                alertDialog.show();
            }
        });
    }

    // Store all values as Key-Pair to save on rotation
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("totalCards", totalCards);
        savedInstanceState.putInt("remainingCards", remainingCards);
        savedInstanceState.putInt("scoreText", scoreNumber);

        // Store card images.
        for (int i = 0; i < listOfCards.size(); i++) {
            Card card = listOfCards.get(i);
            String key = ("card" + (i + 1) + "Image");
            int value = card.image;
            savedInstanceState.putInt(key, value);
        }

        // Store the solved card images.
        for (int i = 0; i < listOfSolvedCards.size(); i++) {
            savedInstanceState.putInt("solved"+i, listOfSolvedCards.get(i));
        }

        savedInstanceState.putInt("solvedSize", listOfSolvedCards.size());

        // Store card pointers' id, unless they were null
        if (cardPtr1 != null) {
            savedInstanceState.putInt("selectedCard", cardPtr1.getButton().getId());
            if (cardPtr2 != null) {
                savedInstanceState.putInt("selected2Card", cardPtr2.getButton().getId());
            } else {
                savedInstanceState.putInt("selected2Card", -1);
            }
        } else {
            savedInstanceState.putInt("selectedCard", -1);
            savedInstanceState.putInt("selected2Card", -1);
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    // Get all values saved and resetup on rotate
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        totalCards = savedInstanceState.getInt("totalCards");
        remainingCards = savedInstanceState.getInt("remainingCards");
        scoreNumber = savedInstanceState.getInt("scoreText");
        scoreText.setText("Score: " + scoreNumber);
        instantiate(true);

        // Set the card pointers on rotate.
        int selectedNum = savedInstanceState.getInt("selectedCard");
        int selected2Num = savedInstanceState.getInt("selected2Card");
        cardPtr1 = setCardPtr(selectedNum);
        cardPtr2 = setCardPtr(selected2Num);

        int solvedSize = savedInstanceState.getInt("solvedSize");

        // Put back the solutions into arraylist
        for (int i = 0; i < solvedSize; i++) {
            listOfSolvedCards.add(savedInstanceState.getInt("solved"+i));
        }

        // Set enabled status and images of listOfCards
        for (int i = 0; i < listOfCards.size(); i++) {
            Card card = listOfCards.get(i);
            ImageButton imageButton = card.getButton();
            card.image = savedInstanceState.getInt("card" + (i + 1) + "Image");

            // Redraw the listOfCards and set enable status
            // If the card is selected
            if ((cardPtr1 != null && imageButton.equals(cardPtr1.getButton())) ||
                    (cardPtr2 != null && imageButton.equals(cardPtr2.getButton()))) {
                imageButton.setEnabled(false);
                imageButton.setImageResource(card.image);
            } else if (listOfSolvedCards.contains(card.image)) {
                // Check if the card was solved, flip face up
                imageButton.setEnabled(false);
                imageButton.setImageResource(card.image);
            } else {
                // Otherwise the card is face-down
                imageButton.setEnabled(true);
                imageButton.setImageResource(R.drawable.card);
            }
        }
    }

    // Match the pointer with its card id
    public Card setCardPtr(int num) {
        Card ptr = null;
        int i = 0;
        if (num != -1) {
            while(ptr == null && i < listOfCards.size()) {
                if (num == listOfCards.get(i).getButton().getId()) {
                    ptr = listOfCards.get(i);
                } else {
                    i++;
                }
            }
        }
        return ptr;
    }

    // Sets up wiring for buttons and textviews
    // Used in onCreate and rotation resume
    public void instantiate(boolean onRotate) {
        prepareAudio("music.mp3", bgmPlayer);
        scoreText = (TextView) findViewById(R.id.score);
        endButton = (Button) findViewById(R.id.debugButton);
        tryButton = (Button) findViewById(R.id.tryButton);
        giveUpButton = (Button) findViewById(R.id.giveUpButton);
        soundButton = (ImageButton) findViewById(R.id.soundButton);
        saveButton = (Button) findViewById(R.id.saveScoreButton);
        card1 = new Card(0, (ImageButton) findViewById(R.id.card1));
        card2 = new Card(0, (ImageButton) findViewById(R.id.card2));
        card3 = new Card(0, (ImageButton) findViewById(R.id.card3));
        card4 = new Card(0, (ImageButton) findViewById(R.id.card4));
        card5 = new Card(0, (ImageButton) findViewById(R.id.card5));
        card6 = new Card(0, (ImageButton) findViewById(R.id.card6));
        card7 = new Card(0, (ImageButton) findViewById(R.id.card7));
        card8 = new Card(0, (ImageButton) findViewById(R.id.card8));
        card9 = new Card(0, (ImageButton) findViewById(R.id.card9));
        card10 = new Card(0, (ImageButton) findViewById(R.id.card10));
        card11 = new Card(0, (ImageButton) findViewById(R.id.card11));
        card12 = new Card(0, (ImageButton) findViewById(R.id.card12));
        card13 = new Card(0, (ImageButton) findViewById(R.id.card13));
        card14 = new Card(0, (ImageButton) findViewById(R.id.card14));
        card15 = new Card(0, (ImageButton) findViewById(R.id.card15));
        card16 = new Card(0, (ImageButton) findViewById(R.id.card16));
        card17 = new Card(0, (ImageButton) findViewById(R.id.card17));
        card18 = new Card(0, (ImageButton) findViewById(R.id.card18));
        card19 = new Card(0, (ImageButton) findViewById(R.id.card19));
        card20 = new Card(0, (ImageButton) findViewById(R.id.card20));

        listOfSolvedCards = new ArrayList<Integer>();

        if (remainingCards != 0) {
            saveButton.setVisibility(View.INVISIBLE);
            saveButton.setEnabled(false);
        } else {
            saveButton.setVisibility(View.VISIBLE);
            saveButton.setEnabled(true);
        }

        // If first time launch, randomize cards.
        if (onRotate == false) {
            setCards();
        } else {
            populateCardsList();
        }
    }

    // Listener for all 20 cards
    public void cardOnClick(View view) {
        Card card = null;
        switch (view.getId()) {
            case R.id.card1:
                card = card1;
                break;
            case R.id.card2:
                card = card2;
                break;
            case R.id.card3:
                card = card3;
                break;
            case R.id.card4:
                card = card4;
                break;
            case R.id.card5:
                card = card5;
                break;
            case R.id.card6:
                card = card6;
                break;
            case R.id.card7:
                card = card7;
                break;
            case R.id.card8:
                card = card8;
                break;
            case R.id.card9:
                card = card9;
                break;
            case R.id.card10:
                card = card10;
                break;
            case R.id.card11:
                card = card11;
                break;
            case R.id.card12:
                card = card12;
                break;
            case R.id.card13:
                card = card13;
                break;
            case R.id.card14:
                card = card14;
                break;
            case R.id.card15:
                card = card15;
                break;
            case R.id.card16:
                card = card16;
                break;
            case R.id.card17:
                card = card17;
                break;
            case R.id.card18:
                card = card18;
                break;
            case R.id.card19:
                card = card19;
                break;
            case R.id.card20:
                card = card20;
                break;
        }

        ImageButton cardButton = card.getButton();
        int sound = R.raw.click;

        // Check if 0 or 1 listOfCards have been selected
        if (cardButton.isEnabled() && cardPtr2 == null) {
            cardButton.setImageResource(card.image);
            // Picking card 2
            if (cardPtr1 != null) {
                cardPtr2 = card;
                // A match
                if (cardPtr2.image == cardPtr1.image) {
                    listOfSolvedCards.add(cardPtr1.image);
                    cardPtr1 = null;
                    cardPtr2 = null;
                    scoreNumber += 2;
                    scoreText.setText("Score: " + scoreNumber);
                    sound = R.raw.success;
                    checkIfGameIsOver();
                } else {
                    // A failed match
                    if (scoreNumber != 0) {
                        scoreNumber += -1;
                        scoreText.setText("Score: " + scoreNumber);
                    }
                    sound = R.raw.wrong;
                }
            } else {
                // Otherwise this is picking card 1
                cardPtr1 = card;
            }
            cardButton.setEnabled(false);
            playSFX(sound);
        }

    }

    // Sound found in RAW folder. Creates a new MediaPlayer each call.
    public void playSFX(int sound) {
        MediaPlayer mp = MediaPlayer.create(PlayActivity.this, sound);
        mp.start();
    }

    // Preserve 2 old high scores, add 1 new one and write to file
    private void saveScore(int score, String name) {
        FileOutputStream outputStream;
        try {
            // Grab the 2 previous highest scores.
            FileReader fr = new FileReader(getFilesDir().toString() + "/highscores.txt");
            BufferedReader br = new BufferedReader(fr);
            String[] highest = {"000:N/A", "000:N/A", "" + (score + ":" + name)};
            String dummy = br.readLine();
            int i = 0;
            while (dummy != null && i != 2) {
                highest[i] = dummy;
                i++;
                dummy = br.readLine();
            }

            // Write in the previous 2 highest scores plus the new one.
            outputStream = openFileOutput("highscores.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            Arrays.sort(highest, Collections.reverseOrder());
            for (String str : highest) {
                osw.write(str);
                osw.write("\n");
            }
            fr.close();
            br.close();
            osw.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            firstTimeSave(name);
            saveScore(score, name);
        } catch (Exception e) {
            Log.d("EXCEPTION : ", "Save file could not be created!");
        }
    }

    // Initialize the save file only once. Empty file.
    public void firstTimeSave(String name) {
        try {
            FileOutputStream outputStream;
            outputStream = openFileOutput("highscores.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write("");
            osw.close();
            outputStream.close();
        } catch (Exception e) {
            Log.d("EXCEPTION : ", e.toString());
        }
    }

    // Load audio file from assests folder and play
    public void playAudio(MediaPlayer mp) {
        if (mp.isPlaying()) {
            mp.pause();
        } else {
            mp.start();
        }
    }

    // Necessary to prevent mediaplayer error codes
    public void prepareAudio(String audioFileName, MediaPlayer mp) {
        try {
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd(audioFileName);
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mp.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Assign randomly 10 images to the pairs of 20
    public void setCards() {
        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.drawable.meowth);
        images.add(R.drawable.mewtwo);
        images.add(R.drawable.dragonair);
        images.add(R.drawable.dragonite);
        images.add(R.drawable.goldeen);
        images.add(R.drawable.lugia);
        images.add(R.drawable.articuno);
        images.add(R.drawable.zapdos);
        images.add(R.drawable.poliwhirl);
        images.add(R.drawable.gyrados);
        Collections.shuffle(images);

        populateCardsList();

        Collections.shuffle(listOfCards);

        int counter = 0;
        // Set individual card pairs to have remember face up card.
        for (int i = 0; i < listOfCards.size(); ) {
            listOfCards.get(i++).image = images.get(counter);
            listOfCards.get(i++).image = images.get(counter++);
        }
        // Set the listOfCards face down
        for (Card c : listOfCards) {
            c.getButton().setImageResource(R.drawable.card);
        }
    }

    public void populateCardsList() {
        listOfCards = new ArrayList<Card>();
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);
        listOfCards.add(card4);
        listOfCards.add(card5);
        listOfCards.add(card6);
        listOfCards.add(card7);
        listOfCards.add(card8);
        listOfCards.add(card9);
        listOfCards.add(card10);
        listOfCards.add(card11);
        listOfCards.add(card12);
        listOfCards.add(card13);
        listOfCards.add(card14);
        listOfCards.add(card15);
        listOfCards.add(card16);
        listOfCards.add(card17);
        listOfCards.add(card18);
        listOfCards.add(card19);
        listOfCards.add(card20);
        // Remove the unused listOfCards
        while (listOfCards.size() != totalCards) {
            listOfCards.get(listOfCards.size() - 1).getButton().setVisibility(View.GONE);
            listOfCards.remove(listOfCards.size() - 1);
        }
    }

    // Check if the game is over when a match is made.
    private void checkIfGameIsOver() {
        remainingCards -= 2;
        if (remainingCards <= 0) {
            saveButton.setVisibility(View.VISIBLE);
            saveButton.setEnabled(true);
            tryButton.setEnabled(false);
            giveUpButton.setEnabled(false);
        }
    }

    public void stopBgm() {
        if (bgmPlayer.isPlaying()) {
            bgmPlayer.stop();
        }
        bgmPlayer = null;
    }

    // Method to Bind ancestral navigation up to the keyboard input 'up arrow'
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                stopBgm();
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, PlayActivity.class);
        return i;
    }
}
