package it.isti.sse.provehwmf;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by m4rt3 on 16/11/2016.
 */

public class AllegatiAdapter extends RecyclerView.Adapter<AllegatiAdapter.PHWViewHolder> {


    private List<String> ListaAllegati;
    private Context mContext;


    public AllegatiAdapter(Context lContext, List<String> Allegati){
        super();
        this.ListaAllegati = Allegati;
        this.mContext = lContext;
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

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_allegato, popup.getMenu());
                popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
                popup.show();
            }
        });

    }



    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.modificaprove:
                    Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.cancellaprova:
                    Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
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
