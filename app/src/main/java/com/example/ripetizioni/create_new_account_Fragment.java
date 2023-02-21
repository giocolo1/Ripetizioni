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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class create_new_account_Fragment extends Fragment implements View.OnClickListener {

    private TextInputLayout tilFullName, tilEmail, tilPwd, tilConfirmPwd;
    private Button btSignUp;
    private FirebaseAuth mAuth;

    public create_new_account_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_new_account, container, false);

        mAuth = FirebaseAuth.getInstance();

        btSignUp = view.findViewById(R.id.bt_signup);
        btSignUp.setOnClickListener(this);

        tilFullName = view.findViewById(R.id.til_fullname);
        tilEmail = view.findViewById(R.id.til_email);
        tilPwd = view.findViewById(R.id.til_pwd);
        tilConfirmPwd = view.findViewById(R.id.til_confirmpwd);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_signup:
                sendRegistrationData(view);
                break;
        }
    }

    private void sendRegistrationData(View view) {

        String fullName = tilFullName.getEditText().getText().toString().trim(); //trim va usato per togliere una parte inutile finale della stringa
        String email = tilEmail.getEditText().getText().toString().trim();
        String pwd = tilPwd.getEditText().getText().toString().trim();
        //String confirmPwd = tietConfirmPwd.getText().toString().trim();


        if(checkData(fullName, email, pwd)){
            mAuth.createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                User user = new User(fullName, email, pwd);

                                FirebaseDatabase.getInstance().getReference("Utente")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) // ritorna l'id dell'utente appena creato
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getActivity(), "L'utente è stato registrato", Toast.LENGTH_LONG).show();
                                                    Navigation.findNavController(view).navigate(R.id.action_create_new_account_to_type_of_user_creationFragment);
                                                    // ((AuthenticationActivity)getActivity()).setViewPager(0);

                                                }
                                                else {
//                                        Toast.makeText(getActivity(), "L'utente non è stato registrato nel databse, Riprovare", Toast.LENGTH_LONG).show();
                                                    Toast.makeText(getActivity(), "Fehler"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            } else {
//                            Toast.makeText(getActivity(), "L'utente non è stato registrato, Riprovare", Toast.LENGTH_LONG).show();
                                Toast.makeText(getActivity(), "Fehler "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private boolean checkData(String fullName, String email, String pwd){
        boolean dataIsValid = true;

        if (fullName.isEmpty()){
            tilFullName.setError("È necessario inserire un nominativo");
            tilFullName.requestFocus();
            dataIsValid = false;
        }

        if (email.isEmpty()){
            tilEmail.setError("È necessario inserire una email");
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

        if(!tilPwd.getEditText().getText().toString().equals(tilConfirmPwd.getEditText().getText().toString())){
            tilConfirmPwd.setError("Le password devono coincidere");
            tilConfirmPwd.requestFocus();
            dataIsValid = false;
        }

        return dataIsValid;
    }


}