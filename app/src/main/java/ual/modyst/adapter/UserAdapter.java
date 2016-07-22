package ual.modyst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ual.modyst.R;
import ual.modyst.model.Users;

/**
 * Created by kargo on 7/21/2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    private List<Users> usersList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_full_name, textView_email, textView_created;
        private ImageView imageView_icon;

        public MyViewHolder(View view) {
            super(view);
            textView_full_name = (TextView) view.findViewById(R.id.full_name_text);
            textView_email = (TextView)view.findViewById(R.id.email_text);
            textView_created = (TextView)view.findViewById(R.id.created_text);
            imageView_icon = (ImageView)view.findViewById(R.id.icon_image_view);
        }
    }

    public UserAdapter(Context context, List<Users> usersList){
        this.context = context;
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Users users = usersList.get(position);
        holder.textView_full_name.setText(""+users.getFull_name());
        holder.textView_email.setText(""+users.getEmail());
        holder.textView_created.setText(""+users.getCreated());
    }

    @Override
    public int getItemCount(){
        return usersList.size();
    }
}
