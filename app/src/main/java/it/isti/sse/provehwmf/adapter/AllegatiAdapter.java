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
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import isti.cnr.sse.rest.data.Allegato;
import it.isti.sse.provehwmf.R;


/**
 * Created by m4rt3 on 16/11/2016.
 */

public class AllegatiAdapter extends RecyclerView.Adapter<AllegatiAdapter.PHWViewHolder> {


    //private List<String> ListaAllegati;
    private List<Allegato> allegati;
    private Context mContext;


    public AllegatiAdapter(Context lContext, /*List<String> ListaAllegati,*/ List<Allegato>  allegati){
        super();
        //this.ListaAllegati = ListaAllegati;
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

    private void init(PHWViewHolder holder,Allegato a){
        holder.matricola.setText(a.getMatricola());
        holder.nomeallegato.setText(a.getNome());
        holder.tipoallegato.setText(a.getTipo());
        if(a.getTipo()!=null){
            if(a.getTipo().equals("JPG")){
                holder.cardimg.setImageResource(R.drawable.ic_menu_camera);
            }else{
                if(a.getTipo().equals("PDF")){
                    holder.cardimg.setImageResource(R.drawable.ic_pdf);
                }else{
                    if(a.getTipo().equals("TXT")){
                        holder.cardimg.setImageResource(R.drawable.ic_txt);
                    }
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(PHWViewHolder holder, int position) {
      //  holder.classe.setText(lorario.get(position).getClasse());


        init(holder,allegati.get(position));
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
                    try {
                         Allegato allegato = allegati.get(position);
                        String tipo = allegato.getTipo();
                        String url = allegato.getUrl();
                        String dati = allegato.getDati();
                        switch (tipo) {
                            case "image/jpeg": {
                                if (url != null) {
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.parse(url), "image/*");
                                    mContext.startActivity(intent);
                                }
                            }
                            case "application/pdf": {
                                if (url != null) {
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                                    mContext.startActivity(intent);
                                }
                            }
                            case "text/plain": {
                                if (url != null) {
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.parse(url), "text/plain");
                                    mContext.startActivity(intent);
                                }
                            }

                            default:
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.parse("http://goo.gl/gEgYUd"), "image/*");
                                mContext.startActivity(intent);
                        }
                    }catch (IndexOutOfBoundsException e){
                        //TODO:Tratta eccezione
                    }
                    return true;
                case R.id.cancellaprovaallegato:
                    Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                    try {
                        allegati.remove(position);
                        notifyDataSetChanged();
                       // notifyItemRemoved(position);
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
        return allegati.size();
    }

    public static class PHWViewHolder extends RecyclerView.ViewHolder /* implements View
            .OnClickListener*/ {

        CardView cv;
        View i;
        public ImageView overflow;
        public TextView matricola;
        public TextView tipoallegato;
        public TextView nomeallegato;
        public ImageView cardimg;


        PHWViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_viewAllegatoTestHW);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
            cardimg = (ImageView) itemView.findViewById(R.id.classecard3);

            matricola = (TextView) itemView.findViewById(R.id.textView9);
            tipoallegato = (TextView) itemView.findViewById(R.id.textView12);
            nomeallegato = (TextView) itemView.findViewById(R.id.textView10);
            nomeallegato.setSelected(true);

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
