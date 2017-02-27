package holay.team.memorycards;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlayActivity extends AppCompatActivity {

    // Global Declarations
    private int numOfCards;
    private int scoreNumber = 0;
    private TextView score;
    private Button endButton;
    private Button tryButton;
    private Button giveUpButton;
    private Button saveButton;
    private ImageButton soundButton;
    private ArrayList<Card> cards;
    private AudioPlayer bgmPlayer = new AudioPlayer(new MediaPlayer());
    private AlertDialog.Builder adb;
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
    private Card selected = null;
    private Card selected2 = null;

    public PlayActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        numOfCards = intent.getExtras().getInt("num");

        score = (TextView) findViewById(R.id.score);
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
        saveButton.setVisibility(View.INVISIBLE);
        saveButton.setEnabled(false);
        setCards();

        // Start new game by going back to main menu
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bgmPlayer.isPlaying()) {
                    bgmPlayer.stop();
                }
                bgmPlayer = null;
                Intent i = MainActivity.newIntent(PlayActivity.this);
                startActivity(i);
            }
        });

        // Reveal all cards but disallow final score
        giveUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Card c : cards) {
                    c.getButton().setImageResource(c.image);
                    c.getButton().setEnabled(false);
                }
                selected = null;
                selected2 = null;
                giveUpButton.setEnabled(false);
                tryButton.setEnabled(false);
            }
        });

        // Try again to reset a mismatched pair
        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != null & selected2 != null) {
                    selected.getButton().setEnabled(true);
                    selected.getButton().setImageResource(R.drawable.card);
                    selected = null;
                    selected2.getButton().setEnabled(true);
                    selected2.getButton().setImageResource(R.drawable.card);
                    selected2 = null;
                }
            }
        });

        // Play or mute the background music
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio("music.mp3", bgmPlayer);
            }
        });

        // Prompt a dialog box and allow entering a alphabetic name to save score.
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

                                if (bgmPlayer.isPlaying()) {
                                    bgmPlayer.stop();
                                }
                                bgmPlayer = null;
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

    // Modularized onClickListener for all 20 buttons
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

        // Check if 0 or 1 cards have been selected
        if (cardButton.isEnabled() && selected2 == null) {
            // Play sound effect
            playAudio("click.wav", new AudioPlayer(new MediaPlayer()));
            cardButton.setImageResource(card.image);
            // 2 Cards will be selected.
            if (selected != null) {
                // A match will disable both cards
                if (selected.image == card.image) {
                    cardButton.setEnabled(false);
                    selected = null;
                    scoreNumber += 2;
                    score.setText("Score: " + scoreNumber);
                    checkIfGameIsOver();
                } else {
                    if (scoreNumber != 0) {
                        scoreNumber += -1;
                        score.setText("Score: " + scoreNumber);
                    }
                    selected2 = card;
                }
            } else {
                // Otherwise this is the first pick
                selected = card;
                cardButton.setEnabled(false);
            }
        }
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
        } catch (Exception e) {
            Log.d("EXCEPTION : ", e.toString());
        }
    }

    // Load audio file from assests folder and play
    public void playAudio(String audioFileName, AudioPlayer audioPlayer) {
        if(audioPlayer.isPrepared() == false) {
            prepareAudio(audioFileName, audioPlayer);
            audioPlayer.setPrepared();
        } else {
            MediaPlayer mediaPlayer = audioPlayer.getMediaPlayer();
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else if (mediaPlayer.getCurrentPosition() > 1) {
                mediaPlayer.start();
            }
        }
    }

    public void prepareAudio(String audioFileName, AudioPlayer audioPlayer) {
        try {
            MediaPlayer mediaPlayer = audioPlayer.getMediaPlayer();
            mediaPlayer.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd(audioFileName);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
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

        cards = new ArrayList<Card>();

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
        cards.add(card10);
        cards.add(card11);
        cards.add(card12);
        cards.add(card13);
        cards.add(card14);
        cards.add(card15);
        cards.add(card16);
        cards.add(card17);
        cards.add(card18);
        cards.add(card19);
        cards.add(card20);

        // Remove the unused cards
        while (cards.size() != numOfCards) {
            cards.get(cards.size() - 1).getButton().setVisibility(View.GONE);
            cards.remove(cards.size() - 1);
        }

        Collections.shuffle(cards);

        // Set face down card image.
        for (Card c : cards) {
            c.getButton().setImageResource(R.drawable.card);
        }

        int counter = 0;
        // Set individual card pairs to have remember face up card.
        for (int i = 0; i < cards.size(); ) {
            cards.get(i++).image = images.get(counter);
            cards.get(i++).image = images.get(counter++);
        }
        // Set the cards face down
        for (Card c : cards) {
            c.getButton().setImageResource(R.drawable.card);
        }
    }

    // Check if the game is over when a match is made.
    private void checkIfGameIsOver() {
        numOfCards -= 2;
        if (numOfCards == 0) {
            saveButton.setVisibility(View.VISIBLE);
            saveButton.setEnabled(true);
            tryButton.setEnabled(false);
            endButton.setEnabled(false);
        }
    }

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, PlayActivity.class);
        return i;
    }
}
