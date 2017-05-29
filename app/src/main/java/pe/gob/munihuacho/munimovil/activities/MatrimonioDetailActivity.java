package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
        setTitle("Detalle de Matrimonio");
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
