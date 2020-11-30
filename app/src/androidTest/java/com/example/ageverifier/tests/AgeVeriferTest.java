package com.example.ageverifier.tests;

import android.content.Intent;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.ageverifier.MainActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.ageverifier.R;

import java.util.concurrent.TimeoutException;

@RunWith(AndroidJUnit4.class)
public class AgeVeriferTest {

    @Rule
    private ActivityTestRule<MainActivity> activityTestRule  = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity;

    @After
    public void tearDown() {
        activityTestRule.getActivity().finish();
    }

    @Given("^I have a MainActivity")
    public void I_have_a_MainActivity(){
        activityTestRule.launchActivity(new Intent());
        mainActivity = activityTestRule.getActivity();
    }

    @When("^I input age (\\S+)$")
    public void I_input_age(final String age) throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.editTextNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextNumber)).perform(typeText(age));
        onView(withId(R.id.button)).perform(click());
    }

    @Then("^I should (true|false) age error$")
    public void I_should_see_error_on_the_editTextView(final boolean accepted) throws InterruptedException {
        Thread.sleep(5000);
        if(accepted) {
            onView(allOf(withId(R.id.statusTextView))).check(matches(withText("You can drink \uD83C\uDF89")));
        } else {
            onView(allOf(withId(R.id.statusTextView))).check(matches(withText("You can't drink yet :(")));
        }

    }

    /**
     * This is BUG which should be fixed in BackEnd
     * @throws InterruptedException
     */
    @Then("^I should see HTTP age error$")
    public void I_should_see_HTTP_age_error() throws InterruptedException {
        onView(allOf(withId(R.id.statusTextView))).check(matches(withText("HTTP Exception 500 Internal Server Error")));
    }




}
