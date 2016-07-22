package ual.modyst.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SessionManager {
    public void setPreferences(Context context, String key, String value){
        /*
            Fungsi ini untuk menambahkan atau menyimpan sesuatu dengan kata kunci (key) berupa String
            MODE_PRIVATE : Agar session hanya bisa diakses oleh aplikasi ini saja
         */
        SharedPreferences.Editor editor = context.getSharedPreferences("Modyst", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferences(Context context, String key){
        /*
            Fungsi ini untuk mengambil atau mendapatkan nilai (string) yang tersimpan berdasarkan key
         */
        SharedPreferences preferences = context.getSharedPreferences("Modyst", Context.MODE_PRIVATE);
        String position = preferences.getString(key, "");
        return position;
    }

    public String getCurrentDateTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
        String dateandtime = ""+mdformat.format(calendar.getTime())+" "+timeformat.format(calendar.getTime());
        return dateandtime;
    }
}
