package pe.gob.munihuacho.munimovil.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pe.gob.munihuacho.munimovil.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlertaHuachoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertaHuachoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button serenazgo;
    Button policia;
    Button bomberos;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AlertaHuachoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlertaHuachoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlertaHuachoFragment newInstance(String param1, String param2) {
        AlertaHuachoFragment fragment = new AlertaHuachoFragment();
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
        View view=inflater.inflate(R.layout.fragment_alerta_huacho, container, false);
        serenazgo=(Button)view.findViewById(R.id.btnLlamarSerenazgo);
        serenazgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:+51986064775"));
                startActivity(intent);
            }
        });
        policia=(Button)view.findViewById(R.id.btnllamarpolicia);
        policia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+51986045774"));
                startActivity(intent);
            }
        });
        bomberos=(Button)view.findViewById(R.id.btnLlamarbomberos);
        bomberos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+51986045774"));
                startActivity(intent);
            }
        });
        return view;
    }

}
