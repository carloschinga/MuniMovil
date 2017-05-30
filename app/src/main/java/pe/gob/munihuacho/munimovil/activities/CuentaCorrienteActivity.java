package pe.gob.munihuacho.munimovil.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.model.Contribuyente;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;

public class CuentaCorrienteActivity extends AppCompatActivity {

    /*WEB SERVICES STRINGS*/
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDeCuentaCorrienteWS/ConsultarCuentaCorriente?WSDL";
    static final String NAMESPACE="http://Consultar/";
    static final String METHODNAME="seleccionarContribuyente";
    static final String SOAP_ACTION=NAMESPACE+METHODNAME;
    /*TEXT VIEWS*/
    TextView tvctactaNombres,
            tvctacteDireccion,
            tvctacteCodigo;
    Button btnctacteVerEstado;
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_corriente);
        setTitle("Consulta de Cuenta Corriente");
        tvctactaNombres=(TextView)findViewById(R.id.tvctactaNombres);
        tvctacteDireccion=(TextView)findViewById(R.id.tvctacteDireccion);
        tvctacteCodigo=(TextView)findViewById(R.id.tvctacteCodigo);
        btnctacteVerEstado=(Button)findViewById(R.id.btnctacteVerEstado);
        usuario=getIntent().getStringExtra("user");
        new SoapAction(this).execute(usuario);
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
            super.onPostExecute(children);
            if(!notEthernetConnection.equals("")){
                Toast.makeText(CuentaCorrienteActivity.this, notEthernetConnection, Toast.LENGTH_SHORT).show();
                btnctacteVerEstado.setEnabled(false);
            }else{
                tvctacteCodigo.setText(usuario);
                tvctactaNombres.setText(cont.getNomb());
                tvctacteDireccion.setText(cont.getDirecc());
            }

        }
    }
}
