package pe.gob.munihuacho.munimovil.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.adapters.ResumenCtaCteAdapter;
import pe.gob.munihuacho.munimovil.model.Detalle;
import pe.gob.munihuacho.munimovil.model.Resumen;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;

public class CtaCteDetailActivity extends AppCompatActivity {
    /*WEB SERVICES STRING*/
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDeCuentaCorrienteWS/ConsultarCuentaCorriente?WSDL";
    static final String NAMESPACE="http://Consultar/";
    static final String METHODNAME="resumenCuentaCorriente";
    static final String SOAP_ACTION=NAMESPACE+METHODNAME;
    /*Vistas*/
    RecyclerView RvResumenContribuyente;
    LinearLayout contenido;
    TextView tvctacteTotDeuda,tvctacteTotPagos,tvctacteTotPendiente;
    /*KEYS*/
    protected static final String DETALLE_LISTA="param1";
    protected static final String DETALLE_CONTRIB="param2";
    /*INTENT*/
    Serializable detalle=new ArrayList();
    List<Object> cont=new Vector<Object>();
    String contrib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cta_cte_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RvResumenContribuyente=(RecyclerView)findViewById(R.id.RvResumenContribuyente);
        RvResumenContribuyente.setLayoutManager(new LinearLayoutManager(this));
        contenido=(LinearLayout)findViewById(R.id.cont_detalle_ctacte);
        tvctacteTotDeuda=(TextView)findViewById(R.id.tvctacteTotDeuda);
        tvctacteTotPagos=(TextView)findViewById(R.id.tvctacteTotPagos);
        tvctacteTotPendiente=(TextView)findViewById(R.id.tvctacteTotPendiente);
        detalle=getIntent().getSerializableExtra(DETALLE_LISTA);

        contrib=getIntent().getStringExtra(DETALLE_CONTRIB);
        cont.add(contrib);
      //  new SoapActionResumen(CtaCteDetailActivity.this).execute(detalle);
    }
    private class SoapActionResumen extends AsynctaskWithDelayedIndeterminateProgress<ArrayList, Integer,String>{
        String notEthernetConnection="";
        String notFoundMessage="";
        Resumen res;
        ArrayList<Resumen> arrayResumen=new ArrayList<Resumen>();
        protected SoapActionResumen(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(ArrayList... params) {
            if (new CheckNetwork(CtaCteDetailActivity.this).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("contrib",params[0].get(0).toString());
                request.addProperty("listaDetalle",params[1]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try{
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject obj1=(SoapObject)envelope.bodyIn;
                    if (obj1.getPropertyCount()>0){
                        for (int i=0;i<obj1.getPropertyCount();i++){
                            SoapObject obj2=(SoapObject)obj1.getProperty(i);
                            res=new Resumen();
                            res.setDescripcion(obj2.getProperty("descripcion").toString());
                            res.setDeuda(Double.parseDouble(obj2.getProperty("deuda").toString()));
                            res.setPagos(Double.parseDouble(obj2.getProperty("deuda").toString()));
                            arrayResumen.add(res);
                        }
                    }
                    else{
                        notFoundMessage="No se encontro datos";
                    }
                }catch (Exception ex){
                    notFoundMessage=ex.getMessage();
                }
            }else{
                notEthernetConnection="Fallo en la conexion";
            }
            return null;
        }
        @Override
        protected void onPostExecute(String children) {
            if(!notEthernetConnection.equals("")){
                super.onPostExecute(children);
                Toast.makeText(CtaCteDetailActivity.this, notEthernetConnection, Toast.LENGTH_SHORT).show();
            }else{
                if(notFoundMessage.equals("")){
                    super.onPostExecute(children);
                    contenido.setVisibility(View.VISIBLE);
                    ResumenCtaCteAdapter adapter=new ResumenCtaCteAdapter(CtaCteDetailActivity.this,arrayResumen);
                    RvResumenContribuyente.setAdapter(adapter);
                }else{
                    super.onPostExecute(children);
                    Toast.makeText(CtaCteDetailActivity.this, notFoundMessage, Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
