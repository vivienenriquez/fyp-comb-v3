package com.example.studentsystem.ui.signout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.studentsystem.MainActivity;
import com.example.studentsystem.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignOutFragment extends Fragment {


    private SignOutViewModel signOutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        signOutViewModel =
                ViewModelProviders.of(this).get(SignOutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_signout, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        signOutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button btn_SignOut = root.findViewById(R.id.btn_SignOut);
        btn_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent myIntent = new Intent(getActivity(),
                        MainActivity.class);
                startActivity(myIntent);
            }
        });
        return root;



    }

}