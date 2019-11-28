package com.example.infinimood;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.infinimood.model.Mood;
import com.example.infinimood.view.AddEditMoodActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AddEditMoodActivityTest {

    private FirebaseControllerMock mockController;

    public AddEditMoodActivityTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        mockController = FirebaseControllerMock.install();
    }

    @Rule
    public ActivityTestRule<AddEditMoodActivity> rule = new ActivityTestRule<>(
            AddEditMoodActivity.class,
            true,
            true
    );

    private Solo solo;

    @Before
    public void setUp() {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }

    @After
    public void tearDown() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        solo.finishOpenedActivities();
        mockController = FirebaseControllerMock.install();
    }

    @Test
    public void testAddSimpleMoodEvent() {
        solo.assertCurrentActivity("Expected add edit mode screen to show", AddEditMoodActivity.class);
        solo.clickOnButton(solo.getString(R.string.submit));
        solo.waitForText(solo.getString(R.string.add_mood_successfully_saved));
        final Mood mood = mockController.addMoodEventToDbCallArgsMood.get(0);
        assertEquals(0, mockController.addImageToDbCallCount, 0);
        assertEquals(1, mockController.addMoodEventToDBCallCount, 0);
        assertEquals("", mood.getReason());
    }

}
