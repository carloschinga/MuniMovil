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
import pe.gob.munihuacho.munimovil.model.Defuncion;
import pe.gob.munihuacho.munimovil.model.Nacimiento;

/**
 * Created by alexisholyoak on 19/05/2017.
 */

public class CtaNoCteAdapter extends RecyclerView.Adapter<CtaNoCteAdapter.CtaNoCteViewHolder> {

    public  class CtaNoCteViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        CardView cardView;
        TextView text1,text2;
        CtaNoCteViewHolder(View view){
            super(view);
           // view.setOnClickListener(this);
            cardView=(CardView) view.findViewById(R.id.ctanocteCard);
            text1=(TextView)view.findViewById(R.id.concepto);
            text2=(TextView)view.findViewById(R.id.importe);
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
  private ArrayList<CajaCentral> cajaCentralArrayList;
    private Context mContext;
    public CtaNoCteAdapter(Context context,ArrayList<CajaCentral> list){
        mContext=context;
       cajaCentralArrayList=list;
    }
    @Override
    public CtaNoCteAdapter.CtaNoCteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ctanocte,parent,false);
        return new CtaNoCteAdapter.CtaNoCteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CtaNoCteAdapter.CtaNoCteViewHolder holder, int position) {
        CajaCentral caja=cajaCentralArrayList.get(position);
        String xConcepto=caja.getConcepto().trim();
        String xImporte=caja.getImporte().trim();
        holder.text1.setText(xConcepto);
        holder.text2.setText(xImporte);

    }

    @Override
    public int getItemCount() {
        return cajaCentralArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private CtaNoCteAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(CtaNoCteAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}
