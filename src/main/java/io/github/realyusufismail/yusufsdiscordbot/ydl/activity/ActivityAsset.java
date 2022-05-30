package io.github.realyusufismail.yusufsdiscordbot.ydl.activity;

import java.util.Optional;

public interface ActivityAsset {

    Optional<String> getLargeImage();

    Optional<String> getLargeText();

    Optional<String> getSmallImage();

    Optional<String> getSmallText();
}
