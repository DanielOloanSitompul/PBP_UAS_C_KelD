package com.danieloloan.pbp_uts.splash;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.danieloloan.pbp_uts.Activity.AskTicket;
import com.danieloloan.pbp_uts.Activity.MainActivity;
import com.danieloloan.pbp_uts.Activity.Tambah_tiket;
import com.danieloloan.pbp_uts.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestTambahTiket {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void testTambahTiket() {
        onView(isRoot()).perform(waitFor(5000));
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_ticket), withContentDescription("Ask Ticket"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                4),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        onView(isRoot()).perform(waitFor(5000));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.add_button),
                        childAtPosition(
                                allOf(withId(R.id.consLayout),
                                        childAtPosition(
                                                withId(R.id.layout_ticket),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add_Ticket), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        onView(isRoot()).perform(waitFor(2000));

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.addNama),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_number_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.addNama),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_number_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Budi"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.add_Ticket), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        onView(isRoot()).perform(waitFor(2000));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.addAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_name_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.addAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_name_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("JAlan setia"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.add_Ticket), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton4.perform(click());

        onView(isRoot()).perform(waitFor(2000));

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.addEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_email_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.addEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_email_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("ansheboerdan@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.add_Ticket), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());

        onView(isRoot()).perform(waitFor(2000));


        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.addQuestion),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_question_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.addQuestion),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_question_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("Apakah mobil yang dipinjam dalam kondisi prima?"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.add_Ticket), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton6.perform(click());

        onView(isRoot()).perform(waitFor(2000));
    }

    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }
            @Override
            public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }
            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
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
