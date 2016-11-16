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

public class ProveAdapter extends RecyclerView.Adapter<ProveAdapter.PHWViewHolder> {


    private List<String> ListaTestHW;
    private static MyClickListener myClickListener;


    public ProveAdapter(List<String> MisuratoriFiscali){
        super();
        this.ListaTestHW = MisuratoriFiscali;
    }

    public static void setMyClickListener(MyClickListener myClickListener) {
        myClickListener = myClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PHWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_test_hw, parent, false);
        PHWViewHolder pvh = new PHWViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PHWViewHolder holder, int position) {
      //  holder.classe.setText(lorario.get(position).getClasse());


        //holder.onClick(holder.i);

    }

    @Override
    public int getItemCount() {
        return ListaTestHW.size();
    }

    public static class PHWViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        CardView cv;
        View i;



        PHWViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_viewinternalCT1);

            i=itemView;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

        }
    }
}
