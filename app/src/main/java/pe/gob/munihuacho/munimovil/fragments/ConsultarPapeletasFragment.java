package pe.gob.munihuacho.munimovil.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import pe.gob.munihuacho.munimovil.activities.PapeletasActivity;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.model.Papeleta;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarPapeletasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarPapeletasFragment extends Fragment {
    ImageView consulta_papeletas;
    EditText inputDni_Papeleta;
    Button btnBuscarCP;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /**WEB SERVICE*/
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDePapeletasWS/ConsultarPapeletas?WSDL";
    static final String NAMESPACE="http://Consultar/";


    private String mParam1;
    private String mParam2;


    public ConsultarPapeletasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarPapeletasFragment.
     */

    public static ConsultarPapeletasFragment newInstance(String param1, String param2) {
        ConsultarPapeletasFragment fragment = new ConsultarPapeletasFragment();
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
        View view=inflater.inflate(R.layout.fragment_consultar_papeletas, container, false);
        btnBuscarCP=(Button)view.findViewById(R.id.btnBuscarCP);
        inputDni_Papeleta=(EditText)view.findViewById(R.id.inputDni_Papeleta);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnBuscarCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputDni_Papeleta.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(), "Por favor ingrese un dato ", Toast.LENGTH_SHORT).show();
                }else{
                    new SoapAction(getActivity()).execute(inputDni_Papeleta.getText().toString().trim());
                }

            }
        });
    }
    private  class SoapAction extends AsynctaskWithDelayedIndeterminateProgress<String,String,String>{
        static final String METHODNAME="consultaDePapeleta";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;
        Papeleta papeleta;
        ArrayList<Papeleta> listPapeleta=new ArrayList<Papeleta>();
        String notFoundMessage="";
        String notEthernetConnection="";
        protected SoapAction(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("busqueda",params[0]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try {
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject obj1=(SoapObject)envelope.bodyIn;
                    if (obj1.getPropertyCount()>0){
                        for (int i=0;i<obj1.getPropertyCount();i++){
                            SoapObject obj2=(SoapObject)obj1.getProperty(i);
                            papeleta=new Papeleta();
                            papeleta.setDni(obj2.getProperty("dni").toString());
                            papeleta.setFechaInfraccion(obj2.getProperty("fechaInfraccion").toString());
                            papeleta.setNume_pape(obj2.getProperty("nume_pape").toString());
                            papeleta.setCodi_inte(obj2.getProperty("codi_inte").toString());
                            papeleta.setPlac_dttr(obj2.getProperty("plac_dttr").toString());
                            papeleta.setTota_pago(Double.parseDouble(obj2.getProperty("tota_pago").toString()));
                            papeleta.setDesc_esta(obj2.getProperty("desc_esta").toString());
                            listPapeleta.add(papeleta);}
                    }else{
                        notFoundMessage="No se encontro ningun registro";
                    }
                }catch (Exception ex){
                        ex.getMessage();
                }

            }else{
                notEthernetConnection="No hay conexion a internet";
            }
            return null;
        }

        @Override
        protected void onPostExecute(String children) {
            if (notEthernetConnection != "") {
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notEthernetConnection, Toast.LENGTH_SHORT).show();
            }
            if (!notFoundMessage.equals("")){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notFoundMessage, Toast.LENGTH_SHORT).show();
            }else{
                super.onPostExecute(children);
                Intent intent=new Intent(getActivity(), PapeletasActivity.class);
                intent.putExtra("listapapeletas",listPapeleta);
                startActivity(intent);
            }


        }
    }
}
