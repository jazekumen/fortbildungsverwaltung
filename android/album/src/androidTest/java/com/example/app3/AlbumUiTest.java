package com.example.app3;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
@RunWith(AndroidJUnit4.class)
public class AlbumUiTest {
    @Test public void bindingApplyCancelAndRecreation() {
        Album.albumDataSet();
        try (ActivityScenario<MainActivity> scenario=ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.composer)).check(matches(not(isEnabled())));
            onView(withId(R.id.classical)).perform(scrollTo(),click());
            onView(withId(R.id.composer)).check(matches(isEnabled()));
            onView(withId(R.id.title)).perform(scrollTo(),replaceText("Entwurf"),closeSoftKeyboard());
            scenario.recreate();
            onView(withId(R.id.title)).check(matches(withText("Entwurf")));
            onView(withId(R.id.cancel)).perform(scrollTo(),click());
            onView(withId(R.id.title)).check(matches(withText("HQ")));
            onView(withId(R.id.title)).perform(scrollTo(),replaceText("Neuer Titel"),closeSoftKeyboard());
            onView(withId(R.id.apply)).perform(scrollTo(),click());
            onData(equalTo("Neuer Titel")).inAdapterView(withId(R.id.albums)).check(matches(isDisplayed()));
            onData(anything()).inAdapterView(withId(R.id.albums)).atPosition(3).perform(click());
            onView(withId(R.id.composer)).check(matches(withText("Sibelius")));
        }
    }
}
