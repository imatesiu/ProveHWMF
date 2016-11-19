package it.isti.sse.provehwmf.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.List;

import it.isti.sse.provehwmf.R;
import it.isti.sse.provehwmf.pojo.Allegati;
import it.isti.sse.provehwmf.pojo.Allegato;

/**
 * Created by m4rt3 on 16/11/2016.
 */

public class AllegatiAdapter extends RecyclerView.Adapter<AllegatiAdapter.PHWViewHolder> {


    private List<String> ListaAllegati;
    private Allegati allegati;
    private Context mContext;


    public AllegatiAdapter(Context lContext, List<String> ListaAllegati, Allegati allegati){
        super();
        this.ListaAllegati = ListaAllegati;
        this.mContext = lContext;
        this.allegati = allegati;
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PHWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_allegato, parent, false);
        PHWViewHolder pvh = new PHWViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PHWViewHolder holder, int position) {
      //  holder.classe.setText(lorario.get(position).getClasse());


        //holder.onClick(holder.i);

        holder.overflow.setOnClickListener(new AllegatiAdapter.MyMenuItemClickListenerMF(position));

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
                case R.id.openprovaallegato:
                    Toast.makeText(mContext, "Open", Toast.LENGTH_SHORT).show();
                    Allegato allegato = allegati.getAllegato().get(position);
                    String tipo = allegato.getTipo();
                    String dati =  allegato.getDati();
                    switch (tipo){
                        case "jpg":{
                            if(dati!=null){
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.parse("http://goo.gl/gEgYUd"), "image/*");
                                mContext.startActivity(intent);
                            }
                        }
                        case "doc":{

                        }
                        case "txt":{

                        }

                        default:
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse("http://goo.gl/gEgYUd"), "image/*");
                            mContext.startActivity(intent);
                    }

                    return true;
                case R.id.cancellaprovaallegato:
                    Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                    try {
                        ListaAllegati.remove(position);
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
            inflater.inflate(R.menu.menu_allegato, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }
    }

    @Override
    public int getItemCount() {
        return ListaAllegati.size();
    }

    public static class PHWViewHolder extends RecyclerView.ViewHolder /* implements View
            .OnClickListener*/ {

        CardView cv;
        View i;
        public ImageView overflow;

        PHWViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_viewAllegatoTestHW);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
            i=itemView;
        }
       /* PHWViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_viewAllegatoTestHW);

            i=itemView;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

        }*/
    }
}
