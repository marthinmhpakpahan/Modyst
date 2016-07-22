package ual.modyst.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ual.modyst.R;
import ual.modyst.adapter.UserAdapter;
import ual.modyst.helper.SessionManager;
import ual.modyst.model.Users;
import ual.modyst.response.UsersResponse;
import ual.modyst.service.RESTClient;

public class UsersActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fab;
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initalizeComponent();
        loadData();
    }

    private void initalizeComponent(){
        List<Users> usersList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyecle_view);
        userAdapter = new UserAdapter(UsersActivity.this, usersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void loadData(){
        if(checkConnection()){
            try {
                Call<UsersResponse> usersCall = RESTClient.getRestClient().getUsers();
                usersCall.enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        try {
                            final List<Users> usersList = response.body().getData();
                            userAdapter = new UserAdapter(UsersActivity.this, usersList);
                            recyclerView.setAdapter(userAdapter);
                            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    Users users = usersList.get(position);
                                    Intent intent = new Intent(UsersActivity.this, ViewUserActivity.class);
                                    new SessionManager().setPreferences(UsersActivity.this, "unique_id_user", ""+users.getUnique_id());
                                    startActivity(intent);
                                }

                                @Override
                                public void onLongClick(View view, int position) {

                                }
                            }));
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {
                        Toast.makeText(UsersActivity.this, "Service get Users failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e){
                showDialogWarning("Warning", "Anda tidak terhubung ke internet. Silahkan cek koneksi internet anda!\nTerima kasih.");
            }
        } else {
            showDialogWarning("Warning", "Anda tidak terhubung ke internet. Silahkan cek koneksi internet anda!\nTerima kasih.");
        }
    }

    @Override
    public void onClick(View v) {
    }

    public boolean checkConnection(){
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for(NetworkInfo ni : networkInfos){
            /**
             * Pengecekan jika terhubung ke wifi
             */
            if(ni.getTypeName().equalsIgnoreCase("WIFI")){
                if(ni.isConnected())
                    haveConnectedWifi = true;
            }
            /**
             * Pengecekan jika terhubung ke mobile (data seluler)
             */
            if(ni.getTypeName().equalsIgnoreCase("MOBILE")){
                if(ni.isConnected())
                    haveConnectedMobile = true;
            }
        }

        /**
         * Pengecekan jika salah satu di antara wifi atau mobile data terhubung
         */
        if(haveConnectedWifi || haveConnectedMobile){
            return true;
        } else {
            return false;
        }
    }

    public void showDialogWarning(String title, String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private UsersActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final UsersActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
