package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.CtaNoCteAdapter;
import pe.gob.munihuacho.munimovil.model.CajaCentral;

public class CcCtaAhorroActivity extends AppCompatActivity {
    //keys for Cuenta Corriente
    ArrayList<CajaCentral> list;
    String listakey="cajacentral";
    TextView tvLiqCtaNoCte,
            tvMovCtaNoCte,
            tvCajeroCtaNoCte,
            tvNombreCtaNoCte,
            tvObservacionCtaNoCte,
            tvTotalCtaNoCte,
            tvUserCtaNoCte,
            tvFechaCtaNoCte,
            tvHoraCtaNoCte;
    String liq="";
    String mov="";
    String caj="";
    String nom="";
    String obs="";
    String tot="";
    String usu="";
    String fec="";
    String hor="";
    RecyclerView RvCtaNoCte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_cta_ahorro);
        setTitle("Cuenta No Corriente");
        list=getIntent().getParcelableArrayListExtra(listakey);
        if(list.size()==0){
            return;
        }
                tvLiqCtaNoCte=(TextView)findViewById(R.id.tvLiqCtaNoCte);
                tvMovCtaNoCte=(TextView)findViewById(R.id.tvMovCtaNoCte);
                tvCajeroCtaNoCte=(TextView)findViewById(R.id.tvCajeroCtaNoCte);
                tvNombreCtaNoCte=(TextView)findViewById(R.id.tvNombreCtaNoCte);
                tvObservacionCtaNoCte=(TextView)findViewById(R.id.tvObservacionCtaNoCte);
                tvTotalCtaNoCte=(TextView)findViewById(R.id.tvTotalCtaNoCte);
                tvUserCtaNoCte=(TextView)findViewById(R.id.tvUserCtaNoCte);
                tvFechaCtaNoCte=(TextView)findViewById(R.id.tvFechaCtaNoCte);
                tvHoraCtaNoCte=(TextView)findViewById(R.id.tvHoraCtaNoCte);
                RvCtaNoCte=(RecyclerView)findViewById(R.id.RvCtaNoCte);
        RvCtaNoCte.setLayoutManager(new LinearLayoutManager(this));
            for(Iterator it=list.iterator();it.hasNext();){
                Object obj=it.next();
                CajaCentral cj=(CajaCentral)obj;
                liq=cj.getLiq().trim();
                caj=cj.getCaja().trim();
                mov=cj.getMovimiento().trim();
                nom=cj.getNombre().trim();
                obs=cj.getObservacion().trim();
                tot=cj.getTotal().trim();
                usu=cj.getUsuario().trim();
                fec=cj.getFecha().trim();
                hor=cj.getHora().trim();
            }
            CtaNoCteAdapter adapter=new CtaNoCteAdapter(this,list);
                RvCtaNoCte.setAdapter(adapter);
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
