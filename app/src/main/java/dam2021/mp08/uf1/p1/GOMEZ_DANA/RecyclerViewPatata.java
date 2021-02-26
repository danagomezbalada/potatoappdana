package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;


public class RecyclerViewPatata extends RecyclerView.Adapter<RecyclerViewPatata.ViewHolder> {

    private final List<Patata> llistaPatates;
    private final LlistaPatatesFragment.OnListFragmentInteractionListener mListener;

    public RecyclerViewPatata(List<Patata> items, LlistaPatatesFragment.OnListFragmentInteractionListener listener) {
        llistaPatates = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.llista_patates, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.patata = llistaPatates.get(position);
        holder.hIdPatata.setText(llistaPatates.get(position).getId());
        holder.hTipusPatata.setText(llistaPatates.get(position).getTipus());
        holder.hSembrarPatata.setText(llistaPatates.get(position).getSembrar());
        holder.hRecollidaPatata.setText(llistaPatates.get(position).getRecollida());
        holder.hPreuPatata.setText(llistaPatates.get(position).getPreu());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.patata);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return llistaPatates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView hIdPatata;
        public final TextView hTipusPatata;
        public final TextView hSembrarPatata;
        public final TextView hRecollidaPatata;
        public final TextView hPreuPatata;
        public Patata patata;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            hIdPatata = (TextView) view.findViewById(R.id.patata_id);
            hTipusPatata = (TextView) view.findViewById(R.id.patata_tipus);
            hSembrarPatata = (TextView) view.findViewById(R.id.patata_sembrar);
            hRecollidaPatata = (TextView) view.findViewById(R.id.patata_recollida);
            hPreuPatata = (TextView) view.findViewById(R.id.patata_preu);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + hTipusPatata.getText() + "'";
        }
    }
}

