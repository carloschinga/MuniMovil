package pe.gob.munihuacho.munimovil.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.activities.DefuncionActivity;
import pe.gob.munihuacho.munimovil.activities.MatrimonioActivity;
import pe.gob.munihuacho.munimovil.activities.NacimientoActivity;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.model.Defuncion;
import pe.gob.munihuacho.munimovil.model.Matrimonio;
import pe.gob.munihuacho.munimovil.model.Nacimiento;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroEstadoCivilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroEstadoCivilFragment extends Fragment {
    String [] opciones={"Nacimiento","Matrimonio","Defuncion"};
    AutoCompleteTextView opcionesRegEstadoCivil;
    ImageView registro_estado_civil_img;
    Button btnConsultarReC;
    EditText input_paternoReC,input_maternoReC,input_nombreReC;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    /**WEB SERVICE STATIC STRINGS**/
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDeArchivosWS/ConsultarArchivos?WSDL";
    static final String NAMESPACE="http://Consultar/";

    public RegistroEstadoCivilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroEstadoCivilFragment.
     */
    public static RegistroEstadoCivilFragment newInstance(String param1, String param2) {
        RegistroEstadoCivilFragment fragment = new RegistroEstadoCivilFragment();
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
        View view=inflater.inflate(R.layout.fragment_registro_estado_civil, container, false);
        //Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, opciones);
        opcionesRegEstadoCivil=(AutoCompleteTextView)view.findViewById(R.id.opcionesRegEstadoCivil);
        opcionesRegEstadoCivil.setInputType(0);
        opcionesRegEstadoCivil.setAdapter(adapter);
        opcionesRegEstadoCivil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcionesRegEstadoCivil.showDropDown();
            }
        });
        input_paternoReC=(EditText)view.findViewById(R.id.input_paternoReC);
        input_maternoReC=(EditText)view.findViewById(R.id.input_maternoReC);
        input_nombreReC=(EditText)view.findViewById(R.id.input_nombreReC);
        btnConsultarReC=(Button)view.findViewById(R.id.btnConsultarReC);
        btnConsultarReC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItem=opcionesRegEstadoCivil.getText().toString();
                String paterno=input_paternoReC.getText().toString().trim();
                String materno=input_maternoReC.getText().toString().trim();
                String nombre=input_nombreReC.getText().toString().trim();
                if(input_paternoReC.getText().toString().trim().isEmpty()&&input_maternoReC.getText().toString().trim().isEmpty()&&input_nombreReC.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Debe Ingresar un dato de busqueda", Toast.LENGTH_SHORT).show();
                }else if(selectedItem.equals("Seleccione una opcion...")){
                    Toast.makeText(getActivity(), "Por favor selecciona una opción", Toast.LENGTH_SHORT).show();
                }else{
                    if (selectedItem.equals("Nacimiento")){
                        new SoapNacimiento(getActivity()).execute(paterno,
                                materno,
                                nombre);
                    }
                    if(selectedItem.equals("Matrimonio")){
                        new SoapMatrimonio(getActivity()).execute(paterno,
                                materno,
                                nombre);
                    }
                    if(selectedItem.equals("Defuncion")){
                        new SoapDefuncion(getActivity()).execute(paterno,
                                materno,
                                nombre);
                    }
                }

            }
        });

        return view;
    }
    /**SOAP METHODS**/
    /**(1) SOAP METHODS FOR DEFUNCION**/
    private class SoapDefuncion extends AsynctaskWithDelayedIndeterminateProgress<String,Integer,String> {
        static final String METHODNAME="consutarDefuncion";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;
        Defuncion defuncion;
        String notFoundMessage="";
        String notEthernetConnection="";
        ArrayList<Defuncion> listaMuertos=new ArrayList<Defuncion>();
        protected SoapDefuncion(Activity activity) {
            super(activity);
        }

        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("paterno",params[0]);
                request.addProperty("materno",params[1]);
                request.addProperty("nombres",params[2]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try {
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject obj1=(SoapObject)envelope.bodyIn;
                    if(obj1.getPropertyCount()>0){
                    for(int i=0;i<obj1.getPropertyCount();i++){
                        SoapObject obj2=(SoapObject)obj1.getProperty(i);
                        defuncion=new Defuncion();
                        defuncion.setPaterno(obj2.getProperty("paterno").toString());
                        defuncion.setMaterno(obj2.getProperty("materno").toString());
                        defuncion.setNombres(obj2.getProperty("nombres").toString());
                        defuncion.setSexo(obj2.getProperty("sexo").toString());
                        defuncion.setFechadef(obj2.getProperty("fechadef").toString());
                        defuncion.setDiainscrip(obj2.getProperty("diainscrip").toString());
                        defuncion.setMesinscrip(obj2.getProperty("mesinscrip").toString());
                        defuncion.setAñoinscrip(obj2.getProperty("añoinscrip").toString());
                        defuncion.setFecha(defuncion.getDiainscrip()+
                                "/"+defuncion.getMesinscrip()+
                                "/"+defuncion.getAñoinscrip());
                        listaMuertos.add(defuncion);
                    }
                    }else{
                        notFoundMessage="No se encontro registros";
                    }

                }catch (Exception ex){
                    // TODO: handle connection timeout exceptions
                }
            }else{
                notEthernetConnection="No hay conexion a internet";
            }
            return null;
        }

        @Override
        protected void onPostExecute(String children) {
            if(!notEthernetConnection.equals("")){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notEthernetConnection, Toast.LENGTH_SHORT).show();
            }else if(!notFoundMessage.equals("")){
                super.onPostExecute(children);
                Toast.makeText(getActivity(),notFoundMessage , Toast.LENGTH_SHORT).show();
            } else{
                super.onPostExecute(children);
                //handle found data intent
                if(listaMuertos.size()!=0){
                Intent intent=new Intent(getActivity(), DefuncionActivity.class);
                intent.putExtra("muertos",listaMuertos);
                startActivity(intent);
                }
             }
            }

        }
    /**(2) SOAP METHODS FOR NACIMIENTO**/
    private class SoapNacimiento extends AsynctaskWithDelayedIndeterminateProgress<String,Integer,String>{
        static final String METHODNAME="consutarNacimiento";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;
        Nacimiento nacimiento;
        String notFoundMessage="";
        String notEthernetConnection="";
        ArrayList<Nacimiento> listaNacidos=new ArrayList<Nacimiento>();
        protected SoapNacimiento(Activity activity) {
            super(activity);
        }

        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("paterno",params[0]);
                request.addProperty("materno",params[1]);
                request.addProperty("nombres",params[2]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try {
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject obj1=(SoapObject)envelope.bodyIn;
                    if(obj1.getPropertyCount()>0) {
                        for (int i = 0; i < obj1.getPropertyCount(); i++) {
                            SoapObject obj2 = (SoapObject) obj1.getProperty(i);
                            nacimiento = new Nacimiento();
                            nacimiento.setPaterno(obj2.getProperty("paterno").toString());
                            nacimiento.setMaterno(obj2.getProperty("materno").toString());
                            nacimiento.setNombres(obj2.getProperty("nombres").toString());
                            nacimiento.setSexo(obj2.getProperty("sexo").toString());
                            nacimiento.setFechanac(obj2.getProperty("fechanac").toString());
                            //Fecha Inscripcion
                            nacimiento.setDiainscrip(obj2.getProperty("diainscrip").toString());
                            nacimiento.setMesinscrip(obj2.getProperty("mesinscrip").toString());
                            nacimiento.setAñoinscrip(obj2.getProperty("añoinscrip").toString());
                            //Concatenacion de fecha de inscripcion
                            nacimiento.setFecha_inscripcion(nacimiento.getDiainscrip() + "/" +
                                    nacimiento.getMesinscrip() + "/" + nacimiento.getAñoinscrip());
                            //End de concatenacion
                            listaNacidos.add(nacimiento);
                        }
                    } else{
                        notFoundMessage="No se encontró ningun registro";
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
            //handle intent
            if(!notEthernetConnection.equals("")){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notEthernetConnection, Toast.LENGTH_SHORT).show();
            }else if(!notFoundMessage.equals("")){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notFoundMessage, Toast.LENGTH_SHORT).show();
            }else{
                super.onPostExecute(children);
                if(listaNacidos.size()!=0){
                Intent intent=new Intent(getActivity(), NacimientoActivity.class);
                intent.putExtra("nacimiento",listaNacidos);
                startActivity(intent);
                }
            }
        }
    }
    /**(3) SOAP METHODS FOR MATRIMONIO**/
    private class SoapMatrimonio extends AsynctaskWithDelayedIndeterminateProgress<String,Integer,String>{
        static final String METHODNAME="consutarMatrimonio";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;
        Matrimonio matrimonio;
        String notFoundMessage="";
        String notEthernetConnection="";
        ArrayList<Matrimonio> listaCasados=new ArrayList<Matrimonio>();
        protected SoapMatrimonio(Activity activity) {
            super(activity);
        }

        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("paterno",params[0]);
                request.addProperty("materno",params[1]);
                request.addProperty("nombres",params[2]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try {
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject obj1=(SoapObject)envelope.bodyIn;
                    if(obj1.getPropertyCount()>0){
                    for(int i=0;i<obj1.getPropertyCount();i++){
                        SoapObject obj2=(SoapObject)obj1.getProperty(i);
                        matrimonio=new Matrimonio();
                        //Hombre
                        matrimonio.setPaterno(obj2.getProperty("paterno").toString());
                        matrimonio.setMaterno(obj2.getProperty("materno").toString());
                        matrimonio.setNombres(obj2.getProperty("nombres").toString());
                        //Mujer
                        matrimonio.setPaternodon(obj2.getProperty("paternodon").toString());
                        matrimonio.setMaternodon(obj2.getProperty("maternodon").toString());
                        matrimonio.setNombresdon(obj2.getProperty("nombresdon").toString());
                        //fecha
                        matrimonio.setFechamat(obj2.getProperty("fechamat").toString());
                        matrimonio.setDiainscrip(obj2.getProperty("diainscrip").toString());
                        matrimonio.setMesinscrip(obj2.getProperty("mesinscrip").toString());
                        matrimonio.setAñoinscrip(obj2.getProperty("añoinscrip").toString());
                        //concatenando fecha de inscripcion
                        matrimonio.setFecha(matrimonio.getDiainscrip()+"/"+
                                matrimonio.getMesinscrip()+"/"+
                                matrimonio.getAñoinscrip());
                        listaCasados.add(matrimonio);
                    }
                    }else{
                        notFoundMessage="No se encontro ningun registro.";
                    }
                }catch (Exception ex){
                    // TODO: handle connection timeout exceptions
                }
            }else{
                notEthernetConnection="No hay conexion a internet";
            }

            return null;
        }

        @Override
        protected void onPostExecute(String children) {
                if(!notEthernetConnection.equals("")){
                    super.onPostExecute(children);
                    Toast.makeText(getActivity(), notEthernetConnection, Toast.LENGTH_SHORT).show();
                }else if(!notFoundMessage.equals("")){
                    super.onPostExecute(children);
                    Toast.makeText(getActivity(), notFoundMessage, Toast.LENGTH_SHORT).show();
                }else{
                    super.onPostExecute(children);
                    if(listaCasados.size()!=0){
                    Intent intent=new Intent(getActivity(), MatrimonioActivity.class);
                    intent.putExtra("matrimonio",listaCasados);
                    startActivity(intent);
                    }
                }
        }
    }
}
