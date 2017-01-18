package com.example.pt.sqlitewithrecyclerview.activity;

/**
 * Created by pt on 5/2/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pt.sqlitewithrecyclerview.R;
import com.example.pt.sqlitewithrecyclerview.helper.DatabaseHelper;
import com.example.pt.sqlitewithrecyclerview.model.Instructor;


public class InstructorAddFragment extends Fragment {

    Toolbar toolbar ;
    private EditText inputName, inputEmail, inputDept , inputSalary;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutDept , inputLayoutSalary;
    private Button btnAdd;
    public InstructorAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_instructor, container, false);

        inputLayoutName = (TextInputLayout)rootView.findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout)rootView.findViewById(R.id.input_layout_email);
        inputLayoutDept = (TextInputLayout)rootView.findViewById(R.id.input_layout_dept);
        inputLayoutSalary = (TextInputLayout)rootView.findViewById(R.id.input_laytout_salary);
        FloatingActionButton list = (FloatingActionButton) rootView.findViewById(R.id.fab_list);


        inputName = (EditText)rootView.findViewById(R.id.input_name);
        inputEmail = (EditText)rootView.findViewById(R.id.input_email);
        inputDept = (EditText)rootView.findViewById(R.id.input_dept);
        inputSalary = (EditText)rootView.findViewById(R.id.input_salary);

        btnAdd = (Button)rootView.findViewById(R.id.btn_add);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputDept.addTextChangedListener(new MyTextWatcher(inputDept));
        inputSalary.addTextChangedListener(new MyTextWatcher(inputSalary));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
                String name= inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String dept = inputDept.getText().toString();
                int salary = Integer.parseInt(inputSalary.getText().toString()) ;

                DatabaseHelper db = new DatabaseHelper(getContext());
                Instructor instructor = new Instructor(name,email,dept,salary);
                db.addInstructor(instructor);
                Toast.makeText(getActivity(), "Data Added To Database ", Toast.LENGTH_SHORT).show();
                inputName.setText(null);
                inputEmail.setText(null);
                inputDept.setText(null);
                inputSalary.setText(null);



            }
        });

       list.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               Fragment listFragment = new InstructorListFragment();
               FragmentTransaction transaction = getFragmentManager().beginTransaction();
               transaction.replace(R.id.container_body,listFragment);
               transaction.addToBackStack(null);
               transaction.commit();
               getActivity().setTitle("Instructor List");
           }
       });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validateDept()) {
            return;
        }
        if(!validateSalary()) {
            return;
        }

        Toast.makeText(getActivity(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateSalary() {


        if (inputSalary.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
        return true ;
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDept() {
        if (inputDept.getText().toString().trim().isEmpty()) {
            inputLayoutDept.setError(getString(R.string.err_msg_dept));
            requestFocus(inputDept);
            return false;
        } else {
            inputLayoutDept.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_dept:
                    validateDept();
                    break;
                case R.id.input_salary:
                    validateSalary();
                    break;
            }
        }
    }
}

