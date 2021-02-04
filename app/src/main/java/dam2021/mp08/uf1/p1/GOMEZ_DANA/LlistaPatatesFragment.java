package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LlistaPatatesFragment extends Fragment {

    private Bundle b;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;
    private List<Patata> patates = new ArrayList<Patata>();
    private RecyclerViewPatata mAdapter;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_llista_patates, container, false);
        View view = inflater.inflate(R.layout.fragment_llista_patates, container, false);

        FloatingActionButton add = view.findViewById(R.id.add);
        FloatingActionButton search = view.findViewById(R.id.search);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LlistaPatatesFragment.this)
                        .navigate(R.id.action_LlistaPatates_to_AfegirPatata);
            }
        });

       search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LlistaPatatesFragment.this)
                        .navigate(R.id.action_LlistaPatates_to_CercaPatates);
            }
        });

        // Set the adapter
        if (view.findViewById(R.id.llista_patates) instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.llista_patates);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            this.mAdapter = new RecyclerViewPatata(this.patates, mListener);
            recyclerView.setAdapter(this.mAdapter);
        }
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        afegirPatates();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void afegirPatates() {
        patates.add(new Patata("1","abc","eeef","123r","123f"));
        patates.add(new Patata("2","abc","eeef","123r","123f"));
        patates.add(new Patata("3","abc","eeef","123r","123f"));
        patates.add(new Patata("4","abc","eeef","123r","123f"));
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Patata patata);
    }

}