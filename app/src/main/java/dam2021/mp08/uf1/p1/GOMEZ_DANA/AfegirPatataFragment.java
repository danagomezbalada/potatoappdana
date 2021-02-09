package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import android.database.SQLException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Context.MODE_PRIVATE;

public class AfegirPatataFragment extends Fragment {

    private SQLiteDatabase baseDades;

    public AfegirPatataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_afegir_patata, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton search = view.findViewById(R.id.search);
        EditText textId = view.findViewById(R.id.textboxID);
        EditText textTipus = view.findViewById(R.id.textboxTipus);
        EditText textDesc = view.findViewById(R.id.textboxDesc);
        EditText textSembrar = view.findViewById(R.id.textboxSembrar);
        EditText textRecollir = view.findViewById(R.id.textboxRecollir);

        view.findViewById(R.id.botoAfegir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!textId.getText().toString().equals("")&&!textTipus.getText().toString().equals("")&&!textDesc.getText().toString().equals("")&&!textSembrar.getText().toString().equals("")&&!textRecollir.getText().toString().equals("")){
                    baseDades = getActivity().openOrCreateDatabase("patata", MODE_PRIVATE, null);
                    String query = "SELECT * FROM patates";
                    Cursor resultat = baseDades.rawQuery(query, null);
                    Boolean esPot = true;
                    String id = textId.getText().toString();
                    String tipus = textTipus.getText().toString();
                    String desc = textDesc.getText().toString();
                    String sembrar = textSembrar.getText().toString();
                    String recollir = textRecollir.getText().toString();
                    if (resultat != null){
                        while (resultat.moveToNext()){
                            if (resultat.getString(0).equals(id)){
                                esPot = false;
                            }
                        }
                    }
                    if (esPot){
                        String sqlQuery = "INSERT INTO patates (id, tipus, descripcio, sembrar, recollir) " +
                                "VALUES ('"+id+"','"+tipus+"','"+desc+"','"+sembrar+"','"+recollir+"');";
                        try {
                            baseDades.execSQL(sqlQuery);
                            Toast.makeText(getActivity().getApplicationContext(), "Afegit correctament", Toast.LENGTH_LONG).show();
                        } catch (SQLException e) {
                            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "L'ID no es pot repetir!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "No es poden deixar un o mes camps en blanc.", Toast.LENGTH_LONG).show();
                }
            }
        });
        view.findViewById(R.id.botoTornar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AfegirPatataFragment.this)
                        .navigate(R.id.action_AfegirPatata_to_LlistaPatates);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AfegirPatataFragment.this)
                        .navigate(R.id.action_AfegirPatata_to_CercaPatates);
            }
        });
    }
}