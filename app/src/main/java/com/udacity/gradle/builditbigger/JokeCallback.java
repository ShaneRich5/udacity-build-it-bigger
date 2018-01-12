package com.udacity.gradle.builditbigger;

import android.support.annotation.NonNull;

interface JokeCallback {
    void onJokeLoaded(@NonNull String joke);
}
