package com.example.hw_25_04;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tvUSD, tvEUR, tvGBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUSD = findViewById(R.id.tvUSD);
        tvEUR = findViewById(R.id.tvEUR);
        tvGBP = findViewById(R.id.tvGBP);

        getRates();
    }

    private void getRates() {

        String url = "https://open.er-api.com/v6/latest/UAH";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONObject rates = response.getJSONObject("rates");

                        double usd = rates.getDouble("USD");
                        double eur = rates.getDouble("EUR");
                        double gbp = rates.getDouble("GBP");

                        tvUSD.setText("USD: " + usd);
                        tvEUR.setText("EUR: " + eur);
                        tvGBP.setText("GBP: " + gbp);

                    } catch (Exception e) {
                        tvUSD.setText("Parse error");
                        e.printStackTrace();
                    }
                },
                error -> {
                    tvUSD.setText("Network error");
                    tvEUR.setText("");
                    tvGBP.setText("");
                    error.printStackTrace();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        queue.add(request);
    }
}