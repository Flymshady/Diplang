package cz.uhk.fim.cellar.diplang.notifications;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;


public class NotificationSettingsActivityTest {

    @Rule
    public ActivityScenarioRule mScenarioRule = new ActivityScenarioRule<>(NavigationActivity.class);

    private ActivityScenario mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mScenarioRule.getScenario();
    }

    @Test
    public void testLaunch(){

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onClick() {
    }

    @Test
    public void popTimePicker() {
    }
}