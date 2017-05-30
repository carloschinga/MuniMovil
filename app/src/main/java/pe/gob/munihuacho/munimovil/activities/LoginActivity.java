package pe.gob.munihuacho.munimovil.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pe.gob.munihuacho.munimovil.MainActivity;
import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.util.Caracteres;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;

public class LoginActivity extends AppCompatActivity {
    Button button_ingresar_sistema;
    EditText input_user, input_password;
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDeCuentaCorrienteWS/ConsultarCuentaCorriente?WSDL";
    static final String NAMESPACE="http://Consultar/";
    static final String METHODNAME="inicioDeSesion";
    static final String SOAP_ACTION=NAMESPACE+METHODNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        input_user=(EditText)findViewById(R.id.input_user);
        input_password=(EditText)findViewById(R.id.input_password);
        button_ingresar_sistema=(Button)findViewById(R.id.button_ingresar_sistema);
        final String paramuser[]={""};
        final String parampass[]={""};
        final Caracteres hacer=new Caracteres();
        button_ingresar_sistema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paramuser[0]=hacer.modificarUsuario(input_user.getText().toString().trim());
                parampass[0]=input_password.getText().toString().trim();
                new SoapAction(LoginActivity.this).execute(paramuser[0],parampass[0]);
            }
        });
    }
    private class SoapAction extends AsynctaskWithDelayedIndeterminateProgress<String, Integer,String>{
        String usuario="";
        String notEthernetConnection="";
        String loginMessage="";
        protected SoapAction(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(LoginActivity.this).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("contrib",params[0]);
                request.addProperty("password",params[1]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try{
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject response= response=(SoapObject)envelope.bodyIn;
                    if(response.getPropertyCount()>0){
                        //handle set data from  soap call
                        loginMessage=response.getProperty(0).toString();
                        usuario=params[0];
                    }else{
                        //handle not found user message
                    }
                }catch (Exception ex){
                        //handle connection timeout
                }
            }else{
                notEthernetConnection="No hay conexión a internet";
            }
            return null;
        }
        @Override
        protected void onPostExecute(String children) {
            super.onPostExecute(children);
            //verify data and then intent to activity result
            //if login message is exito do intent
            if(loginMessage.equals("Exito")){
                //Intent
                Intent intent=new Intent(getApplicationContext(),CuentaCorrienteActivity.class);
                //pass data
                intent.putExtra("user",usuario);
                finish();
                startActivity(intent);
            }else if(loginMessage.equals("La contraseña no es correcta.")){
                Toast.makeText(LoginActivity.this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
