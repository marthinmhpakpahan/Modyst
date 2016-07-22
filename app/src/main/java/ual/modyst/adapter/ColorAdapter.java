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
import ual.modyst.model.Colors;

/**
 * Created by kargo on 7/21/2016.
 */
public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder>{
    private List<Colors> colorsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_name;
        private LinearLayout layout_color;

        public MyViewHolder(View view) {
            super(view);
            textView_name = (TextView) view.findViewById(R.id.name_text);
            layout_color = (LinearLayout)view.findViewById(R.id.color_layout);
        }
    }

    public ColorAdapter(Context context, List<Colors> colorsList){
        this.context = context;
        this.colorsList = colorsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_color, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Colors colors = colorsList.get(position);
        holder.textView_name.setText(""+colors.getName());
    }

    @Override
    public int getItemCount(){
        return colorsList.size();
    }
}
