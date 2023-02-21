package com.example.ripetizioni;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login_Fragment extends Fragment implements View.OnClickListener {

    TextInputLayout tilPwd, tilEmail;
    TextView tvForgotpwd;
    Button btSignin;
    SwitchMaterial sw_remember;

    boolean rememberMePlz;

    private FirebaseAuth mAuth;

    public Login_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        if(User.getUserName(getActivity()).length() == 0){
            tilPwd = view.findViewById(R.id.til_pwd);
            tilEmail = view.findViewById(R.id.til_email);

            btSignin = view.findViewById(R.id.bt_signin);
            btSignin.setOnClickListener(this);
            tvForgotpwd = view.findViewById(R.id.tv_forgotpwd);
            tvForgotpwd.setOnClickListener(this);

            sw_remember = view.findViewById(R.id.sw_remember);
            sw_remember.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) (button, isChecked) -> {
                if(isChecked) {
                    rememberMePlz = true;
                } else {
                    rememberMePlz = false;
                }

            });


            mAuth = FirebaseAuth.getInstance();

        } else {
            Intent intent = new Intent(getActivity(), mainActivity.class);
            startActivity(intent);
            getActivity().finish();

        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_signin:
                loginUser();
                break;

            case R.id.tv_forgotpwd:
                Navigation.findNavController(view).navigate(R.id.action_login_to_recover_password_Fragment);
                break;
        }
    }

    private void loginUser() {
        String email = tilEmail.getEditText().getText().toString().trim();
        String pwd = tilPwd.getEditText().getText().toString().trim();


        if(checkData(email, pwd)){
            mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()) {
                        if (rememberMePlz){
                            User.setUserName(getActivity(),email);
                        }


                        Intent intent = new Intent(getActivity(), mainActivity.class);
                        startActivity(intent);
                        getActivity().finish();


//                    if (utente.isEmailVerified()) {
//                        //se la mail è verificata mi fa accedere
//                        startActivity(new Intent(Login.this, MainActivity.class));
//                    }else {
//                        utente.sendEmailVerification();
//
//                    Toast.makeText(Login.this, "conferma l'account via mail", Toast.LENGTH_LONG).show();
//                    }

                    }else {
                        Toast.makeText(getActivity(), "Email o password errata", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }

    private boolean checkData(String email, String pwd){
        boolean dataIsValid = true;

        if (email.isEmpty()){
            tilEmail.setError("È necessario inserire un email");
            tilEmail.requestFocus();
            dataIsValid = false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmail.setError("È necessario inserire un email valida");
            tilEmail.requestFocus();
            dataIsValid = false;
        }

        if (pwd.isEmpty()){
            tilPwd.setError("È necessario inserire una password");
            tilPwd.requestFocus();
            dataIsValid = false;
        }
        else if (pwd.length() < 6) {
            tilPwd.setError("È necessario inserire una password con almeno 6 caratteri");
            tilPwd.requestFocus();
            dataIsValid = false;
        }

        return dataIsValid;
    }
}