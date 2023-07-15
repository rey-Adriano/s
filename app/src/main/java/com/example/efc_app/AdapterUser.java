package com.example.efc_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUser  extends RecyclerView.Adapter<AdapterUser.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public AdapterUser(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public AdapterUser.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.MyViewHolder holder, int position) {

        User    user = userArrayList.get(position);
        holder.nombre.setText(user.nombre);
        holder.apellido.setText(user.apellido);
        holder.email.setText(user.email);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, apellido, email;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.eTNombre);
            apellido= itemView.findViewById(R.id.eTapellido);
            email = itemView.findViewById(R.id.eTemail);
        }
    }
}
