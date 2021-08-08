package cz.uhk.fim.cellar.diplang;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TheoryLesson2ActivityTest {
    @Rule
    public ActivityScenarioRule mScenarioRule = new ActivityScenarioRule<>(TheoryLesson2Activity.class);

    private ActivityScenario mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mScenarioRule.getScenario();
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
}