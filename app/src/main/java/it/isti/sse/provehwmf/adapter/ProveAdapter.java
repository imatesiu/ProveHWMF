package it.isti.sse.provehwmf.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import it.isti.sse.provehwmf.R;
import it.isti.sse.provehwmf.pojo.ProvaHW;
import it.isti.sse.provehwmf.pojo.ProveHW;
import it.isti.sse.provehwmf.util.Utility;

/**
 * Created by m4rt3 on 16/11/2016.
 */

public class ProveAdapter extends RecyclerView.Adapter<ProveAdapter.PHWViewHolder> {


    //private List<String> ListaProve;
    private Context mContext;
    private static MyClickListenerProve myClickListener;
    private ProveHW LPHW;


    public ProveAdapter(Context mContext, /*List<String> Prove,*/ ProveHW LPHW){
        super();
       // this.ListaProve = Prove;
        this.mContext = mContext;
        this.LPHW = LPHW;
    }


    public static void setMyClickListener(MyClickListenerProve myClickListener) {
        ProveAdapter.myClickListener = myClickListener;
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

    private void init(PHWViewHolder holder, ProvaHW PHW){

        holder.TestHW.setText("Prova: "+PHW.getTipo());
        holder.EsitoTestHW.setText("Stato Prove: "+PHW.getEsito());
        holder.cv.setCardBackgroundColor(Utility.getColor(PHW.getEsito()));
        holder.matricola.setText("Matricola Fiscale: "+PHW.getMatricola());
        holder.modello.setText("Modello: "+PHW.getModello());
        holder.data.setText("Data: "+PHW.getTimeStartPHW());
    }



    @Override
    public void onBindViewHolder(PHWViewHolder holder, int position) {
      //  holder.classe.setText(lorario.get(position).getClasse());

         /*  LPHW.getProvaHW().get(position).getMatricola();
        LPHW.getProvaHW().get(position).getTipo();
        LPHW.getProvaHW().get(position).getTimeStartPHW();*/

        holder.position=position;
        init(holder,LPHW.getProvaHW().get(position));
        //holder.onClick(holder.i);

       // holder.TestHW.setText("Prova: Alimentazione senza\\n Vincolo Fiscale");
       // holder.EsitoTestHW.setText("Stato Prove: Positivo");
       // holder.matricola.setText("Matricola Fiscale: 1255998-552");
       // holder.modello.setText("Modello: TIPO C");
       // holder.data.setText("Data: 12/12/2016 12:15:16");

        holder.overflow.setOnClickListener(new ProveAdapter.MyMenuItemClickListenerMF(position));

    }

    class MyMenuItemClickListenerMF implements View
            .OnClickListener, PopupMenu.OnMenuItemClickListener {

        int position;
        public MyMenuItemClickListenerMF(int position) {
            this.position=position;

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.modificaprove:
                    Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
                    ProvaHW rova = new ProvaHW();
                    try {
                        rova = LPHW.getProvaHW().get(position);
                    }catch (IndexOutOfBoundsException | NullPointerException e){
                        //TODO: gestisci eccezione
                    }
                    myClickListener.onItemClick(rova);
                    return true;
                case R.id.cancellaprova :
                    Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                    try{
                        LPHW.remove(position);
                        notifyItemRemoved(position);
                    }catch (IndexOutOfBoundsException e){
                        //TODO: exception
                    }
                    return true;

                default:
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            PopupMenu popup = new PopupMenu(mContext, v, Gravity.RIGHT);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_card, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }
    }


    @Override
    public int getItemCount() {
        return LPHW.size();
    }

    public static class PHWViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        CardView cv;
        View i;
        int position;
        public ImageView overflow;

        public TextView TestHW;
        public TextView EsitoTestHW;
        public TextView matricola;
        public TextView modello;
        public TextView data;


        PHWViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_viewinternalCT1);
            overflow = (ImageView) itemView.findViewById(R.id.overflowTHW);

            TestHW = (TextView) itemView.findViewById(R.id.textView101);
            EsitoTestHW = (TextView) itemView.findViewById(R.id.textView121);
            matricola = (TextView) itemView.findViewById(R.id.textView91);
            modello = (TextView) itemView.findViewById(R.id.textView111);
            data = (TextView) itemView.findViewById(R.id.textView17);


            i=itemView;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

        }
    }
}
