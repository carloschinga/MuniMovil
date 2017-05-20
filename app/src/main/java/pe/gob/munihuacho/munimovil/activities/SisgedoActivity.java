package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import pe.gob.munihuacho.munimovil.R;

public class SisgedoActivity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sisgedo);
        setTitle("Sisgedo");
        webView=(WebView)findViewById(R.id.sisgedo_view);
        webView.getSettings().setJavaScriptEnabled(true);//
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("http://app.munihuacho.gob.pe/sisgedonew/app/main.php");
        //Metodo que permite  navegar dentro    del webview sin abrir navegador
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                return false;
            }
        });
    }
}
