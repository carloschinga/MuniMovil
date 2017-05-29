package pe.gob.munihuacho.munimovil.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Calendar;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.activities.CcCtaAhorroActivity;
import pe.gob.munihuacho.munimovil.activities.CcCtaCteActivity;
import pe.gob.munihuacho.munimovil.activities.RegistroCivilActivity;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.model.Caja;
import pe.gob.munihuacho.munimovil.model.CajaCentral;
import pe.gob.munihuacho.munimovil.model.CajaCentralCc;
import pe.gob.munihuacho.munimovil.util.Caracteres;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;
import pe.gob.munihuacho.munimovil.util.Comunes;
import pe.gob.munihuacho.munimovil.util.DatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarPagoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarPagoFragment extends Fragment {
    TabHost tbhPago;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /**REGISTRO CIVIL*/
    Button btnConsultaRc;
    //Campos del formulario
    EditText recibo;

    /**CAJA CENTRAL*/
    Button btnConsultaCc;
    EditText input_fecha;
    EditText numMovimiento;
    EditText tvCajeroCC;

    private String mParam1;
    private String mParam2;

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
    String listakey="cajacentral";
    String observacion="observacion";
    //keys for Cuenta no corriente (ahorro)
    String listaConc="listaConcepto";
    String listaImp="listaImporte";
    String liquidacion="liquidacion";
    public ConsultarPagoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarPagoFragment.
     */

    public static ConsultarPagoFragment newInstance(String param1, String param2) {
        ConsultarPagoFragment fragment = new ConsultarPagoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_consultar_pago, container, false);
        /**INICIO DE TAB HOST*/
        tbhPago=(TabHost)view.findViewById(R.id.tabHostPago);
        tbhPago.setup();
        TabHost.TabSpec tab1=tbhPago.newTabSpec("tab1");
        TabHost.TabSpec tab2=tbhPago.newTabSpec("tab2");
        tab1.setIndicator("Registro Civil");
        tab1.setContent(R.id.Registro_Civil);
        tab2.setIndicator("Caja Central");
        tab2.setContent(R.id.Caja_Central);
        tbhPago.addTab(tab1);
        tbhPago.addTab(tab2);
        /**FIN DE TAB HOST*/
        btnConsultaRc=(Button)view.findViewById(R.id.btnConsultaRC);
        recibo=(EditText)view.findViewById(R.id.input_num_recibo);
        btnConsultaCc=(Button)view.findViewById(R.id.btnBuscarCC);
        final String[] parametro = {""};
        btnConsultaRc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parametro[0] =recibo.getText().toString().trim();
                if(recibo.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Por favor Ingrese un dato valido", Toast.LENGTH_SHORT).show();
                }else{
                    //handle soap action
                    new SoapRegistroCivil(getActivity()).execute(parametro[0]);
                }
            }
        });

        input_fecha=(EditText)view.findViewById(R.id.input_fecha);
        input_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        numMovimiento=(EditText)view.findViewById(R.id.numMovimiento);
        final Caracteres hacer=new Caracteres();
        tvCajeroCC=(EditText)view.findViewById(R.id.tvCajeroCC);
        final String[] paramfecha = {""};
        final String[] parammovi = {""};
        final String[] paramcaja={""};
        btnConsultaCc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paramfecha[0]=input_fecha.getText().toString().trim();
                parammovi[0]=numMovimiento.getText().toString().trim();
                paramcaja[0]=tvCajeroCC.getText().toString().trim();
                if(paramfecha[0].isEmpty()){
                    Toast.makeText(getActivity(), "Ingresa una fecha", Toast.LENGTH_SHORT).show();
                }else if(numMovimiento.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Ingresa número de movimiento", Toast.LENGTH_SHORT).show();
                }else if(hacer.movimientoSet(numMovimiento.getText().toString().trim())){
                    Toast.makeText(getActivity(), "Número de movimiento tiene 7 digitos maximo", Toast.LENGTH_SHORT).show();

                }else if(tvCajeroCC.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Ingresa cajero valido", Toast.LENGTH_SHORT).show();
                }else{
                    //handle soap action
                    new SoapAction(getActivity()).execute(paramfecha[0],
                            parammovi[0],
                            paramcaja[0]);
                }
            }
        });
        return view;
    }
    /**DATE PICKER ACTIONS**/
    private void showDatePicker(){
        DatePickerFragment date=new DatePickerFragment();
        /*
        * Set up Current Date in Dialog
        */
        Calendar calendar=Calendar.getInstance();
        Bundle args=new Bundle();
        args.putInt("year",calendar.get(Calendar.YEAR));
        args.putInt("month",calendar.get(Calendar.MONTH));
        args.putInt("day",calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /*
        * Ser CallBack to capture selected Data
        */
        date.setCallBack(ondate);
        date.show(getFragmentManager(),"Date Picker");
    }
    DatePickerDialog.OnDateSetListener ondate=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Caracteres caracteres=new Caracteres();
            input_fecha.setText(String.valueOf(year)+"-"+caracteres.fechaSet(String.valueOf(month+1))+"-"+caracteres.fechaSet(String.valueOf(dayOfMonth)));
        }
    };
    private class SoapRegistroCivil extends AsynctaskWithDelayedIndeterminateProgress<String,Integer,String>{
        static final String METHODNAME="ConsultarRegistroCivil";
        static final String URL= "http://apps2.munihuacho.gob.pe:9090/ConsultaDePagoWS/ConsultarPagos?WSDL";
        static final String NAMESPACE="http://consultar/";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;

        //*Datos*//
        Caja caja=new Caja();
        String nombreCompleto;
        String notFoundMessage="";
        String notEthernetConnection="";

        protected SoapRegistroCivil(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("id",params[0]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try {
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject response=(SoapObject)envelope.getResponse();
                    if(response.getPropertyCount()>0){
                        //*Rescatar elementos del Output**/
                        caja.setUsuario(response.getProperty("usuario").toString());
                        caja.setLibro(response.getProperty("libro").toString());
                        caja.setNombres(response.getProperty("nombres").toString());
                        caja.setMaterno(response.getProperty("materno").toString());
                        caja.setPaterno(response.getProperty("paterno").toString());
                        caja.setFolio(response.getProperty("folio").toString());
                        caja.setAñore(response.getProperty("añore").toString());
                        caja.setImporte(response.getProperty("importe").toString());
                        caja.setFecha(response.getProperty("fecha").toString());
                        caja.setCantidad(response.getProperty("cantidad").toString());
                        caja.setTotal(response.getProperty("total").toString());
                        caja.setTipo(response.getProperty("tipo").toString());
                        //Concat full name
                        nombreCompleto=caja.getNombres() +" "+caja.getPaterno()+" "+caja.getMaterno();
                    }
                    else{
                        notFoundMessage="No se encontro datos";
                    }
                }catch (Exception ex){

                }
            }else{
                notEthernetConnection="No hay conexion a internet";
            }
            return null;
        }

        @Override
        protected void onPostExecute(String children) {
            if (notEthernetConnection!=""){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notEthernetConnection, Toast.LENGTH_SHORT).show();
            }else if (notFoundMessage!=""){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notFoundMessage, Toast.LENGTH_SHORT).show();
            }else{
                super.onPostExecute(children);
                Intent intent =new Intent(getActivity(),RegistroCivilActivity.class);
                intent.putExtra("usuario",caja.getUsuario());
                intent.putExtra("libro",caja.getLibro());
                intent.putExtra("nombres",caja.getNombres());
                intent.putExtra("materno",caja.getMaterno());
                intent.putExtra("paterno",caja.getPaterno());
                intent.putExtra("folio",caja.getFolio());
                intent.putExtra("añore",caja.getAñore());
                intent.putExtra("importe",caja.getImporte());
                intent.putExtra("fecha",caja.getFecha());
                intent.putExtra("cantidad",caja.getCantidad());
                intent.putExtra("total",caja.getTotal());
                intent.putExtra("tipo",caja.getTipo());
                intent.putExtra("nombrecompleto",nombreCompleto);
                startActivity(intent);
            }
        }
    }
    private class SoapAction extends AsynctaskWithDelayedIndeterminateProgress<String,String,String> {
        static final String URL= "http://apps2.munihuacho.gob.pe:9090/ConsultaDePagoWS/ConsultarPagos?WSDL";
        static final String NAMESPACE="http://consultar/";
        String METHODNAME="";
        CajaCentralCc cajaCentralCc=new CajaCentralCc();
        CajaCentral cajaCentral;
        ArrayList<CajaCentral> listaCajaCentral=new ArrayList<CajaCentral>();
        String notFoundMessage="";
        String notEthernetConnection="";
        protected SoapAction(Activity activity) {
            super(activity);
        }

        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                try {
                    METHODNAME="ConsultarCajaCentralCc";
                    String SOAP_ACTION=NAMESPACE+METHODNAME;
                    SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                    request.addProperty("fecha",params[0]);
                    request.addProperty("movimiento",params[1]);
                    request.addProperty("caja",params[2]);
                    SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet=false;
                    envelope.setOutputSoapObject(request);
                    HttpTransportSE transporte = new HttpTransportSE(URL);
                    transporte.call(SOAP_ACTION, envelope);
                    SoapObject response=(SoapObject)envelope.getResponse();
                    if(response.getPropertyCount()>0){
                        //ASINGAR PROPIEDADES
                        cajaCentralCc.setTributo(response.getProperty("tributo").toString());
                        cajaCentralCc.setContribuyente(response.getProperty("contribuyente").toString());
                        cajaCentralCc.setMovimiento(response.getProperty("movimiento").toString());
                        cajaCentralCc.setNombre(response.getProperty("nombre").toString());
                        cajaCentralCc.setDeuda(response.getProperty("deuda").toString());
                        cajaCentralCc.setEmision(response.getProperty("emision").toString());
                        cajaCentralCc.setMorein(response.getProperty("morein").toString());
                        cajaCentralCc.setPeriodo(response.getProperty("periodo").toString());
                        cajaCentralCc.setTotal(response.getProperty("total").toString());
                        cajaCentralCc.setHora(response.getProperty("hora").toString());
                        cajaCentralCc.setUsuario(response.getProperty("usuario").toString());
                        cajaCentralCc.setFecha(response.getProperty("fecha").toString());
                        cajaCentralCc.setCaja(response.getProperty("caja").toString());
                    }
                }catch (Exception ex){
                    //this returns a list
                    try {
                        METHODNAME="ConsultarCajaCentral";
                        String SOAP_ACTION=NAMESPACE+METHODNAME;
                        SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                        //WebParams
                        request.addProperty("fecha",params[0]);
                        request.addProperty("movimiento",params[1]);
                        request.addProperty("caja",params[2]);
                        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.dotNet=false;
                        envelope.setOutputSoapObject(request);
                        HttpTransportSE transporte = new HttpTransportSE(URL);
                        transporte.call(SOAP_ACTION, envelope);
                        //
                        SoapObject obj1=(SoapObject)envelope.bodyIn;
                        if(obj1.getPropertyCount()>0){
                        for (int i=0;i<obj1.getPropertyCount();i++){
                            SoapObject obj2=(SoapObject)obj1.getProperty(i);
                            cajaCentral=new CajaCentral();
                            cajaCentral.setNombre(obj2.getProperty("nombre").toString());
                            cajaCentral.setMovimiento(obj2.getProperty("movimiento").toString());
                            cajaCentral.setLiq(obj2.getProperty("liq").toString());
                            cajaCentral.setCaja(obj2.getProperty("caja").toString());
                            cajaCentral.setUsuario(obj2.getProperty("usuario").toString());
                            cajaCentral.setHora(obj2.getProperty("hora").toString());
                            cajaCentral.setFecha(obj2.getProperty("fecha").toString());
                            cajaCentral.setTotal(obj2.getProperty("total").toString());
                            cajaCentral.setObservacion(obj2.getProperty("observacion").toString());
                            cajaCentral.setConcepto(obj2.getProperty("concepto").toString());
                            cajaCentral.setImporte(obj2.getProperty("importe").toString());
                            listaCajaCentral.add(cajaCentral);
                        }
                        }else{
                            notFoundMessage="Verifica los datos y vuelve a intentarlo.";
                        }

                    }catch (Exception er){

                        er.printStackTrace();
                    }

                }
            }else{
                notEthernetConnection="No hay conexion a internet";
            }
            return null;
        }
        @Override
        protected void onPostExecute(String children) {

           if(notEthernetConnection!=""){
               super.onPostExecute(children);
               Toast.makeText(getActivity(), notEthernetConnection, Toast.LENGTH_SHORT).show();
           }else  if(notFoundMessage!=""){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notFoundMessage, Toast.LENGTH_SHORT).show();
            }else {
               if (METHODNAME.equals("ConsultarCajaCentralCc")) {
                   super.onPostExecute(children);
                   //handle caja central cc intent
                   if(!cajaCentralCc.getTributo().equals(null)){
                       return;
                   }
                   Intent intent = new Intent(getActivity(), CcCtaCteActivity.class);
                   intent.putExtra("tributo", cajaCentralCc.getTributo());
                   intent.putExtra(contribuyente, cajaCentralCc.getContribuyente());
                   intent.putExtra(movimiento, cajaCentralCc.getMovimiento());
                   intent.putExtra(nombre, cajaCentralCc.getNombre());
                   intent.putExtra(periodo, cajaCentralCc.getPeriodo());
                   intent.putExtra(deuda, cajaCentralCc.getDeuda());
                   intent.putExtra(emision, cajaCentralCc.getEmision());
                   intent.putExtra(morein, cajaCentralCc.getMorein());
                   intent.putExtra(fecha, cajaCentralCc.getFecha());
                   intent.putExtra(hora, cajaCentralCc.getHora());
                   intent.putExtra(total, cajaCentralCc.getTotal());
                   intent.putExtra(usuario, cajaCentralCc.getUsuario());
                   intent.putExtra(caja, cajaCentralCc.getCaja());
                   startActivity(intent);
               }
                if (METHODNAME.equals("ConsultarCajaCentral")) {

                    if(listaCajaCentral.size()!=0){
                        super.onPostExecute(children);
                   Intent intent = new Intent(getActivity(), CcCtaAhorroActivity.class);
                   intent.putExtra(listakey, listaCajaCentral);
                   startActivity(intent);
                    }else{
                        super.onPostExecute(children);
                    }
                }
            }
           }
        }
}

