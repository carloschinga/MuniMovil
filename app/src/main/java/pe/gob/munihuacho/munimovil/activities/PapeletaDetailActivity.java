package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
        setTitle("Detalle de Papeleta");
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
