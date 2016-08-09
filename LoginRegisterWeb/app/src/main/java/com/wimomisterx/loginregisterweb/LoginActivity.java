package com.wimomisterx.loginregisterweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    String server_url = "http://10.1.1.6:8000/api/user-list";
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.bLogin);
        textView = (TextView) findViewById(R.id.tvResult) ;

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    textView.setText(response.getString("name"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                MySingleton.getInstance(LoginActivity.this).addToRequestque(jsonObjectRequest);
            }
        });
    }
}
