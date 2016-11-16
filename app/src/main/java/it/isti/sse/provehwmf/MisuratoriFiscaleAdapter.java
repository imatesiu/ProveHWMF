package it.isti.sse.provehwmf;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by m4rt3 on 16/11/2016.
 */

public class MisuratoriFiscaleAdapter extends RecyclerView.Adapter<MisuratoriFiscaleAdapter.MFViewHolder> {


    private List<String> ListaMisuratoreFiscale;
    private static MyClickListener myClickListener;


    public MisuratoriFiscaleAdapter(List<String> MisuratoriFiscali){
        super();
        this.ListaMisuratoreFiscale = MisuratoriFiscali;
    }

    public static void setMyClickListener(MyClickListener myClickListener) {
        myClickListener = myClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_misuratore_fiscale, parent, false);
        MFViewHolder pvh = new MFViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MFViewHolder holder, int position) {
      //  holder.classe.setText(lorario.get(position).getClasse());


        //holder.onClick(holder.i);

    }

    @Override
    public int getItemCount() {
        return ListaMisuratoreFiscale.size();
    }

    public static class MFViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        CardView cv;
        View i;



        MFViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_view);

            i=itemView;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

        }
    }
}
