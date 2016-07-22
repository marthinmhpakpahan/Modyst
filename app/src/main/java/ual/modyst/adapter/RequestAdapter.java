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
import ual.modyst.model.Requests;

/**
 * Created by kargo on 7/21/2016.
 */
public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyViewHolder>{
    private List<Requests> requestsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_item, textView_user, textView_created;

        public MyViewHolder(View view) {
            super(view);
            textView_item = (TextView) view.findViewById(R.id.item_text);
            textView_user = (TextView) view.findViewById(R.id.user_text);
            textView_created = (TextView) view.findViewById(R.id.created_text);
        }
    }

    public RequestAdapter(Context context, List<Requests> requestsList){
        this.context = context;
        this.requestsList = requestsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_request, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Requests requests = requestsList.get(position);
        holder.textView_item.setText(""+requests.getUnique_id_item());
        holder.textView_user.setText(""+requests.getUnique_id_users());
        holder.textView_created.setText(""+requests.getCreated());
    }

    @Override
    public int getItemCount(){
        return requestsList.size();
    }
}
