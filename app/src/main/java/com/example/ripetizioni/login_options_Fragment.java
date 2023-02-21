package com.example.ripetizioni;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_options_Fragment extends Fragment implements View.OnClickListener{

    SignInButton google_sign_in_button;

    Button email_sign_in_button;

    TextView tv_signup;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;

    public login_options_Fragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_options, container, false);

        google_sign_in_button = (SignInButton) view.findViewById(R.id.google_sign_in_button);
        google_sign_in_button.setOnClickListener(this);
        email_sign_in_button = (Button) view.findViewById(R.id.email_sign_in_button);
        email_sign_in_button.setOnClickListener(this);
        tv_signup = view.findViewById(R.id.tv_signup);
        tv_signup.setOnClickListener(this);

        // Check if user is signed in
//        FirebaseUser currentUser = mAuth.getCurrentUser();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.google_sign_in_button:
//                googleSignIn();
                break;

            case R.id.email_sign_in_button:
                Navigation.findNavController(view).navigate(R.id.action_login_options_Fragment_to_login2);
                break;

            case R.id.tv_signup:
                Navigation.findNavController(view).navigate(R.id.action_login_options_Fragment_to_create_new_account2);
                break;
        }
    }

    private void googleSignIn() {
        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("920855986125-e9gpvc6hrb31lg48vthkdsa0kb7e9fln.apps.googleusercontent.com")
////                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
//
//        mAuth = FirebaseAuth.getInstance();
    }

}