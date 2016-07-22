package ual.modyst.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ual.modyst.R;
import ual.modyst.helper.SessionManager;
import ual.modyst.response.AuthenticationResponse;
import ual.modyst.response.UserResponse;
import ual.modyst.service.RESTClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_email, editText_password;
    private TextView textView_error_email, textView_error_password, textView_register;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeComponent();
    }

    private void initializeComponent(){
        editText_email = (EditText)findViewById(R.id.email_edit_text);
        editText_password = (EditText)findViewById(R.id.password_edit_text);
        textView_error_email = (TextView)findViewById(R.id.error_email_text);
        textView_error_password = (TextView)findViewById(R.id.error_password_text);
        textView_register = (TextView)findViewById(R.id.register_text);
        button_login = (Button)findViewById(R.id.login_button);

        textView_register.setOnClickListener(this);
        button_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == textView_register){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        if(v == button_login){
            String email = "", password = "";
            boolean email_empty = true, password_empty = true;

            email = editText_email.getText().toString().trim();
            password = editText_password.getText().toString().trim();

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

            if(!email_empty && !password_empty){
                if(checkConnection()){
                    try {
                        Call<AuthenticationResponse> authenticateCall = RESTClient.getRestClient().authenticate(email, password, "registration_key");
                        authenticateCall.enqueue(new Callback<AuthenticationResponse>() {
                            @Override
                            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                                if(response.body().getResult().equals("true")){
                                    new SessionManager().setPreferences(LoginActivity.this, "id_login", response.body().getId_users());
                                    Log.e("LoginActivity", "ID Users: "+response.body().getId_users());
                                    Call<UserResponse> userCall = RESTClient.getRestClient().getUser(Integer.parseInt(response.body().getId_users()));
                                    userCall.enqueue(new Callback<UserResponse>() {
                                        @Override
                                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                            if(response.body().getResult().equals("true")){
                                                if(response.body().getUnique_id_roles() == 1){
                                                    new SessionManager().setPreferences(LoginActivity.this, "role", "admin");
                                                    Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                                                    startActivity(intent);
                                                } else {
                                                    new SessionManager().setPreferences(LoginActivity.this, "role", "customer");
                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            } else {
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UserResponse> call, Throwable t) {
                                            Toast.makeText(LoginActivity.this, "Service get User failed!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    showDialogWarning("Warning!", "Username atau password anda tidak valid!");
                                }
                            }

                            @Override
                            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Service login failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e){
                        showDialogWarning("Warning", "Anda tidak terhubung ke internet. Silahkan cek koneksi internet anda!\nTerima kasih.");
                    }
                } else {
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
