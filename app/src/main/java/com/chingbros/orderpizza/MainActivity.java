package com.chingbros.orderpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnOrder;
    RequestQueue requestQueue ;
    EditText editText;
    CheckBox checkBox1;
    CheckBox checkBox2;
    RadioGroup radioGroup;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOrder=findViewById(R.id.btnOrder);
      editText =findViewById(R.id.txtPhone);
        checkBox1 =findViewById(R.id.checkbox1);
       checkBox2 =findViewById(R.id.checkbox2);
       radioGroup=findViewById(R.id.RadioGroup);
       ratingBar=findViewById(R.id.ratingBar);
      requestQueue=  Volley.newRequestQueue(this);
        btnOrder.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                String pep="false";
                String mus="false";
                String dev="false";
                if (checkBox1.isChecked()==true){
                    pep="true";
                }
                if (checkBox2.isChecked()==true){
                    mus="true";
                }
                int id=radioGroup.getCheckedRadioButtonId();

                if (id==R.id.radioButton2){
                    dev="true";
                }





                System.out.println("================..fck u"+id);
                JSONObject json=new JSONObject();
                try {
                    json.put("phone", editText.getText().toString());
                    json.put("pepperino", pep);
                    json.put("mushroom", mus);
                    json.put("delivery", dev);
                    json.put("rating", ratingBar.getRating());

                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

                String url="http://192.168.0.112/first_php/pizza.php";




                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });





                requestQueue.add(jsonObjectRequest);


                Intent intent=new Intent(getApplicationContext(),GetOrderScreen.class);
                startActivity(intent);



            }
        });

    }
}
