package com.example.iatiimd_eindoplevering;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class IdeeAdapter extends RecyclerView.Adapter<IdeeAdapter.IdeeViewHolder> {
    private Idee[] idees;

    public IdeeAdapter(Idee[] idees) {
        this.idees = idees;
    }

    public static class IdeeViewHolder extends RecyclerView.ViewHolder {
        public TextView Idee_Name;
        public TextView Idee_Desc;
        public TextView Idee_Cat;
        public ImageButton Idee_Edit;
        public int Idee_id;

        public IdeeViewHolder(View v) {
            super(v);
            Idee_Name =v.findViewById(R.id.Idee_Name);
            Idee_Desc = v.findViewById(R.id.idee_Desc);
            Idee_Cat = v.findViewById(R.id.idee_Cat);
            Idee_Edit = v.findViewById(R.id.idee_Edit);
        }
    }

    @NonNull
    @Override
    public IdeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.idee_card, parent, false);
        IdeeViewHolder ideeViewHolder = new IdeeViewHolder(v);
        return ideeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IdeeAdapter.IdeeViewHolder holder, int position) {
        holder.Idee_Name.setText(idees[position].getTitle());
        holder.Idee_Desc.setText(idees[position].getDescription());
        holder.Idee_Cat.setText(idees[position].getCategorie());
        holder.Idee_id = idees[position].getId();

        holder.Idee_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG_TEST", "onClick: " + holder.Idee_id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return idees.length;
    }
}
