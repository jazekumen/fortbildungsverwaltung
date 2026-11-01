package fortbildungsverwaltung;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
/** Frische Testinstallation mit unverändertem Startkonto verwenden. */
@RunWith(AndroidJUnit4.class)
public class LoginUiTest {
    @Test public void normalRoleCannotChangePermissions() {
        try (ActivityScenario<FortbildungsverwaltungHS> scenario=ActivityScenario.launch(FortbildungsverwaltungHS.class)) {
            onView(withId(R.id.name)).perform(typeText("admin"));
            onView(withId(R.id.password)).perform(typeText("aaAA11&&aa"),closeSoftKeyboard());
            onView(withId(R.id.normal)).perform(scrollTo(),click());
            onView(withId(R.id.ok)).perform(scrollTo(),click());
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
            onView(withText("Sachbearbeiter bearbeiten")).perform(click());
            onData(equalTo("admin")).inAdapterView(withId(R.id.list)).perform(click());
            onView(withId(R.id.admin)).check(matches(not(isEnabled())));
            onView(withId(R.id.normal)).check(matches(not(isEnabled())));
        }
    }
}
