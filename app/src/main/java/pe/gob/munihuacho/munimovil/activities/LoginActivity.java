package pe.gob.munihuacho.munimovil.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pe.gob.munihuacho.munimovil.MainActivity;
import pe.gob.munihuacho.munimovil.R;

public class LoginActivity extends AppCompatActivity {
Button button_ingresar_sistema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_ingresar_sistema=(Button)findViewById(R.id.button_ingresar_sistema);
        button_ingresar_sistema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Implement predios y arbitrios activity
                Toast.makeText(LoginActivity.this, "Zona sin implementar...", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
