package com.joo.veheclemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText quantity,date,description,lecence,plateno,refno,price;
    Button Login;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity=(EditText)findViewById(R.id.q);
        date=(EditText)findViewById(R.id.d);
        description=(EditText)findViewById(R.id.de);
        lecence=(EditText)findViewById(R.id.l);
        plateno=(EditText)findViewById(R.id.p);
        refno=(EditText)findViewById(R.id.r);
        price=(EditText)findViewById(R.id.pr) ;
        queue = Volley.newRequestQueue(this);

        Login = (Button) findViewById(R.id.submit);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( TextUtils.isEmpty(quantity.getText())|| TextUtils.isEmpty(description.getText())|| TextUtils.isEmpty(lecence.getText())
                        || TextUtils.isEmpty(plateno.getText())|| TextUtils.isEmpty(refno.getText())){
                    Toast.makeText(getApplicationContext(), "Please Fill all Fields..!!", Toast.LENGTH_LONG).show();
                }else {

                    final Map<String, Object> data = new HashMap<>();
                    data.put("quantity",Integer.parseInt(quantity.getText().toString()));
                    data.put("price",Integer.parseInt(price.getText().toString()));
                    data.put("date", date.getText().toString());
                    data.put("description", description.getText().toString());
                    data.put("licence",Double.valueOf( lecence.getText().toString()));
                    data.put("plateNo", plateno.getText().toString());
                    data.put("refNo", Integer.parseInt(refno.getText().toString()));
                    String url = "http://192.168.43.137:8080/service/v";
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                                Log.d("", "work");


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            VolleyLog.e("Error: ", error.getMessage());
                            Toast.makeText(getApplicationContext(), "Insert succesfull", Toast.LENGTH_LONG).show();
                        }
                    });

                    queue.add(request);
                }
            }
        });
    }
}
