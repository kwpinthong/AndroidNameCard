package com.example.navadon.androidnamecard;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import android.widget.TextView;

import com.example.navadon.androidnamecard.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //private int check = 0;
    //private View.OnClickListener onClickListener;

    // Step 1 //
    private MainViewModel viewModel;
    ActivityMainBinding binding;
    private FirebaseFirestore db;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFirestore();
        test();
    }

    private void initView(){
        //initOnClickListener();
        // To register click event to view
        // findViewById(R.id.btn_change).setOnClickListener(onClickListener);

        // Step 2 //
        viewModel = new MainViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(viewModel);
    }

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    private void test() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    /*
    private void initOnClickListener(){
        // Only one OnclickListener is created to handle all onClick events.
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_change:
                        if(check == 0) check = 1;
                        else if(check == 1) check = 0;
                        changeInformation(check);
                        break;
                }
            }
        };
    }
    */

    public void click (View View){
        long time = System.currentTimeMillis();
        if(time%2==0)
            setDataViewModel(getResources().getString(R.string.name),  getResources().getString(R.string.idCode),
                    getResources().getString(R.string.email), getResources().getString(R.string.phone));
       else
            setDataViewModel(getResources().getString(R.string.name2),  getResources().getString(R.string.idCode2),
                    getResources().getString(R.string.email2), getResources().getString(R.string.phone2));

        // Step 3 //
        binding.name.setText(viewModel.getName());
        binding.idCode.setText(viewModel.getId());
        binding.email.setText(viewModel.getEmail());
        binding.phone.setText(viewModel.getPhone());
    }

    private void setDataViewModel(String name, String id, String email, String phone){
        viewModel.setName(name);
        viewModel.setId(id);
        viewModel.setEmail(email);
        viewModel.setPhone(phone);
    }
    /*
    private void changeInformation(int check){

        TextView name = findViewById(R.id.name);
        TextView idCode = findViewById(R.id.idCode);
        TextView email = findViewById(R.id.email);
        TextView phone = findViewById(R.id.phone);

        if (check == 1){
            name.setText(getResources().getString(R.string._name));
            idCode.setText(getResources().getString(R.string._idCode));
            email.setText(getResources().getString(R.string._email));
            phone.setText(getResources().getString(R.string._phone));
        }else{
            name.setText(getResources().getString(R.string.name));
            idCode.setText(getResources().getString(R.string.idCode));
            email.setText(getResources().getString(R.string.email));
            phone.setText(getResources().getString(R.string.phone));
        }
    }
    */
}
