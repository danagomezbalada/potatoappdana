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
        View view = inflater.inflate(R.layout.fragment_afegir_patata, container, false);

        getActivity().setTitle(getString(R.string.afegir_patata_fragment_label));

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton search = view.findViewById(R.id.search);
        EditText textId = view.findViewById(R.id.textboxID);
        EditText textTipus = view.findViewById(R.id.textboxTipus);
        EditText textDesc = view.findViewById(R.id.textboxDesc);
        EditText textSembrar = view.findViewById(R.id.textboxSembrar);
        EditText textRecollir = view.findViewById(R.id.textboxRecollir);
        EditText textPreu = view.findViewById(R.id.textboxPreu);

        view.findViewById(R.id.botoAfegir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!textId.getText().toString().equals("")&&!textTipus.getText().toString().equals("")&&!textDesc.getText().toString().equals("")&&!textSembrar.getText().toString().equals("")&&!textRecollir.getText().toString().equals("")&&!textPreu.getText().toString().equals("")){
                    baseDades = getActivity().openOrCreateDatabase("patata", MODE_PRIVATE, null);
                    String query = "SELECT * FROM patates";
                    Cursor resultat = baseDades.rawQuery(query, null);
                    Boolean esPot = true;
                    String id = textId.getText().toString();
                    String tipus = textTipus.getText().toString();
                    String desc = textDesc.getText().toString();
                    String sembrar = textSembrar.getText().toString();
                    String recollir = textRecollir.getText().toString();
                    String preu = textPreu.getText().toString();
                    if (resultat != null){
                        while (resultat.moveToNext()){
                            if (resultat.getString(0).equals(id)){
                                esPot = false;
                            }
                        }
                    }
                    if (esPot){
                        String sqlQuery = "INSERT INTO patates (id, tipus, descripcio, sembrar, recollir, preu) " +
                                "VALUES ('"+id+"','"+tipus+"','"+desc+"','"+sembrar+"','"+recollir+"','"+preu+"');";
                        try {
                            baseDades.execSQL(sqlQuery);
                            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.afegitCorrecte), Toast.LENGTH_LONG).show();
                        } catch (SQLException e) {
                            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.idRepetit), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), getString(R.string.campsBlanc), Toast.LENGTH_LONG).show();
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