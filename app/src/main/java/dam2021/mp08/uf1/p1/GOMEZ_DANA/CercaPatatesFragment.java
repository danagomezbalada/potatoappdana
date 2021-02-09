package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Context.MODE_PRIVATE;

public class CercaPatatesFragment extends Fragment {

    private SQLiteDatabase baseDades;
    Bundle dades;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cerca_patates, container, false);

        getActivity().setTitle(getString(R.string.cerca_patates_fragment_label));

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton add = view.findViewById(R.id.add);
        EditText textId = view.findViewById(R.id.textbox);

        view.findViewById(R.id.botoCerca).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDades = getActivity().openOrCreateDatabase("patata", MODE_PRIVATE, null);
                String query = "SELECT * FROM patates";
                Cursor resultat = baseDades.rawQuery(query, null);
                Boolean trobat = false;
                String id = textId.getText().toString();
                if (resultat != null){
                    while (resultat.moveToNext() && !trobat){
                        if (resultat.getString(0).equals(id)){
                            trobat = true;
                        }
                    }
                }
                if (trobat){
                    dades = new Bundle();
                    dades.putString("ID", id);
                    NavHostFragment.findNavController(CercaPatatesFragment.this)
                            .navigate(R.id.action_CercaPatates_to_ResultatPatata, dades);
                }
            }
        });
       view.findViewById(R.id.botoTornar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CercaPatatesFragment.this)
                        .navigate(R.id.action_CercaPatates_to_LlistaPatates);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CercaPatatesFragment.this)
                        .navigate(R.id.action_CercaPatates_to_AfegirPatata);
            }
        });
    }
}