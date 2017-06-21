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
import pe.gob.munihuacho.munimovil.model.CajaCentral;
import pe.gob.munihuacho.munimovil.model.Resumen;

/**
 * Created by alexisholyoak on 31/05/2017.
 */

public class ResumenCtaCteAdapter extends RecyclerView.Adapter<ResumenCtaCteAdapter.ResumenCtaCteViewHolder> {

public  class ResumenCtaCteViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    CardView cardView;
    TextView text1,text2,text3,text4;
    ResumenCtaCteViewHolder(View view){
        super(view);
        // view.setOnClickListener(this);
        cardView=(CardView) view.findViewById(R.id.ctacteCard);
        text1=(TextView)view.findViewById(R.id.descripcion);
        text2=(TextView)view.findViewById(R.id.deuda);
        text3=(TextView)view.findViewById(R.id.pagos);
        text4=(TextView)view.findViewById(R.id.pendiente);
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
    private ArrayList<Resumen> resumenArrayList;
    private Context mContext;
    public ResumenCtaCteAdapter(Context context,ArrayList<Resumen> list){
        mContext=context;
        resumenArrayList=list;
    }
    @Override
    public ResumenCtaCteAdapter.ResumenCtaCteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ctacte_estado_contribuyente,parent,false);
        return new ResumenCtaCteAdapter.ResumenCtaCteViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ResumenCtaCteAdapter.ResumenCtaCteViewHolder holder, int position) {
        Resumen resumen=resumenArrayList.get(position);
        String xDescripcion=resumen.getDescripcion().trim();
        Double xDeuda=resumen.getDeuda();
        Double xPagos=resumen.getPagos();
        String xPendiente=String.valueOf(xDeuda-xPagos);
        holder.text1.setText(xDescripcion);
        holder.text2.setText(xDeuda.toString());
        holder.text3.setText(xPagos.toString());
        holder.text4.setText(xPendiente);
    }
    @Override
    public int getItemCount() {
        return resumenArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private ResumenCtaCteAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }
    public void setOnEntryClickListener(ResumenCtaCteAdapter.OnEntryClickListener onEntryClickListener) {
            mOnEntryClickListener = onEntryClickListener;
    }
}