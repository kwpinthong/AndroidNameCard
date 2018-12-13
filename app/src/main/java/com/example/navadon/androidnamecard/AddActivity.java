package com.example.navadon.androidnamecard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseFirestore db;
    private EditText nameInput;
    private EditText idInput;
    private EditText emailInput;
    private EditText phoneInput;
    private Button btnAdd;
    private String name;
    private String id;
    private String email;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        initFirestore();
    }

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    private void initView() {
        nameInput = findViewById(R.id.box_name);
        idInput = findViewById(R.id.box_id);
        phoneInput = findViewById(R.id.box_phone);
        emailInput = findViewById(R.id.box_email);
        btnAdd = findViewById(R.id.btn_add);
    }

    public void click(View view) {
        name = nameInput.getText().toString();
        id = idInput.getText().toString();
        phone = phoneInput.getText().toString();
        email = emailInput.getText().toString();

        Map<String, Object> student = new HashMap<>();
        student.put("name", name);
        student.put("id", id);
        student.put("phone", phone);
        student.put("email", email);
        hideKeyboardInput(view);

        // Add a new document with a generated ID
        db.collection("students")
                .add(student)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        MainActivity.unRead = true;
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    private void hideKeyboardInput(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
