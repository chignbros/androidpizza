package com.chingbros.orderpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetOrderScreen extends AppCompatActivity {
    RequestQueue requestQueue1;

    ListView listView;
    ArrayList<String> jasonList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order_screen);

        String url1 = "http://192.168.0.112/first_php/index.php";


        listView = findViewById(R.id.listView);

        requestQueue1 = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url1, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

               parseJson(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue1.add(jsonArrayRequest);


    }
    private  void parseJson(JSONArray response){

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                String phone = jsonObject.getString("phone");
                int mushroom = jsonObject.getInt("mushroom");
                int pepperino=jsonObject.getInt("pepperino");
                int delivery = jsonObject.getInt("delivery");
                String rating = jsonObject.getString("rating");
                String time = jsonObject.getString("time");
                String mus="";
                String pep="";
                String dev="Eat In";
                if (mushroom==1){
                    mus="Mushroom";
                }
                if (pepperino==1){
                    pep="Pepperino";
                }
                if (delivery==1){
                    dev="Take Out";
                }

                String result ="phone: "+ phone +"\t\t"+"Topping: "+mus+ "\t\t" +pep+ "\t\t" +"Delivery method: "+dev+ "\t\t"+"Rating Star: " +rating+ "\t\t" +"Order Time: "+time;
                jasonList.add(result);
                System.out.println(jsonObject);
            }
            arrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,jasonList);
            listView.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}





