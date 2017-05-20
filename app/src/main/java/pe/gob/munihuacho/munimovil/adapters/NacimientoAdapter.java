package pe.gob.munihuacho.munimovil.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.model.Nacimiento;
import pe.gob.munihuacho.munimovil.model.Papeleta;

/**
 * Created by alexisholyoak on 4/05/2017.
 */

public class NacimientoAdapter extends RecyclerView.Adapter<NacimientoAdapter.NacimientoViewHolder> {

    public  class NacimientoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        CardView cardView;
        TextView text1,text2;
        NacimientoViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            cardView=(CardView) view.findViewById(R.id.nacimientoCard);
            text1=(TextView)view.findViewById(R.id.nombre_nacimiento);
            text2=(TextView)view.findViewById(R.id.fecha_nacimiento);
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
    private ArrayList<Nacimiento> mNacimiento;
    private Context mContext;
    public NacimientoAdapter(Context context,ArrayList<Nacimiento> nacimientoArrayList){
        mContext=context;
        mNacimiento=nacimientoArrayList;
    }
    @Override
    public NacimientoAdapter.NacimientoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.nacimiento,parent,false);
        return new NacimientoAdapter.NacimientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NacimientoAdapter.NacimientoViewHolder holder, int position) {
        Nacimiento nacimiento=mNacimiento.get(position);
        String nombreNacimiento=nacimiento.getPaterno().trim()+" "+nacimiento.getMaterno().trim()+" "+nacimiento.getNombres().trim();
        String fechaNacimiento=nacimiento.getFechanac();
        holder.text1.setText(nombreNacimiento);
        holder.text2.setText("Fecha Nacimiento: "+fechaNacimiento);
    }

    @Override
    public int getItemCount() {
        return mNacimiento.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private NacimientoAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(NacimientoAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}
