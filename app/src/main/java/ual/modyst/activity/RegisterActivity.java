package ual.modyst.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ual.modyst.R;
import ual.modyst.response.RequestResponse;
import ual.modyst.service.RESTClient;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_full_name, editText_email, editText_password, editText_phone_number, editText_address;
    private TextView textView_login, textView_error_full_name, textView_error_email, textView_error_password, textView_error_phone_number, textView_error_address;
    private Button button_register;
    private Spinner spinner_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeComponent();
    }

    private void initializeComponent(){
        editText_full_name = (EditText)findViewById(R.id.full_name_edit_text);
        editText_email = (EditText)findViewById(R.id.email_edit_text);
        editText_password = (EditText)findViewById(R.id.password_edit_text);
        editText_phone_number = (EditText)findViewById(R.id.phone_number_edit_text);
        editText_address = (EditText)findViewById(R.id.address_edit_text);
        textView_login = (TextView)findViewById(R.id.login_text);
        textView_error_full_name = (TextView)findViewById(R.id.error_full_name_text);
        textView_error_email = (TextView)findViewById(R.id.error_email_text);
        textView_error_password = (TextView)findViewById(R.id.error_password_text);
        textView_error_phone_number = (TextView)findViewById(R.id.error_phone_number_text);
        textView_error_address = (TextView)findViewById(R.id.error_address_text);
        button_register = (Button)findViewById(R.id.register_button);
        spinner_sex = (Spinner)findViewById(R.id.sex_spinner);

        /**
         * Insert data list spinner
         */
        ArrayList<String> driver = new ArrayList<String>();
        driver.add("Female");
        driver.add("Male");
        ArrayAdapter adapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_item, driver);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sex.setAdapter(adapter);

        textView_login.setOnClickListener(this);
        button_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String full_name="", email="", password="", sex="", phone_number="", address="";
        boolean full_name_empty=true, email_empty=true, password_empty=true, phone_number_empty=true, address_empty=true;
        full_name = editText_full_name.getText().toString().trim();
        email = editText_email.getText().toString().trim();
        password = editText_password.getText().toString().trim();
        sex = spinner_sex.getSelectedItem().toString();
        phone_number = editText_phone_number.getText().toString().trim();
        address = editText_address.getText().toString().trim();

        if(v == textView_login){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        if(v == button_register){
            if(full_name.equals("")){
                textView_error_full_name.setVisibility(View.VISIBLE);
            } else {
                textView_error_full_name.setVisibility(View.GONE);
                full_name_empty = false;
            }
            if(email.equals("")){
                textView_error_email.setVisibility(View.VISIBLE);
            } else {
                textView_error_email.setVisibility(View.GONE);
                email_empty = false;
            }
            if(password.equals("")){
                textView_error_password.setVisibility(View.VISIBLE);
            } else {
                textView_error_password.setVisibility(View.GONE);
                password_empty = false;
            }
            if(phone_number.equals("")){
                textView_error_phone_number.setVisibility(View.VISIBLE);
            } else {
                textView_error_phone_number.setVisibility(View.GONE);
                phone_number_empty = false;
            }
            if(address.equals("")){
                textView_error_address.setVisibility(View.VISIBLE);
            } else {
                textView_error_address.setVisibility(View.GONE);
                address_empty = false;
            }

            if(!full_name_empty && !email_empty && !password_empty && !phone_number_empty && !address_empty){
                if(checkConnection()){
                    try {
                        Call<RequestResponse> registerCall = RESTClient.getRestClient().register(full_name, email, password, sex, phone_number, address);
                        registerCall.enqueue(new Callback<RequestResponse>() {
                            @Override
                            public void onResponse(Call<RequestResponse> call, Response<RequestResponse> response) {
                                if(response.body().getResult().equals("true")){
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    showDialogWarning("Warning!", "Register gagal, silahkan periksa kembali data anda.\nTerima kasih.");
                                }
                            }

                            @Override
                            public void onFailure(Call<RequestResponse> call, Throwable t) {
                                Toast.makeText(RegisterActivity.this, "Service register failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e){
                        showDialogWarning("Warning", "Anda tidak terhubung ke internet. Silahkan cek koneksi internet anda!\nTerima kasih.");
                    }
                }  else {
                    showDialogWarning("Warning", "Anda tidak terhubung ke internet. Silahkan cek koneksi internet anda!\nTerima kasih.");
                }
            }
        }
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
}
