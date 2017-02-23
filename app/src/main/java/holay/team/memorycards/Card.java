package holay.team.memorycards;

import android.widget.ImageButton;

public class Card {

    public int image;
    private ImageButton button;

    public Card(int i, ImageButton b) {
        image = i;
        button = b;
    }
    public ImageButton getButton() {
        return button;
    }
}
