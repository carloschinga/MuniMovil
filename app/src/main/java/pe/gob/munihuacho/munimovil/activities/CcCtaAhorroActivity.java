package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.CtaNoCteAdapter;

public class CcCtaAhorroActivity extends AppCompatActivity {
    //keys for Cuenta Corriente
    String tributo="tributo";
    String contribuyente="contribuyente";
    String movimiento="movimiento";
    String nombre="nombre";
    String deuda="deuda";
    String emision="emision";
    String morein="morein";
    String periodo="periodo";
    String total="total";
    String hora="hora";
    String usuario="usuario";
    String fecha="fecha";
    String caja="caja";
    ArrayList<String> importe;
    ArrayList<String> concepto;
    //keys for Cuenta no corriente (ahorro)
    String listaConc="listaConcepto";
    String listaImp="listaImporte";
    String liquidacion="liquidacion";
    String observacion="observacion";
    TextView tvLiqCtaNoCte,
            tvMovCtaNoCte,
            tvCajeroCtaNoCte,
            tvNombreCtaNoCte,
            tvObservacionCtaNoCte,
            tvTotalCtaNoCte,
            tvUserCtaNoCte,
            tvFechaCtaNoCte,
            tvHoraCtaNoCte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_cta_ahorro);
        setTitle("Cuenta No Corriente");
        String liq=getIntent().getStringExtra(liquidacion);
        String mov=getIntent().getStringExtra(movimiento);
        String caj=getIntent().getStringExtra(caja);
        String nom=getIntent().getStringExtra(nombre);
        String obs=getIntent().getStringExtra(observacion);
        importe=getIntent().getStringArrayListExtra(listaImp);
        concepto=getIntent().getStringArrayListExtra(listaConc);
        if(importe.size()==0){
            return;
        }
        String usu=getIntent().getStringExtra(usuario);
        String fec=getIntent().getStringExtra(fecha);
        String hor=getIntent().getStringExtra(hora);
        String tot=getIntent().getStringExtra(total);
        tvLiqCtaNoCte=(TextView)findViewById(R.id.tvLiqCtaNoCte);
                tvMovCtaNoCte=(TextView)findViewById(R.id.tvMovCtaNoCte);
                tvCajeroCtaNoCte=(TextView)findViewById(R.id.tvCajeroCtaNoCte);
                tvNombreCtaNoCte=(TextView)findViewById(R.id.tvNombreCtaNoCte);
                tvObservacionCtaNoCte=(TextView)findViewById(R.id.tvObservacionCtaNoCte);
                tvTotalCtaNoCte=(TextView)findViewById(R.id.tvTotalCtaNoCte);
                tvUserCtaNoCte=(TextView)findViewById(R.id.tvUserCtaNoCte);
                tvFechaCtaNoCte=(TextView)findViewById(R.id.tvFechaCtaNoCte);
                tvHoraCtaNoCte=(TextView)findViewById(R.id.tvHoraCtaNoCte);


        tvLiqCtaNoCte.setText(liq);
                tvMovCtaNoCte.setText(mov);
                tvCajeroCtaNoCte.setText(caj);
                tvNombreCtaNoCte.setText(nom);
                tvObservacionCtaNoCte.setText(obs);
                tvTotalCtaNoCte.setText(tot);
                tvUserCtaNoCte.setText(usu);
                tvFechaCtaNoCte.setText(fec);
                tvHoraCtaNoCte.setText(hor);





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
