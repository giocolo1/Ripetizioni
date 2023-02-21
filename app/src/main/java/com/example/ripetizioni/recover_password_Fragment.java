package com.example.ripetizioni;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class recover_password_Fragment extends Fragment {

    private Button btResetPwd;
    TextInputLayout tilEmail;
    FirebaseAuth auth;

    public recover_password_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recover_password, container, false);


        btResetPwd = (Button) view.findViewById(R.id.bt_resetpwd);
        tilEmail = view.findViewById(R.id.til_email);

        auth = FirebaseAuth.getInstance();

        btResetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword(view);
            }
        });

        return view;
    }

    private void resetPassword(View view) {

        String email = tilEmail.getEditText().getText().toString().trim();

        if(checkData(email)){
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Apri la mail per reimpostare la password", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.action_recover_password_Fragment_to_login);
                    }else {
                        Toast.makeText(getActivity(), "Qualcosa è andato storto, riprovare", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    private boolean checkData(String email) {
        boolean dataIsValid = true;

        if (email.isEmpty()) {
            tilEmail.setError("È necessario inserire un email");
            tilEmail.requestFocus();
            dataIsValid = false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("È necessario inserire un email valida");
            tilEmail.requestFocus();
            dataIsValid = false;
        }
        return dataIsValid;
    }

}