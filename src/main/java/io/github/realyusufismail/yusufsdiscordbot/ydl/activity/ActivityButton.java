package io.github.realyusufismail.yusufsdiscordbot.ydl.activity;

public interface ActivityButton {

    /**
     * Gets the text shown on the button. (1- 32 characters)
     * 
     * @return The text shown on the button.
     */
    String getLabel();

    /**
     * Gets the url which is opened when the button is clicked. (1- 512 characters)
     * 
     * @return The url which is opened when the button is clicked.
     */
    String getUrl();
}
