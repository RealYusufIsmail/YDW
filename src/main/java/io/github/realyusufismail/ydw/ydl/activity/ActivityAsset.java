package io.github.realyusufismail.ydw.ydl.activity;

import java.util.Optional;

public interface ActivityAsset {

    Optional<String> getLargeImage();

    Optional<String> getLargeText();

    Optional<String> getSmallImage();

    Optional<String> getSmallText();
}
