package com.example.ripetizioni;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class type_of_user_creationFragment extends Fragment implements View.OnClickListener {

    Button button_create_student, button_create_teacher;

    public type_of_user_creationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_of_user_creation, container, false);

        button_create_student = (Button) view.findViewById(R.id.button_create_student);
        button_create_student.setOnClickListener(this);
        button_create_teacher = (Button) view.findViewById(R.id.button_create_teacher);
        button_create_teacher.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_create_teacher:
                Navigation.findNavController(view).navigate(R.id.action_type_of_user_creationFragment_to_professor_settingsFragment);
                break;

            case R.id.button_create_student:
                Navigation.findNavController(view).navigate(R.id.action_type_of_user_creationFragment_to_student_settingsFragment);
                break;
        }
    }
}