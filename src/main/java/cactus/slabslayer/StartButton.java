package cactus.slabslayer;

import processing.core.PVector;

public class StartButton extends Button {

    /**
     * Constructs a new button.
     *
     * @param window as a Window
     */
    public StartButton(Window window) {
        super(window);
    }

    /**
     * Constructs a new button.
     *
     * @param text as a String
     * @param width as an int
     * @param height as an int
     * @param localPos as a PVector
     * @param window as a Window
     */
    public StartButton(String text, int width, int height, PVector localPos, Window window) {
        super(text, width, height, localPos, window);
    }

    /**
     * Executes the button's given function if clicked.
     */
    @Override
    public void execute() {
        Game.getGameInstance().currState = Game.State.PLAYING;
    }

}
