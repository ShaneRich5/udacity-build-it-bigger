package com.udacity.gradle.builditbigger;


import android.support.annotation.NonNull;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertNotNull;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {
    public static final int TEST_TIMEOUT_LIMIT_MILLISECONDS = 3000;
    public static final int TASK_TIMEOUT_LIMIT_SECONDS = 20;

    private JokeAsyncTask jokeTask;

    @Before
    public void createJokeTask() {
        JokeCallback callback = new JokeCallback() {
            @Override
            public void onJokeLoaded(@NonNull String joke) {

            }
        };
        jokeTask = new JokeAsyncTask(callback);
    }

    @Test(timeout = TEST_TIMEOUT_LIMIT_MILLISECONDS)
    public void testJokeResponse() throws InterruptedException, ExecutionException, TimeoutException {
        jokeTask.execute();
        String joke = jokeTask.get(TASK_TIMEOUT_LIMIT_SECONDS, TimeUnit.SECONDS);
        assertNotNull(joke);
    }
}
