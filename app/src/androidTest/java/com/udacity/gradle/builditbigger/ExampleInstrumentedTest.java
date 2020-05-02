package com.udacity.gradle.builditbigger;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.intent.Intents.getIntents;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.IsNot.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private IdlingResource mIdlingResource;
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Before
    public void setUp()  {
        //  ActivityScenarioRule<MainActivity> o = new ActivityScenarioRule<MainActivity>(MainActivity.class);
        //o.getScenario();
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                mIdlingResource = activity.getIdlingResource();
                IdlingRegistry.getInstance().register(mIdlingResource);
                activity.runEndPoint();
            }
        });
    }

    @Test
    public void checkAsynResult() throws Throwable {
        //intending(Matchers.not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
        List<Intent> receivedIntent = getIntents();
        ViewMatchers.assertThat(receivedIntent.get(0).getStringExtra("JOKE"), not(isEmptyString()));

    }
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
