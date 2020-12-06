package com.danieloloan.pbp_uts.splash;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.danieloloan.pbp_uts.Activity.AskTicket;
import com.danieloloan.pbp_uts.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest_9656 {

    @Rule
    public ActivityTestRule<AskTicket> mActivityTestRule = new ActivityTestRule<>(AskTicket.class);

    @Test
    public void loginTest_9656() {
        ViewInteraction textInputEditText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.emailLogim),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("ansheboe"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.passwordLogim),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.loginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());


        ViewInteraction textInputEditText3 = onView(
                allOf(withText("ansheboe"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.emailLogim),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText(""));

        ViewInteraction textInputEditText4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.emailLogim),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.loginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.emailLogim),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("ansheboerdan@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withText("test"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.passwordLogim),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(replaceText(""));

        ViewInteraction textInputEditText7 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.passwordLogim),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText7.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.loginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textInputEditText8 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.passwordLogim),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText8.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.passwordLogim),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText9.perform(replaceText("test123"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.loginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
