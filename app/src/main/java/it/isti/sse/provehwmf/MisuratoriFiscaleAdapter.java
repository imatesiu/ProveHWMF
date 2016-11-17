package it.isti.sse.provehwmf;

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
import android.widget.Toast;

import java.util.List;

/**
 * Created by m4rt3 on 16/11/2016.
 */

public class MisuratoriFiscaleAdapter extends RecyclerView.Adapter<MisuratoriFiscaleAdapter.MFViewHolder> {


    private List<String> ListaMisuratoreFiscale;
    private Context mContext;
    private static MyClickListener myClickListener;


    public MisuratoriFiscaleAdapter(Context lContext,List<String> MisuratoriFiscali){
        super();
        this.ListaMisuratoreFiscale = MisuratoriFiscali;
        this.mContext = lContext;
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

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, view, Gravity.RIGHT);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_allegato, popup.getMenu());
                popup.setOnMenuItemClickListener(new MisuratoriFiscaleAdapter.MyMenuItemClickListenerMF());
                popup.show();
            }
        });

    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListenerMF implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListenerMF() {
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
        return ListaMisuratoreFiscale.size();
    }

    public static class MFViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        CardView cv;
        View i;
        public ImageView overflow;


        MFViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_view);
            overflow = (ImageView) itemView.findViewById(R.id.overflowMF);

            i=itemView;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

        }
    }
}
