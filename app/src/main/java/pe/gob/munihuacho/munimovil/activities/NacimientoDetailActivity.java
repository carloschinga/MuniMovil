package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
}
