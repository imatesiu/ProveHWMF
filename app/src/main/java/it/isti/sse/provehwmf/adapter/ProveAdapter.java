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
import android.widget.Toast;

import java.util.List;

import it.isti.sse.provehwmf.MyClickListener;
import it.isti.sse.provehwmf.R;

/**
 * Created by m4rt3 on 16/11/2016.
 */

public class ProveAdapter extends RecyclerView.Adapter<ProveAdapter.PHWViewHolder> {


    private List<String> ListaProve;
    private Context mContext;
    private static MyClickListener myClickListener;


    public ProveAdapter(Context mContext, List<String> Prove){
        super();
        this.ListaProve = Prove;
        this.mContext = mContext;
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

        holder.position=position;
        //holder.onClick(holder.i);

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


                    return true;
                case R.id.cancellaprova:
                    Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                    ListaProve.remove(position);
                    notifyItemRemoved(position);
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
        return ListaProve.size();
    }

    public static class PHWViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        CardView cv;
        View i;
        int position;
        public ImageView overflow;


        PHWViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_viewinternalCT1);
            overflow = (ImageView) itemView.findViewById(R.id.overflowTHW);

            i=itemView;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

        }
    }
}
