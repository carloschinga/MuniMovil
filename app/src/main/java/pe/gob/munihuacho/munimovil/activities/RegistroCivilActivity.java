package pe.gob.munihuacho.munimovil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import pe.gob.munihuacho.munimovil.R;

public class RegistroCivilActivity extends AppCompatActivity {
    TextView tvCajeroRC;
    TextView tvFechaRC;
    TextView tvNomYApe;
    TextView tvTipoPartidaRc;
    TextView tvLibRc;
    TextView tvImporte;
    TextView tvFolioRC;
    TextView tvAnioRegistroRC;
    TextView tvCantidad;
    TextView tvTotalAPagar;
    protected static final String REGISTROCIVIL_TRIBUTO = "tributo";
    protected static final String REGISTROCIVIL_CONTRIBUYENTE = "contribuyente";
    protected static final String REGISTROCIVIL_MOVIMIENTO = "movimiento";
    protected static final String REGISTROCIVIL_NOMBRE = "nombre";
    protected static final String REGISTROCIVIL_PERIODO = "periodo";
    protected static final String REGISTROCIVIL_DEUDA = "deuda";
    protected static final String REGISTROCIVIL_EMISION = "emision";
    protected static final String REGISTROCIVIL_MOREIN = "morein";
    protected static final String REGISTROCIVIL_FECHA = "fecha";
    protected static final String REGISTROCIVIL_HORA = "hora";
    protected static final String REGISTROCIVIL_TOTAL = "total";
    protected static final String REGISTROCIVIL_USUARIO = "usuario";
    protected static final String REGISTROCIVIL_CAJA = "caja";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_civil);
        setTitle("Pagos en registro civil");
        /**DATOS**/
        String usuario=getIntent().getStringExtra("usuario");
        String libro=getIntent().getStringExtra("libro");
        String nombrecompleto=getIntent().getStringExtra("nombrecompleto");
        String folio=getIntent().getStringExtra("folio");
        String añore=getIntent().getStringExtra("añore");
        String importe=getIntent().getStringExtra("importe");
        String fecha=getIntent().getStringExtra("fecha");
        String cantidad= getIntent().getStringExtra("cantidad");
        String total=getIntent().getStringExtra("total");
        String tipo=getIntent().getStringExtra("tipo");
        /**CAMPOS**/
        tvCajeroRC=(TextView)findViewById(R.id.tvCajeroRC);
        tvFechaRC=(TextView)findViewById(R.id.tvFechaRC);
        tvTipoPartidaRc=(TextView)findViewById(R.id.tvTipoPartidaRc);
        tvLibRc=(TextView)findViewById(R.id.tvLibRC);
        tvFolioRC=(TextView)findViewById(R.id.tvFolioRC);
        tvAnioRegistroRC=(TextView)findViewById(R.id.tvAnioRegistroRC);
        tvImporte=(TextView)findViewById(R.id.tvImporte);
        tvCantidad=(TextView)findViewById(R.id.tvCantidad);
        tvTotalAPagar=(TextView)findViewById(R.id.tvTotalAPagar);
        tvNomYApe=(TextView)findViewById(R.id.tvNomYApe);
        /**ASIGNACION**/
        tvCajeroRC.setText(usuario);
        tvLibRc.setText("Lib: "+libro);
        tvTipoPartidaRc.setText(tipo);
        tvFechaRC.setText(fecha);
        tvFolioRC.setText("Folio: "+folio);
        tvNomYApe.setText(nombrecompleto);
        tvAnioRegistroRC.setText("Año registro: "+añore);
        tvImporte.setText(importe);
        tvCantidad.setText(cantidad);
        tvTotalAPagar.setText(total);
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
