package com.example.efc_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Queue;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    AdapterUser adapterUser;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ProgressDialog progressDialog = new ProgressDialog(this);
        p

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        adapterUser = new AdapterUser(MainActivity2.this,userArrayList);
        EventChangeListener();
    }

    private void EventChangeListener(){
        db.collection("Users").orderBy("nombre",Query.Direction.ASCENDING)
                .addSanpshotListener(new EventListener<QuerySnapshot>(){
                    @Override
                    public void  onEvent(@Nullable QuerySnapshot value, @Nullable FiresbaseFirestoreException error){
                        if(error!= null  ){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc: value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                userArrayList.add(dc.getDocument().toObject(User.class));
                            }
                            adapterUser.notifyDataSetChanged();
                        }
                    }
                }
    }


}