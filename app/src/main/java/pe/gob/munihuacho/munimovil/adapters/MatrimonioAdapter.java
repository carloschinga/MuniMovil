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
import pe.gob.munihuacho.munimovil.model.Matrimonio;
import pe.gob.munihuacho.munimovil.model.Nacimiento;

/**
 * Created by alexisholyoak on 4/05/2017.
 */

public class MatrimonioAdapter extends RecyclerView.Adapter<MatrimonioAdapter.MatrimonioViewHolder> {

    public  class MatrimonioViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        CardView cardView;
        TextView text1,text2;
        MatrimonioViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            cardView=(CardView) view.findViewById(R.id.matrimonioCard);
            text1=(TextView)view.findViewById(R.id.nombre_esposo);
            text2=(TextView)view.findViewById(R.id.nombre_esposa);
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
    private ArrayList<Matrimonio> mMatrimonios;
    private Context mContext;
    public MatrimonioAdapter(Context context,ArrayList<Matrimonio> matrimonioArrayList){
        mContext=context;
        mMatrimonios=matrimonioArrayList;
    }
    @Override
    public MatrimonioAdapter.MatrimonioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.matrimonio,parent,false);
        return new MatrimonioAdapter.MatrimonioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatrimonioAdapter.MatrimonioViewHolder holder, int position) {
        Matrimonio matrimonio=mMatrimonios.get(position);
        String nombreEsposa=matrimonio.getPaterno().trim()+" "+matrimonio.getMaterno().trim()+" "+matrimonio.getNombres().trim();
        String nombreEsposo=matrimonio.getPaternodon().trim()+" "+matrimonio.getMaternodon().trim()+" "+matrimonio.getNombresdon().trim();
        holder.text1.setText(nombreEsposa);
        holder.text2.setText(nombreEsposo);
    }

    @Override
    public int getItemCount() {
        return mMatrimonios.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private MatrimonioAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(MatrimonioAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}
