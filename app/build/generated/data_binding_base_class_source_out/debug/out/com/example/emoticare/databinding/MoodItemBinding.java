// Generated by view binder compiler. Do not edit!
package com.example.emoticare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.emoticare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MoodItemBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView dateTextView;

  @NonNull
  public final TextView moodTextView;

  private MoodItemBinding(@NonNull LinearLayout rootView, @NonNull TextView dateTextView,
      @NonNull TextView moodTextView) {
    this.rootView = rootView;
    this.dateTextView = dateTextView;
    this.moodTextView = moodTextView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MoodItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MoodItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.mood_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MoodItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dateTextView;
      TextView dateTextView = ViewBindings.findChildViewById(rootView, id);
      if (dateTextView == null) {
        break missingId;
      }

      id = R.id.moodTextView;
      TextView moodTextView = ViewBindings.findChildViewById(rootView, id);
      if (moodTextView == null) {
        break missingId;
      }

      return new MoodItemBinding((LinearLayout) rootView, dateTextView, moodTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
