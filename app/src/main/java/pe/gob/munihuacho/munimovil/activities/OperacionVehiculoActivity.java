package pe.gob.munihuacho.munimovil.activities;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import pe.gob.munihuacho.munimovil.R;

public class OperacionVehiculoActivity extends AppCompatActivity {
    TextView tvPlacaOV,
            tvPadronOV,
            tvCertiOperOV,
            tvDniOV,
            tvNombreOv,
            tvEmpresaOV,
            tvServicioOV,
            tvClaseOV,
            tvMotorOV,
            tvSerieOV,
            tvColorOV,
            tvFechaEmisionOV,
            tvFechaCaducidadOV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacion_vehiculo);
        setTitle("Consulta de operación vehicular");

        //**CONSIGUIENDO LOS DATOS DEL FRAGMENT OPERACION VEHICULO*/
        String numplaca=getIntent().getStringExtra("numplaca");
        String numpadron=getIntent().getStringExtra("numpadron");
        String numtarjeta=getIntent().getStringExtra("numtarjeta");
        String per_dni=getIntent().getStringExtra("per_dni");
        String nombre=getIntent().getStringExtra("nombre");
        String razonsocial=getIntent().getStringExtra("razonsocial");
        String tiposervicioempresa=getIntent().getStringExtra("tiposervicioempresa");
        String clasevh=getIntent().getStringExtra("clasevh");
        String nummotor=getIntent().getStringExtra("nummotor");
        String numserie=getIntent().getStringExtra("numserie");
        String color=getIntent().getStringExtra("color");
        String fechaEmision=getIntent().getStringExtra("fechaEmision");
        String fechaCaducidad=getIntent().getStringExtra("fechaCaducidad");

        //**INICIALIZANDO CAMPOS DE TEXTO*/
        tvPlacaOV=(TextView)findViewById(R.id.tvPlacaOV);
        tvPadronOV=(TextView)findViewById(R.id.tvPadronOV);
        tvCertiOperOV=(TextView)findViewById(R.id.tvCertiOperOV);
        tvDniOV=(TextView)findViewById(R.id.tvDniOV);
        tvNombreOv=(TextView)findViewById(R.id.tvNombreOv);
        tvEmpresaOV=(TextView)findViewById(R.id.tvEmpresaOV);
        tvServicioOV=(TextView)findViewById(R.id.tvServicioOV);
        tvClaseOV=(TextView)findViewById(R.id.tvClaseOV);
        tvMotorOV=(TextView)findViewById(R.id.tvMotorOV);
        tvSerieOV=(TextView)findViewById(R.id.tvSerieOV);
        tvColorOV=(TextView)findViewById(R.id.tvColorOV);
        tvFechaEmisionOV=(TextView)findViewById(R.id.tvFechaEmisionOV);
        tvFechaCaducidadOV=(TextView)findViewById(R.id.tvFechaCaducidadOV);

        //**SETEANDO CAMPOS DE TEXTO*/
        tvPlacaOV.setText(numplaca);
        tvPadronOV.setText(numpadron);
        tvCertiOperOV.setText(numtarjeta);
        tvDniOV.setText(per_dni);
        tvNombreOv.setText(nombre);
        tvEmpresaOV.setText(razonsocial);
        tvServicioOV.setText(tiposervicioempresa);
        tvClaseOV.setText(clasevh);
        tvMotorOV.setText(nummotor);
        tvSerieOV.setText(numserie);
        tvColorOV.setText(color);
        tvFechaEmisionOV.setText(fechaEmision);
        tvFechaCaducidadOV.setText(fechaCaducidad)       ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_child_activitys, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_back){
            if(getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
            }else{
                super.onBackPressed();
            }
        }
        if(id==R.id.action_screenshot){
            Toast.makeText(this, "Proximamente...!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void captureScreen() {
        View v = getWindow().getDecorView().getRootView();
        v.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        try {
            FileOutputStream fos = new FileOutputStream(new File(Environment
                    .getExternalStorageDirectory().toString(), "SCREEN"
                    + System.currentTimeMillis() + ".png"));
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
