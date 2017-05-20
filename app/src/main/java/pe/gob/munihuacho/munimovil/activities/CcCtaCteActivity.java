package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import pe.gob.munihuacho.munimovil.R;

public class CcCtaCteActivity extends AppCompatActivity {
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

    //keys for Cuenta no corriente (ahorro)
    String listaConc="listaConcepto";
    String listaImp="listaImporte";
    String liquidacion="liquidacion";

    //Textviews
    TextView tvTributoCcte,
            tvContribuyenteCcte,
            tvMovCcte,
            tvCajeroCcte,
            tvNombreCcte,
            tvTolDeudaCcte,
            tvTolEmisionCcte,
            tvTolMeReInCcte,
            tvUsuarioCcte,
            tvFechaHoraCcte,
            tvTotalCcte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_cta_cte);
        setTitle("Cuenta corriente");
        //get data from fragment
        String trib=getIntent().getStringExtra("tributo");
        String cont=getIntent().getStringExtra(contribuyente);
        String mov=getIntent().getStringExtra(movimiento);
        String caj=getIntent().getStringExtra(caja);
        String nom=getIntent().getStringExtra(nombre);
        String toldeu=getIntent().getStringExtra(deuda);
        String tolemi=getIntent().getStringExtra(emision);
        String tolmor=getIntent().getStringExtra(morein);
        String tot=getIntent().getStringExtra(total);
        String user=getIntent().getStringExtra(usuario);
        String  fec=getIntent().getStringExtra(fecha);

        //find textviews
        tvTributoCcte=(TextView)findViewById(R.id.tvTributoCcte);
        tvContribuyenteCcte=(TextView)findViewById(R.id.tvContribuyenteCcte);
        tvMovCcte=(TextView)findViewById(R.id.tvMovCcte);
        tvCajeroCcte=(TextView)findViewById(R.id.tvCajeroCcte);
        tvNombreCcte=(TextView)findViewById(R.id.tvNombreCcte);
        tvTolDeudaCcte=(TextView)findViewById(R.id.tvTolDeudaCcte);
        tvTolEmisionCcte=(TextView)findViewById(R.id.tvTolEmisionCcte);
        tvTolMeReInCcte=(TextView)findViewById(R.id.tvTolMeReInCcte);
        tvUsuarioCcte=(TextView)findViewById(R.id.tvUsuarioCcte);
        tvFechaHoraCcte=(TextView)findViewById(R.id.tvFechaHoraCcte);
        tvTotalCcte=(TextView)findViewById(R.id.tvTotalCcte);

        //set data to textviews
        tvTributoCcte.setText(trib);
        tvContribuyenteCcte.setText(cont);
        tvMovCcte.setText(mov);
        tvCajeroCcte.setText(caj);
        tvNombreCcte.setText(nom);
        tvTolDeudaCcte.setText(toldeu);
        tvTolEmisionCcte.setText(tolemi);
        tvTolMeReInCcte.setText(tolmor);
        tvUsuarioCcte.setText(user);
        tvFechaHoraCcte.setText(fec);
        tvTotalCcte.setText(tot);

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
