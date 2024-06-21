// Generated by view binder compiler. Do not edit!
package com.example.emoticare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.emoticare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView articlesRecyclerView;

  @NonNull
  public final CalendarView calendarView;

  @NonNull
  public final ImageButton darkMode;

  @NonNull
  public final TextView greetings;

  @NonNull
  public final LinearLayout headerHome;

  @NonNull
  public final ImageButton history;

  @NonNull
  public final TextView home;

  @NonNull
  public final TextView question;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView articlesRecyclerView, @NonNull CalendarView calendarView,
      @NonNull ImageButton darkMode, @NonNull TextView greetings, @NonNull LinearLayout headerHome,
      @NonNull ImageButton history, @NonNull TextView home, @NonNull TextView question) {
    this.rootView = rootView;
    this.articlesRecyclerView = articlesRecyclerView;
    this.calendarView = calendarView;
    this.darkMode = darkMode;
    this.greetings = greetings;
    this.headerHome = headerHome;
    this.history = history;
    this.home = home;
    this.question = question;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.articlesRecyclerView;
      RecyclerView articlesRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (articlesRecyclerView == null) {
        break missingId;
      }

      id = R.id.calendarView;
      CalendarView calendarView = ViewBindings.findChildViewById(rootView, id);
      if (calendarView == null) {
        break missingId;
      }

      id = R.id.darkMode;
      ImageButton darkMode = ViewBindings.findChildViewById(rootView, id);
      if (darkMode == null) {
        break missingId;
      }

      id = R.id.greetings;
      TextView greetings = ViewBindings.findChildViewById(rootView, id);
      if (greetings == null) {
        break missingId;
      }

      id = R.id.headerHome;
      LinearLayout headerHome = ViewBindings.findChildViewById(rootView, id);
      if (headerHome == null) {
        break missingId;
      }

      id = R.id.history;
      ImageButton history = ViewBindings.findChildViewById(rootView, id);
      if (history == null) {
        break missingId;
      }

      id = R.id.home;
      TextView home = ViewBindings.findChildViewById(rootView, id);
      if (home == null) {
        break missingId;
      }

      id = R.id.question;
      TextView question = ViewBindings.findChildViewById(rootView, id);
      if (question == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, articlesRecyclerView,
          calendarView, darkMode, greetings, headerHome, history, home, question);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}