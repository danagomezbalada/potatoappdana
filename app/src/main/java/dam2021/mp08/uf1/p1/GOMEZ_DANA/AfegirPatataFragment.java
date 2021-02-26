package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Context.MODE_PRIVATE;

public class AfegirPatataFragment extends Fragment {

    private SQLiteDatabase baseDades;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private String rutaImatge = "";
    private String rutaAudio = "";

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
        view.findViewById(R.id.botoAudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // MediaPlayer so = MediaPlayer.create(getActivity(), R.raw.canco); //Agafa l'audio seleccionat
                comprovarPermisos();
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);


            }
        });
        view.findViewById(R.id.botoImatge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprovarPermisos();
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });


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
                    String audio = rutaAudio;
                    String imatge = rutaImatge;
                    if (resultat != null){
                        while (resultat.moveToNext()){
                            if (resultat.getString(0).equals(id)){
                                esPot = false;
                            }
                        }
                    }
                    if (esPot){
                        String sqlQuery = "INSERT INTO patates (id, tipus, descripcio, sembrar, recollir, preu, audio, imatge) " +
                                "VALUES ('"+id+"','"+tipus+"','"+desc+"','"+sembrar+"','"+recollir+"','"+preu+"','"+audio+"','"+imatge+"');";
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri seleccio;
        Cursor cursor;
        int indexColumna;
        switch (requestCode) {
            case 0:
                seleccio = data.getData();
                String[] columna = {MediaStore.Audio.Media.DATA};
                cursor = getActivity().getContentResolver().query(seleccio, columna, null, null, null);
                cursor.moveToFirst();
                indexColumna = cursor.getColumnIndex(columna[0]);
                rutaAudio = cursor.getString(indexColumna);
                cursor.close();
            case 1:
                seleccio = data.getData();
                String[] columna2 = {MediaStore.Images.Media.DATA};
                cursor = getActivity().getContentResolver().query(seleccio, columna2, null, null, null);
                cursor.moveToFirst();
                indexColumna = cursor.getColumnIndex(columna2[0]);
                rutaImatge = cursor.getString(indexColumna);
                cursor.close();

               // Bitmap imatge = BitmapFactory.decodeFile(rutaFitxer);
            /* Reduïm la imatge per no tenir problemes de visualització
            .
            Calculem l’alçada per mantenir la proporció amb una
            amplada de 1080 píxels
            */
               // int alt = (int) (imatge.getHeight() * 1080 / imatge.getWidth());
               // Bitmap reduit = Bitmap.createScaledBitmap(imatge, 1080, alt, true);
                // Afegim la imatge reduïda a l’Imageview
                //this.imatge.setImageBitmap(reduit);
            default: break;
        }
    }

    private void comprovarPermisos() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }
    }

}