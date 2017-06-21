package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.model.Nacimiento;

public class NacimientoDetailActivity extends AppCompatActivity {
    protected static final String DETALLE_NACIMIENTO = "nacimiento_detalle";
    Nacimiento naci;
    TextView tvPaternoNacimiento,
            tvMaternoNacimiento,
            tvNombresNacimiento,
            tvNacimiento,
            tvInscripcionNacimiento,
            tvSexoNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacimiento_detail);
        naci=getIntent().getParcelableExtra(DETALLE_NACIMIENTO);
        setTitle("Detalle de Nacimiento");
        if(naci==null){
            throw  new  NullPointerException("Array list received is null");
        }
        tvPaternoNacimiento=(TextView)findViewById(R.id.tvPaternoNacimiento);
        tvMaternoNacimiento=(TextView)findViewById(R.id.tvMaternoNacimiento);
        tvNombresNacimiento=(TextView)findViewById(R.id.tvNombresNacimiento);
        tvNacimiento=(TextView)findViewById(R.id.tvNacimiento);
        tvInscripcionNacimiento=(TextView)findViewById(R.id.tvInscripcionNacimiento);
        tvSexoNacimiento=(TextView)findViewById(R.id.tvSexoNacimiento);

        tvPaternoNacimiento.setText(naci.getPaterno());
        tvMaternoNacimiento.setText(naci.getMaterno());
        tvNombresNacimiento.setText(naci.getNombres());
        tvInscripcionNacimiento.setText(naci.getFecha_inscripcion());
        tvSexoNacimiento.setText(naci.getSexo());
        tvNacimiento.setText(naci.getFechanac());
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
}
