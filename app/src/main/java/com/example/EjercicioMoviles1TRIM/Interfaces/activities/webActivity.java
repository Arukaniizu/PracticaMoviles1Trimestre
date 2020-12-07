package com.example.EjercicioMoviles1TRIM.Interfaces.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.example.EjercicioMoviles1TRIM.R;
import static com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants.MADRIDSPORTS;
import static com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants.MADRIDRESERVAS;
import static com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants.MADRIDPISCINAS;
import static com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants.MARCA;
public class webActivity extends AppCompatActivity {
    int paginaWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = findViewById(R.id.webId);

        Intent recibir =getIntent();

        paginaWeb =getIntent().getIntExtra("marca", 0);


        if (paginaWeb == 1){
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.loadUrl(MADRIDSPORTS);
        }else if (paginaWeb == 2){
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.loadUrl(MADRIDRESERVAS);
        }else if (paginaWeb == 3){
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.loadUrl(MADRIDPISCINAS);
        }else if(paginaWeb == 4){
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.loadUrl(MARCA);

        }

    }
}