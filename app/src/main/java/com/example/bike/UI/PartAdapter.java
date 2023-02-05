package com.example.bike.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bike.R;
import com.example.bike.entities.Part;

import java.util.List;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.PartViewHolder> {

    class PartViewHolder extends RecyclerView.ViewHolder {
        private final TextView partItemView;
        private final TextView partItemView2;
        private PartViewHolder(View itemview) {
            super(itemview);
            partItemView = itemview.findViewById(R.id.textViewpartname);
            partItemView2 = itemview.findViewById(R.id.textViewpartprice);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Part current = mParts.get(position);
                    Intent intent = new Intent(context,PartDetails.class);
                    intent.putExtra("id", current.getPartID());
                    intent.putExtra("name", current.getPartName());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Part> mParts;
    private final Context context;
    private final LayoutInflater mInflater;
    public PartAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PartAdapter.PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.part_list_item,parent,false);

        return new PartViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull PartAdapter.PartViewHolder holder, int position) {

        if (mParts != null) {
            Part current = mParts.get(position);
            String name = current.getPartName();
            int prodID = current.getPartID();
            holder.partItemView.setText(name);
            holder.partItemView2.setText(Integer.toString(prodID));
        } else {
            holder.partItemView.setText("No Part Name");
            holder.partItemView2.setText("No Part ID");
        }

    }

    @Override
    public int getItemCount() {
        return mParts.size();
    }

    public void setParts(List<Part> parts) {
        mParts = parts;
        notifyDataSetChanged();
    }
}
