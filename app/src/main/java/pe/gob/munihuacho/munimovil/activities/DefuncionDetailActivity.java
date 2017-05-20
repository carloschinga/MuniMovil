package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
    }
}
