package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.model.Papeleta;

public class PapeletaDetailActivity extends AppCompatActivity {
    protected static final String DETALLE_PAPELETA = "papeleta_detalle";
   Papeleta pape;
    TextView tvdniPapeleta,
            tvfechaPapeleta,
            tvNumPapeleta,
            tvTipoPapeleta,
            tvPlacaPapeleta,
            tvMontoPapeleta,
            tvEstadoPapeleta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papeleta_detail);
        pape=getIntent().getParcelableExtra(DETALLE_PAPELETA);
        setTitle("Papeleta");
        if(pape==null){
            throw  new  NullPointerException("Array list received is null");
        }
        tvdniPapeleta=(TextView)findViewById(R.id.tvdniPapeleta);
        tvfechaPapeleta=(TextView)findViewById(R.id.tvfechaPapeleta);
        tvNumPapeleta=(TextView)findViewById(R.id.tvNumPapeleta);
        tvTipoPapeleta=(TextView)findViewById(R.id.tvTipoPapeleta);
        tvPlacaPapeleta=(TextView)findViewById(R.id.tvPlacaPapeleta);
        tvMontoPapeleta=(TextView)findViewById(R.id.tvMontoPapeleta);
        tvEstadoPapeleta=(TextView)findViewById(R.id.tvEstadoPapeleta);

        tvdniPapeleta.setText(pape.getDni());
        tvfechaPapeleta.setText(pape.getFechaInfraccion());
        tvNumPapeleta.setText(pape.getNume_pape());
        tvTipoPapeleta.setText(pape.getCodi_inte());
        tvPlacaPapeleta.setText(pape.getPlac_dttr());
        tvMontoPapeleta.setText(String.valueOf(pape.getTota_pago()));
        tvEstadoPapeleta.setText(pape.getDesc_esta());

    }
}
