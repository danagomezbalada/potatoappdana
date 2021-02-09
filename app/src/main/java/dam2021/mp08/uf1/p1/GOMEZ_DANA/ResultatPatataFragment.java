package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Context.MODE_PRIVATE;

public class ResultatPatataFragment extends Fragment {

    private SQLiteDatabase baseDades;
    Bundle dades;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        dades = getArguments();
        return inflater.inflate(R.layout.fragment_resultat_patata, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton add = view.findViewById(R.id.add);
        FloatingActionButton search = view.findViewById(R.id.search);
        TextView id = view.findViewById(R.id.id);
        TextView tipus = view.findViewById(R.id.tipus);
        TextView desc = view.findViewById(R.id.desc);
        TextView semb = view.findViewById(R.id.semb);
        TextView reco = view.findViewById(R.id.reco);
        String getId = "";

        if(dades != null) {
            getId = dades.getString("ID");
            baseDades = getActivity().openOrCreateDatabase("patata", MODE_PRIVATE, null);
            String query = "SELECT * FROM patates WHERE id='"+getId+"'";
            Cursor resultat = baseDades.rawQuery(query, null);
            if (resultat != null){
                resultat.moveToNext();
                id.setText(resultat.getString(0));
                tipus.setText(resultat.getString(1));
                desc.setText(resultat.getString(2));
                semb.setText(resultat.getString(3));
                reco.setText(resultat.getString(4));
            }
        }


        view.findViewById(R.id.botoTornar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultatPatataFragment.this)
                        .navigate(R.id.action_ResultatPatata_to_LlistaPatates);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultatPatataFragment.this)
                        .navigate(R.id.action_ResultatPatata_to_AfegirPatata);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultatPatataFragment.this)
                        .navigate(R.id.action_ResultatPatata_to_CercaPatates);
            }
        });
    }
}