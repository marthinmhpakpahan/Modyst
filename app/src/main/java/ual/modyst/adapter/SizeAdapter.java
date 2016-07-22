package ual.modyst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ual.modyst.R;
import ual.modyst.model.Sizes;

/**
 * Created by kargo on 7/21/2016.
 */
public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.MyViewHolder>{
    private List<Sizes> sizesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_name, textView_description;

        public MyViewHolder(View view) {
            super(view);
            textView_name = (TextView) view.findViewById(R.id.name_text);
            textView_description = (TextView)view.findViewById(R.id.description_text);
        }
    }

    public SizeAdapter(Context context, List<Sizes> sizesList){
        this.context = context;
        this.sizesList = sizesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_size, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Sizes sizes = sizesList.get(position);
        holder.textView_name.setText(""+sizes.getName());
        holder.textView_description.setText(""+sizes.getDescription());
    }

    @Override
    public int getItemCount(){
        return sizesList.size();
    }
}