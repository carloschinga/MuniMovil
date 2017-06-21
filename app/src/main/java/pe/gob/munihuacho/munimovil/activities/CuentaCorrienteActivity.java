package pe.gob.munihuacho.munimovil.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.model.Contribuyente;
import pe.gob.munihuacho.munimovil.model.Detalle;
import pe.gob.munihuacho.munimovil.model.Papeleta;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;

public class CuentaCorrienteActivity extends AppCompatActivity {

    /*WEB SERVICES STRINGS*/
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDeCuentaCorrienteWS/ConsultarCuentaCorriente?WSDL";
    static final String NAMESPACE="http://Consultar/";
    static final String METHODNAME="seleccionarContribuyente";
    static final String SOAP_ACTION=NAMESPACE+METHODNAME;
    static final String METHODCONSULTA="detalleCuentaCorriente";
    static final String SOAP_ACTION_CONS=NAMESPACE+METHODCONSULTA;
    /*TEXT VIEWS*/
    TextView tvctactaNombres,
            tvctacteDireccion,
            tvctacteCodigo;
    Button btnctacteVerEstado;
    String usuario;
    ConstraintLayout contenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_corriente);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Consulta de Cuenta Corriente");
        tvctactaNombres=(TextView)findViewById(R.id.tvctactaNombres);
        tvctacteDireccion=(TextView)findViewById(R.id.tvctacteDireccion);
        tvctacteCodigo=(TextView)findViewById(R.id.tvctacteCodigo);
        btnctacteVerEstado=(Button)findViewById(R.id.btnctacteVerEstado);
        usuario=getIntent().getStringExtra("user");
        contenido=(ConstraintLayout)findViewById(R.id.cont_cta_cte);
        new SoapAction(this).execute(usuario);
        btnctacteVerEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //new SoapCargarDetalle(CuentaCorrienteActivity.this).execute(tvctacteCodigo.getText().toString());
                Toast.makeText(CuentaCorrienteActivity.this, "Estamos trabajando en esta zona...", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class SoapAction extends AsynctaskWithDelayedIndeterminateProgress<String, Integer, String>{
        String notEthernetConnection="";
        Contribuyente cont=new Contribuyente();
        protected SoapAction(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(CuentaCorrienteActivity.this).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("contrib",params[0]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try{
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject response=(SoapObject)envelope.getResponse();
                    if(response.getPropertyCount()>0){
                        cont.setNomb(response.getProperty("nomb").toString());
                        cont.setDirecc(response.getProperty("direcc").toString());
                    }
                }catch (Exception ex){
                }
            }else{
                notEthernetConnection="Se perdio la conexión a internet";
            }
            return null;
        }
        @Override
        protected void onPostExecute(String children) {
            if(!notEthernetConnection.equals("")){
                super.onPostExecute(children);
                Toast.makeText(CuentaCorrienteActivity.this, notEthernetConnection, Toast.LENGTH_SHORT).show();
                btnctacteVerEstado.setEnabled(false);
            }else{
                super.onPostExecute(children);
                tvctacteCodigo.setText(usuario);
                tvctactaNombres.setText(cont.getNomb());
                tvctacteDireccion.setText(cont.getDirecc());
                contenido.setVisibility(View.VISIBLE);
            }
        }
    }
    private class SoapCargarDetalle extends  AsynctaskWithDelayedIndeterminateProgress<String, Integer,String>{
        String notEthernetConnection="";
        String notFoundMessage="";
        Detalle det;
        ArrayList<Detalle> detalleArrayList=new ArrayList<Detalle>();
        protected SoapCargarDetalle(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(CuentaCorrienteActivity.this).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODCONSULTA);
                request.addProperty("contrib",params[0]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try{
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION_CONS,envelope);
                    SoapObject obj1=(SoapObject)envelope.bodyIn;
                    if (obj1.getPropertyCount()>0){
                        for (int i=0;i<obj1.getPropertyCount();i++){
                            SoapObject obj2=(SoapObject)obj1.getProperty(i);
                            det=new Detalle();
                            det.setTributo(obj2.getProperty("tributo").toString());
                            det.setTrides(obj2.getProperty("trides").toString());
                            //det.setAini(obj2.getProperty("aini").toString());
                            //det.setPeini(obj2.getProperty("peini").toString());
                            det.setPeriodo(obj2.getProperty("periodo").toString());
                            det.setInsoluto(Double.parseDouble(obj2.getProperty("insoluto").toString()));
                            det.setSobretasas(Double.parseDouble(obj2.getProperty("sobretasas").toString()));
                            det.setPagos(Double.parseDouble(obj2.getProperty("pagos").toString()));
                            det.setSaldo(Double.parseDouble(obj2.getProperty("saldo").toString()));
                            det.setSecid(Integer.parseInt(obj2.getProperty("secid").toString()));
                            detalleArrayList.add(det);
                        }
                    }else{
                        notFoundMessage="Hubo un error, no se encontraron datos";
                    }
                }catch (Exception ex){
                    notFoundMessage=ex.getMessage();
                }
            }else{
                notEthernetConnection="No hay conexión a internet";
            }
            return null;
        }
        @Override
        protected void onPostExecute(String children) {
            if(notEthernetConnection.equals("")){
                if(notFoundMessage.equals("")){
                super.onPostExecute(children);
                Intent intent=new Intent(getApplicationContext(),CtaCteDetailActivity.class);
                intent.putExtra(CtaCteDetailActivity.DETALLE_LISTA,detalleArrayList);
                intent.putExtra(CtaCteDetailActivity.DETALLE_CONTRIB,usuario);
                startActivity(intent);
                }else{
                    super.onPostExecute(children);
                    Toast.makeText(CuentaCorrienteActivity.this, notFoundMessage, Toast.LENGTH_SHORT).show();
                }
            }else{
                super.onPostExecute(children);
                Toast.makeText(CuentaCorrienteActivity.this, notEthernetConnection, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sesion, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        yes_no_dialog();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_logout){
            yes_no_dialog();
        }
        return super.onOptionsItemSelected(item);
    }
    //Dialogo de Si / No opciones para asegurar cierre de sesión
    public void yes_no_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Estass seguro?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.dismiss();
                Intent intent=new Intent(CuentaCorrienteActivity.this,LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
