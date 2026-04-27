package com.example.hw_26_04;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.hw_26_04.network.BioUpdateRequest;
import com.example.hw_26_04.network.GitHubApiService;
import com.example.hw_26_04.network.GitHubUserResponse;
import com.example.hw_26_04.network.RetrofitClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GitHubAPI";

    private static final String GITHUB_TOKEN = "YOUR_API_KEY";

    private EditText etBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBio = findViewById(R.id.etBio);
        Button button = findViewById(R.id.changeBioButton);

        button.setText("Обновить Bio в GitHub");

        button.setOnClickListener(v -> {
            String newBio = etBio.getText().toString().trim();
            if (newBio.isEmpty()) {
                Toast.makeText(this, "Введите текст bio!", Toast.LENGTH_SHORT).show();
                return;
            }
            updateGitHubBio(newBio);
        });
    }

    private void updateGitHubBio(String newBio) {
        GitHubApiService apiService = RetrofitClient.getApiService();

        String authHeader = "Bearer " + GITHUB_TOKEN;

        BioUpdateRequest request = new BioUpdateRequest(newBio);

        apiService.updateBio(authHeader, request).enqueue(new Callback<GitHubUserResponse>() {
            @Override
            public void onResponse(Call<GitHubUserResponse> call, Response<GitHubUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GitHubUserResponse user = response.body();
                    Log.d(TAG, "Успех! Новый bio: " + user.getBio());
                    Toast.makeText(MainActivity.this,
                            "Bio успешно обновлено!\nЛогин: " + user.getLogin(),
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.e(TAG, "Ошибка: " + response.code() + " - " + response.message());
                    Toast.makeText(MainActivity.this,
                            "Ошибка сервера: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GitHubUserResponse> call, Throwable t) {
                Log.e(TAG, "Ошибка подключения: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this,
                        "Не удалось подключиться к GitHub", Toast.LENGTH_SHORT).show();
            }
        });
    }
}