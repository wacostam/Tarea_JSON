package com.example.tarea_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtlista =findViewById(R.id.txtLista);

        String Lista="";
        JSONArray JSONlista =  new JSONArray(result);
        for(int i = 0; i< JSONlista.length(); i++) {
            JSONObject user =  JSONlista.getJSONObject(i);
            String id = user.getString("id");
            String nombreUser = user.getString("username");
            String correoElec = user.getString("email");


            Lista = Lista + "\n" + "Nº:" + id +
                    "Nombre:" + nombreUser + "Correo:" + correoElec.toLowerCase();
        }

        txtlista.setText(Lista);

    }
}