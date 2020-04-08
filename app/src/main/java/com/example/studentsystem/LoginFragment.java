package com.example.studentsystem;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button button = (Button) view.findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // https://firebase.google.com/docs/auth/android/microsoft-oauth#before_you_begin

                //FirebaseAuth.getInstance().signOut();
                OAuthProvider.Builder provider = OAuthProvider.newBuilder("microsoft.com");
                // Force re-consent.
                provider.addCustomParameter("prompt", "select_account");


                List<String> scopes =
                        new ArrayList<String>() {
                            {
                                add("mail.read");
                                add("calendars.read");
                            }
                        };
                provider.setScopes(scopes);

                Task<AuthResult> pendingResultTask = firebaseAuth.getPendingAuthResult();
                if (pendingResultTask != null) {
                    // There's something already here! Finish the sign-in for your user.
                    pendingResultTask
                            .addOnSuccessListener(
                                    new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            // User is signed in.
                                            // IdP data available in
                                            // authResult.getAdditionalUserInfo().getProfile().
                                            // The OAuth access token can also be retrieved:
                                            // authResult.getCredential().getAccessToken().
                                            // The OAuth ID token can also be retrieved:
                                            // authResult.getCredential().getIdToken().
                                            Toast.makeText(getActivity(), "Hi " + authResult.getUser().getDisplayName() + " \nSuccessfully logged in with microsoft !!", Toast.LENGTH_LONG).show();
                                            Intent myIntent = new Intent(getActivity(),
                                                    HomeActivity.class);
                                            startActivity(myIntent);
                                        }
                                    })
                            .addOnFailureListener(
                                    new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Handle failure.
                                            Log.d("Failed", "onFailure: ");
                                            Toast.makeText(getActivity(), "Failed to login with microsoft", Toast.LENGTH_LONG).show();
                                        }
                                    });
                } else {
                    // There's no pending result so you need to start the sign-in flow.
                    // See below.
                    firebaseAuth
                            .startActivityForSignInWithProvider(/* activity= */ getActivity(), provider.build())
                            .addOnSuccessListener(
                                    new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            // User is signed in.
                                            // IdP data available in
                                            // authResult.getAdditionalUserInfo().getProfile().
                                            // The OAuth access token can also be retrieved:
                                            // authResult.getCredential().getAccessToken().
                                            // The OAuth ID token can also be retrieved:
                                            // authResult.getCredential().getIdToken().

                                            Toast.makeText(getActivity(), "Hi " + authResult.getUser().getDisplayName() + " \nSuccessfully logged in with microsoft !!", Toast.LENGTH_LONG).show();
                                            Intent myIntent = new Intent(getActivity(),
                                                    HomeActivity.class);
                                            startActivity(myIntent);
                                        }
                                    })
                            .addOnFailureListener(
                                    new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Handle failure.
                                            Toast.makeText(getActivity(), "Failed to login with microsoft", Toast.LENGTH_LONG).show();
                                        }
                                    });
                }


            }

        });
        // Inflate the layout for this fragment
        return view;
    }

}
