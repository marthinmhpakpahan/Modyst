package ual.modyst.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ual.modyst.R;

public class AdminDashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_list_user, button_list_color, button_list_size, button_list_item, button_list_request, button_create_item,
    button_create_color, button_create_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeComponent();
    }

    private void initializeComponent(){
        button_list_user = (Button)findViewById(R.id.list_user_button);
        button_list_color = (Button)findViewById(R.id.list_color_button);
        button_list_size = (Button)findViewById(R.id.list_size_button);
        button_list_item = (Button)findViewById(R.id.list_item_button);
        button_list_request = (Button)findViewById(R.id.list_request_button);
        button_create_item = (Button)findViewById(R.id.create_item_button);
        button_create_color = (Button)findViewById(R.id.create_color_button);
        button_create_size = (Button)findViewById(R.id.create_size_button);

        button_list_user.setOnClickListener(this);
        button_list_color.setOnClickListener(this);
        button_list_size.setOnClickListener(this);
        button_list_item.setOnClickListener(this);
        button_list_request.setOnClickListener(this);
        button_create_item.setOnClickListener(this);
        button_create_color.setOnClickListener(this);
        button_create_size.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == button_list_user){
            Intent intent = new Intent(AdminDashboardActivity.this, UsersActivity.class);
            startActivity(intent);
        }
        if(v == button_list_color){
            Intent intent = new Intent(AdminDashboardActivity.this, ColorsActivity.class);
            startActivity(intent);
        }
        if(v == button_list_size){
            Intent intent = new Intent(AdminDashboardActivity.this, SizesActivity.class);
            startActivity(intent);
        }
        if(v == button_list_item){
            Intent intent = new Intent(AdminDashboardActivity.this, ItemsActivity.class);
            startActivity(intent);
        }
        if(v == button_list_request){
            Intent intent = new Intent(AdminDashboardActivity.this, RequestsActivity.class);
            startActivity(intent);
        }
        if(v == button_create_item){
            Intent intent = new Intent(AdminDashboardActivity.this, CreateItemActivity.class);
            startActivity(intent);
        }
        if(v == button_create_color){
            Intent intent = new Intent(AdminDashboardActivity.this, CreateColorActivity.class);
            startActivity(intent);
        }
        if(v == button_create_size){
            Intent intent = new Intent(AdminDashboardActivity.this, CreateSizeActivity.class);
            startActivity(intent);
        }
    }
}
