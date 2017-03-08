/***************************************************************
 * file: PlayActivity.java
 * author: Luis Cortes, Oscar Hernandez, Henry Hu, Y-Uyen La, and An Le
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Swing Project v1.0
 * date last modified: 2/5/2017
 *
 * purpose: This class just holds the image button and the face up image of the card.
 *
 ****************************************************************/

package holay.team.memorycards;

import android.widget.ImageButton;

// A class to contain an imageButton and its face up image string.
public class Card {

    public int image;
    private ImageButton button;

    //method: onCreate
    //purpose: This method stores the face-up image resource and the corresponding button for it
    public Card(int i, ImageButton b) {
        image = i;
        button = b;
    }

    //method: onCreate
    //purpose: This method just returns the button for the card.
    public ImageButton getButton() {
        return button;
    }
}
