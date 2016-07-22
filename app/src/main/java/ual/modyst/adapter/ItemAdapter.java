package ual.modyst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ual.modyst.R;
import ual.modyst.model.Items;

/**
 * Created by kargo on 7/21/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
    private List<Items> itemsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_name, textView_category, textView_created;
        public ImageView imageView_icon;

        public MyViewHolder(View view) {
            super(view);
            textView_name = (TextView) view.findViewById(R.id.name_text);
            textView_category = (TextView) view.findViewById(R.id.category_text);
            textView_created = (TextView) view.findViewById(R.id.created_text);
            imageView_icon = (ImageView)view.findViewById(R.id.icon_image_view);
        }
    }

    public ItemAdapter(Context context, List<Items> itemsList){
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Items items = itemsList.get(position);
        holder.textView_name.setText(""+items.getName());
        holder.textView_category.setText(""+items.getUnique_id_categories());
        holder.textView_created.setText(""+items.getCreated());
    }

    @Override
    public int getItemCount(){
        return itemsList.size();
    }
}
