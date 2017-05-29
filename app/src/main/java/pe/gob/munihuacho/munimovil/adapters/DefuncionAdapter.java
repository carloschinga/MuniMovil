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
import pe.gob.munihuacho.munimovil.model.Defuncion;
import pe.gob.munihuacho.munimovil.model.Matrimonio;

/**
 * Created by alexisholyoak on 4/05/2017.
 */

public class DefuncionAdapter extends RecyclerView.Adapter<DefuncionAdapter.DefuncionViewHolder> {

public  class DefuncionViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    CardView cardView;
    TextView text1,text2;
    DefuncionViewHolder(View view){
        super(view);
        view.setOnClickListener(this);
        cardView=(CardView) view.findViewById(R.id.defuncionCard);
        text1=(TextView)view.findViewById(R.id.nombre_defuncion);
        text2=(TextView)view.findViewById(R.id.fecha_defuncion);
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
    private ArrayList<Defuncion> mDefuncion;
    private Context mContext;
    public DefuncionAdapter(Context context,ArrayList<Defuncion> defuncionArrayList){
        mContext=context;
        mDefuncion=defuncionArrayList;
    }
    @Override
    public DefuncionAdapter.DefuncionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.defuncion,parent,false);
        return new DefuncionAdapter.DefuncionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DefuncionAdapter.DefuncionViewHolder holder, int position) {
        Defuncion defuncion=mDefuncion.get(position);
        String nombreDifunto=defuncion.getPaterno().trim()+" "+defuncion.getMaterno().trim()+" "+defuncion.getNombres().trim();
        String fechaDifunsion=defuncion.getFechadef().trim();
        holder.text1.setText(nombreDifunto);
        holder.text2.setText("Fecha de defunción: "+fechaDifunsion);
    }

    @Override
    public int getItemCount() {
        return mDefuncion.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private DefuncionAdapter.OnEntryClickListener mOnEntryClickListener;

public interface OnEntryClickListener {
    void onEntryClick(View view, int position);
}

    public void setOnEntryClickListener(DefuncionAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}