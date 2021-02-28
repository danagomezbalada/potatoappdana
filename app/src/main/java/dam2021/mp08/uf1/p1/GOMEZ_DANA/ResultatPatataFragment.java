package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

public class ResultatPatataFragment extends Fragment {

    private SQLiteDatabase baseDades;
    Bundle dades;
    private boolean trobat = false;
    private MediaPlayer so = null;
    private boolean isPlaying = false;
    private ImageView image = null;

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
        View view = inflater.inflate(R.layout.fragment_resultat_patata, container, false);

        dades = getArguments();
        getActivity().setTitle(getString(R.string.resultat_patata_fragment_label));

        return view;
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
        TextView preu = view.findViewById(R.id.preu);
        String getId = "";
        String audio="", imatge="";

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
                preu.setText(resultat.getString(5));
                audio = resultat.getString(6);
                imatge = resultat.getString(7);
                trobat = true;
            }
        }

        if (trobat && imatge!=null && !imatge.equals("")){
            try{
                this.image = (ImageView) view.findViewById(R.id.imatge);
                Bitmap bitmap = BitmapFactory.decodeFile(imatge);
                bitmap = Bitmap.createScaledBitmap(bitmap,100,100,true);
                this.image.setImageBitmap(bitmap);
            }catch (NullPointerException e){
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.imatgeNoTrobada), Toast.LENGTH_LONG).show();
            }
        }

        String finalAudio = audio;
        view.findViewById(R.id.botoPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dades!=null){
                    if (!isPlaying){
                        if (trobat && finalAudio!=null && !finalAudio.equals("")){
                            Uri path = Uri.fromFile(new File(finalAudio));
                            so = MediaPlayer.create(getActivity(), path);
                            try{
                                so.start();
                                Snackbar.make(view, "Play", Snackbar.LENGTH_SHORT)
                                        .setAction("Action", null).show();
                                isPlaying = true;
                            }catch(NullPointerException e){
                                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.audioNoTrobat), Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.audioNoTrobat), Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.audioReproduint), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        view.findViewById(R.id.botoStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dades!=null && so!=null && isPlaying){
                    if (trobat && finalAudio!=null && !finalAudio.equals("")){
                        Uri path = Uri.fromFile(new File(finalAudio));
                        so.stop();
                        so.release();
                        Snackbar.make(view, "Stop", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        isPlaying = false;
                    }
                }
            }
        });

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