package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.model.Defuncion;
import pe.gob.munihuacho.munimovil.model.Nacimiento;

public class DefuncionDetailActivity extends AppCompatActivity {
    protected static final String DETALLE_DEFUNCION = "defuncion_detalle";
    Defuncion difu;
    TextView tvPaternoDefuncion,
            tvMaternoDefuncion,
            tvNombresDefuncion,
            tvDefuncion,
            tvInscripcionDefuncion,
            tvSexoDefuncion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defuncion_detail);
        setTitle("Detalle de Defunción");
        difu=getIntent().getParcelableExtra(DETALLE_DEFUNCION);
        if(difu==null){
            throw  new  NullPointerException("Array list received is null");
        }
        tvPaternoDefuncion=(TextView)findViewById(R.id.tvPaternoDefuncion);
        tvMaternoDefuncion=(TextView)findViewById(R.id.tvMaternoDefuncion);
        tvNombresDefuncion=(TextView)findViewById(R.id.tvNombresDefuncion);
        tvDefuncion=(TextView)findViewById(R.id.tvDefuncion);
        tvInscripcionDefuncion=(TextView)findViewById(R.id.tvInscripcionDefuncion);
        tvSexoDefuncion=(TextView)findViewById(R.id.tvSexoDefuncion);

        tvPaternoDefuncion.setText(difu.getPaterno());
        tvMaternoDefuncion.setText(difu.getMaterno());
        tvNombresDefuncion.setText(difu.getNombres());
        tvInscripcionDefuncion.setText(difu.getFecha());
        tvSexoDefuncion.setText(difu.getSexo());
        tvDefuncion.setText(difu.getFechadef());
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
