package pe.gob.munihuacho.munimovil.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import javax.security.auth.Subject;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.model.Papeleta;

/**
 * Created by alexisholyoak on 21/04/2017.
 */

public class PapeletasAdapter extends RecyclerView.Adapter<PapeletasAdapter.AdaptadorViewHolder> {


    public  class AdaptadorViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        CardView cardView;
        TextView text1,text2,text3;
        AdaptadorViewHolder(View view){
           super(view);
            view.setOnClickListener(this);
           cardView=(CardView) view.findViewById(R.id.papeletaCard);
           text1=(TextView)view.findViewById(R.id.numero_papeleta);
           text2=(TextView)view.findViewById(R.id.estado_papeleta);
           text3=(TextView)view.findViewById(R.id.fecha_infraccion_pap);
          }
        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }
    private ArrayList<Papeleta> mPapeletas;
    private Context mContext;
    public PapeletasAdapter(Context context,ArrayList<Papeleta> papeletaArrayList){
          mContext=context;
          mPapeletas=papeletaArrayList;
    }
    @Override
    public AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.papeletas,parent,false);
        return new AdaptadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorViewHolder holder, int position) {
        Papeleta papeleta=mPapeletas.get(position);
        String numPapeleta=papeleta.getNume_pape();
        String estadoPapeleta=papeleta.getDesc_esta();
        String fechaInfraccion=papeleta.getFechaInfraccion();
        holder.text1.setText("N° Papeleta:"+numPapeleta);
        holder.text2.setText(estadoPapeleta);
        holder.text3.setText(fechaInfraccion);
    }

    @Override
    public int getItemCount() {
        return mPapeletas.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

}
