package cz.uhk.fim.cellar.diplang.lessons;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class Lesson3ActivityTest {
    @Rule
    public ActivityScenarioRule mScenarioRule = new ActivityScenarioRule<>(Lesson3Activity.class);

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
    public void moveNext() {
    }

    @Test
    public void movePrevious() {
    }
}