package holay.team.memorycards;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class PlayActivity extends AppCompatActivity {

    private int numOfCards;
    private TextView score;
    private int scoreNumber = 0;
    private Button endButton;
    private Button tryButton;
    private Button giveUpButton;
    private ImageButton soundButton;
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
    private ArrayList<Card> cards;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    public PlayActivity () {
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
        card1 = new Card(0,(ImageButton) findViewById(R.id.card1));
        card2 = new Card(0,(ImageButton) findViewById(R.id.card2));
        card3 = new Card(0,(ImageButton) findViewById(R.id.card3));
        card4 = new Card(0,(ImageButton) findViewById(R.id.card4));
        card5 = new Card(0,(ImageButton) findViewById(R.id.card5));
        card6 = new Card(0,(ImageButton) findViewById(R.id.card6));
        card7 = new Card(0,(ImageButton) findViewById(R.id.card7));
        card8 = new Card(0,(ImageButton) findViewById(R.id.card8));
        card9 = new Card(0,(ImageButton) findViewById(R.id.card9));
        card10 = new Card(0,(ImageButton) findViewById(R.id.card10));
        card11 = new Card(0,(ImageButton) findViewById(R.id.card11));
        card12 = new Card(0,(ImageButton) findViewById(R.id.card12));
        card13 = new Card(0,(ImageButton) findViewById(R.id.card13));
        card14 = new Card(0,(ImageButton) findViewById(R.id.card14));
        card15 = new Card(0,(ImageButton) findViewById(R.id.card15));
        card16 = new Card(0,(ImageButton) findViewById(R.id.card16));
        card17 = new Card(0,(ImageButton) findViewById(R.id.card17));
        card18 = new Card(0,(ImageButton) findViewById(R.id.card18));
        card19 = new Card(0,(ImageButton) findViewById(R.id.card19));
        card20 = new Card(0,(ImageButton) findViewById(R.id.card20));
        setCards();

        endButton.setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer = null;
                Intent i = MainActivity.newIntent(PlayActivity.this);
                startActivity(i);
            }
        });

        giveUpButton.setOnClickListener(new View.OnClickListener() {
            @ Override
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

        tryButton.setOnClickListener(new View.OnClickListener() {
            @ Override
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

        soundButton.setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                playAudio("music.mp3");
            }
        });

        card1.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card1.getButton().isEnabled() && selected2 == null) {
                    card1.getButton().setImageResource(card1.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card1.image) {
                            card1.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card1;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card1;
                        card1.getButton().setEnabled(false);
                    }
                }
            }
        });

        card2.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card2.getButton().isEnabled() && selected2 == null) {
                    card2.getButton().setImageResource(card2.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card2.image) {
                            card2.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card2;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card2;
                        card2.getButton().setEnabled(false);
                    }
                }
            }
        });

        card3.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card3.getButton().isEnabled() && selected2 == null) {
                    card3.getButton().setImageResource(card3.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card3.image) {
                            card3.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card3;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card3;
                        card3.getButton().setEnabled(false);
                    }
                }
            }
        });

        card4.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card4.getButton().isEnabled() && selected2 == null) {
                    card4.getButton().setImageResource(card4.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card4.image) {
                            card4.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card4;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card4;
                        card4.getButton().setEnabled(false);
                    }
                }
            }
        });

        card5.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card5.getButton().isEnabled() && selected2 == null) {
                    card5.getButton().setImageResource(card5.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card5.image) {
                            card5.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card5;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card5;
                        card5.getButton().setEnabled(false);
                    }
                }
            }
        });

        card6.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card6.getButton().isEnabled() && selected2 == null) {
                    card6.getButton().setImageResource(card6.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card6.image) {
                            card6.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card6;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card6;
                        card6.getButton().setEnabled(false);
                    }
                }
            }
        });

        card7.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card7.getButton().isEnabled() && selected2 == null) {
                    card7.getButton().setImageResource(card7.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card7.image) {
                            card7.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card7;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card7;
                        card7.getButton().setEnabled(false);
                    }
                }
            }
        });

        card8.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card8.getButton().isEnabled() && selected2 == null) {
                    card8.getButton().setImageResource(card8.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card8.image) {
                            card8.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card8;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card8;
                        card8.getButton().setEnabled(false);
                    }
                }
            }
        });

        card9.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card9.getButton().isEnabled() && selected2 == null) {
                    card9.getButton().setImageResource(card9.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card9.image) {
                            card9.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card9;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card9;
                        card9.getButton().setEnabled(false);
                    }
                }
            }
        });

        card10.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card10.getButton().isEnabled() && selected2 == null) {
                    card10.getButton().setImageResource(card10.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card10.image) {
                            card10.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card10;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card10;
                        card10.getButton().setEnabled(false);
                    }
                }
            }
        });

        card11.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card11.getButton().isEnabled() && selected2 == null) {
                    card11.getButton().setImageResource(card11.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card11.image) {
                            card11.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card11;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card11;
                        card11.getButton().setEnabled(false);
                    }
                }
            }
        });

        card12.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card12.getButton().isEnabled() && selected2 == null) {
                    card12.getButton().setImageResource(card12.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card12.image) {
                            card12.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card12;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card12;
                        card12.getButton().setEnabled(false);
                    }
                }
            }
        });

        card13.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card13.getButton().isEnabled() && selected2 == null) {
                    card13.getButton().setImageResource(card13.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card13.image) {
                            card13.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card13;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card13;
                        card13.getButton().setEnabled(false);
                    }
                }
            }
        });

        card14.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card14.getButton().isEnabled() && selected2 == null) {
                    card14.getButton().setImageResource(card14.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card14.image) {
                            card14.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card14;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card14;
                        card14.getButton().setEnabled(false);
                    }
                }
            }
        });

        card15.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card15.getButton().isEnabled() && selected2 == null) {
                    card15.getButton().setImageResource(card15.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card15.image) {
                            card15.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card15;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card15;
                        card15.getButton().setEnabled(false);
                    }
                }
            }
        });

        card16.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card16.getButton().isEnabled() && selected2 == null) {
                    card16.getButton().setImageResource(card16.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card16.image) {
                            card16.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card16;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card16;
                        card16.getButton().setEnabled(false);
                    }
                }
            }
        });

        card17.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card17.getButton().isEnabled() && selected2 == null) {
                    card17.getButton().setImageResource(card17.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card1.image) {
                            card17.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card17;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card17;
                        card17.getButton().setEnabled(false);
                    }
                }
            }
        });

        card18.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card18.getButton().isEnabled() && selected2 == null) {
                    card18.getButton().setImageResource(card18.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card18.image) {
                            card18.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card18;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card18;
                        card18.getButton().setEnabled(false);
                    }
                }
            }
        });

        card19.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card19.getButton().isEnabled() && selected2 == null) {
                    card19.getButton().setImageResource(card19.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card19.image) {
                            card19.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card19;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card19;
                        card19.getButton().setEnabled(false);
                    }
                }
            }
        });

        card20.getButton().setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                // Check if 0 or 1 cards have been selected
                if (card20.getButton().isEnabled() && selected2 == null) {
                    card20.getButton().setImageResource(card20.image);
                    // 2 Cards will be selected.
                    if (selected != null) {
                        // A match will disable both cards
                        if (selected.image == card20.image) {
                            card20.getButton().setEnabled(false);
                            selected = null;
                            scoreNumber += 2;
                            score.setText("Score: " + scoreNumber);
                        } else {
                            if (scoreNumber != 0) {
                                scoreNumber += -1;
                                score.setText("Score: " + scoreNumber);
                            }
                            selected2 = card20;
                        }
                    } else {
                        // Otherwise this is the first pick
                        selected = card20;
                        card20.getButton().setEnabled(false);
                    }
                }
            }
        });
    }

    public void playAudio(String audioFileName) {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else if (mediaPlayer.getCurrentPosition() > 1) {
            mediaPlayer.start();
        } else {
            try {
                mediaPlayer.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd(audioFileName);
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setCards() {
        // 10 Images here
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
        for (int i = 0; i < cards.size();) {
            cards.get(i++).image = images.get(counter);
            cards.get(i++).image = images.get(counter++);
        }

        for (Card c : cards) {
            c.getButton().setImageResource(R.drawable.card);
        }

    }

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, PlayActivity.class);
        return i;
    }
}
