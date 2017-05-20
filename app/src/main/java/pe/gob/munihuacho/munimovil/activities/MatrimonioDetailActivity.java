package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.model.Matrimonio;

public class MatrimonioDetailActivity extends AppCompatActivity {
    TextView tvPaternoMat,
            tvMaternoMat,
            tvNombresMat,
            tvPaternoDonMat,
            tvMaternoDonMat,
            tvNombresDonMat,
            tvFechMat,
            tvInscripMat;
    Matrimonio mat;
    protected static final String DETALLE_MATRIMONIO = "matrimonio_detalle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrimonio_detail);
        setTitle("Matrimonio");
        mat=getIntent().getParcelableExtra(DETALLE_MATRIMONIO);
        if(mat==null){
            throw  new  NullPointerException("Array list received is null");
        }
        tvPaternoMat=(TextView)findViewById(R.id.tvPaternoMat);
        tvMaternoMat=(TextView)findViewById(R.id.tvMaternoMat);
        tvNombresMat=(TextView)findViewById(R.id.tvNombresMat);
        tvFechMat=(TextView)findViewById(R.id.tvFechMat);
        tvInscripMat=(TextView)findViewById(R.id.tvInscripMat);
        tvPaternoDonMat=(TextView)findViewById(R.id.tvPaternoDonMat);
        tvMaternoDonMat=(TextView)findViewById(R.id.tvMaternoDonMat);
        tvNombresDonMat=(TextView)findViewById(R.id.tvNombresDonMat);


       tvPaternoMat.setText(mat.getPaterno());
        tvMaternoMat.setText(mat.getMaterno());
        tvNombresMat.setText(mat.getNombres());
        tvFechMat.setText(mat.getFechamat());
        tvInscripMat.setText(mat.getFecha());
        tvPaternoDonMat.setText(mat.getPaternodon());
        tvMaternoDonMat.setText(mat.getMaternodon());
        tvNombresDonMat.setText(mat.getNombresdon());

    }
}
