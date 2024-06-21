// Generated by view binder compiler. Do not edit!
package com.example.emoticare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

public final class FragmentChatBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout button;

  @NonNull
  public final RecyclerView chatRecyclerView;

  @NonNull
  public final EditText messageInput;

  @NonNull
  public final ImageButton sendButton;

  @NonNull
  public final LinearLayout sendMessageLayout;

  private FragmentChatBinding(@NonNull ConstraintLayout rootView, @NonNull LinearLayout button,
      @NonNull RecyclerView chatRecyclerView, @NonNull EditText messageInput,
      @NonNull ImageButton sendButton, @NonNull LinearLayout sendMessageLayout) {
    this.rootView = rootView;
    this.button = button;
    this.chatRecyclerView = chatRecyclerView;
    this.messageInput = messageInput;
    this.sendButton = sendButton;
    this.sendMessageLayout = sendMessageLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentChatBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      LinearLayout button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.chatRecyclerView;
      RecyclerView chatRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (chatRecyclerView == null) {
        break missingId;
      }

      id = R.id.messageInput;
      EditText messageInput = ViewBindings.findChildViewById(rootView, id);
      if (messageInput == null) {
        break missingId;
      }

      id = R.id.sendButton;
      ImageButton sendButton = ViewBindings.findChildViewById(rootView, id);
      if (sendButton == null) {
        break missingId;
      }

      id = R.id.sendMessageLayout;
      LinearLayout sendMessageLayout = ViewBindings.findChildViewById(rootView, id);
      if (sendMessageLayout == null) {
        break missingId;
      }

      return new FragmentChatBinding((ConstraintLayout) rootView, button, chatRecyclerView,
          messageInput, sendButton, sendMessageLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}