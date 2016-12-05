package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isti.cnr.sse.rest.data.Allegato;
import isti.cnr.sse.rest.data.Esito;
import isti.cnr.sse.rest.data.Prova;
import isti.cnr.sse.rest.data.TipoProve;
import it.isti.sse.provehwmf.adapter.AllegatiAdapter;


public class ProvaActivity extends AppCompatActivity {

    //private Spinner modello;
    private EditText modello;
    private EditText Matricola;
    private Spinner tipoprova;
    private Spinner esito;
    private List<Allegato> allegati;
    private Prova TPHW = new Prova();
    private boolean edited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPHW);
        setSupportActionBar(toolbar);

        //modello = (Spinner)findViewById(R.id.spinner);
        modello = (EditText)findViewById(R.id.spinner);
        Matricola  = (EditText)findViewById(R.id.spinnerMMF);
        tipoprova  = (Spinner)findViewById(R.id.spinnerTipoProva);
        esito      = (Spinner)findViewById(R.id.spinnerEsito);


     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuPHW);
        menuRed.setClosedOnTouchOutside(true);


     /*   FloatingActionButton notaHW = (FloatingActionButton) findViewById(R.id.P_add_note);
        notaHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuPHW);
                menuRed.close(false);
                Intent i = new Intent(ProvaActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
                startActivity(i);
            }
        });

        FloatingActionButton cameraHW = (FloatingActionButton) findViewById(R.id.P_add_Camera);
        cameraHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuPHW);
                menuRed.close(false);
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                takePictureIntent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 2 * 1024 * 1024);
                File fileUri = Utility.getOutputMediaFile(); // create a file to save the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name


                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 100);
                    // startActivity(takePictureIntent);
                }
               // Intent i = new Intent(ProvaActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
               // startActivity(i);
            }
        });*/

        FloatingActionButton attachHW = (FloatingActionButton) findViewById(R.id.P_add_Doc);
        attachHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuPHW);
                menuRed.close(false);
               // Intent i = new Intent(ProvaActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
               // startActivity(i);
                Intent i = new Intent(ProvaActivity.this, AllegatoActivity.class);
                i.putExtra("MatricolaMF",Matricola.getText().toString());
                i.putExtra("TipoProva",String.valueOf(tipoprova.getSelectedItemPosition()));
                // i.putExtra("key","value");
                startActivityForResult(i, 190);
            }
        });

        Button buttonSave  = (Button)  findViewById(R.id.savePHW);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                //ProvaHW t = new ProvaHW();


                String model = modello.getText().toString();
                TPHW.setNomeModello(model);
                TPHW.setNumeroRapportoProva(Matricola.getText().toString());
               // TPHW.setSelezionabile(edited);
                TPHW.setNomeProva(tipoprova.getSelectedItem().toString());
                TPHW.setTp(TipoProve.values()[tipoprova.getSelectedItemPosition()]);
                Esito e = Esito.values()[esito.getSelectedItemPosition()];

                TPHW.setStato(e);

                TPHW.setListallegato(allegati);
                String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
                if(!edited)
                    TPHW.setTimeStartPHW(timeStamp);


                TPHW.setTimeEndPHW(timeStamp);





              //  setResult(Activity.RESULT_OK);


                setResult(Activity.RESULT_OK,getIntent().putExtra("newProva", TPHW));//, intent);
                finish();
            }

        });

        Button buttonEdit  = (Button)  findViewById(R.id.editPHW);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                if(tipoprova.isEnabled()){
                    tipoprova.setEnabled(false);
                    modello.setEnabled(false);
                    Matricola.setEnabled(false);
                    esito.setEnabled(false);
                }else {

                    esito.setEnabled(true);
                    edited = true;
                }
            }

        });

        RecyclerView rva = (RecyclerView)findViewById(R.id.AllegatiProva);
        rva.setHasFixedSize(true);
        LinearLayoutManager llma = new LinearLayoutManager(this);
        llma.setOrientation(LinearLayoutManager.HORIZONTAL);
        rva.setLayoutManager(llma);

        allegati = new ArrayList<>();
        setInit(allegati);

        //TODO riempilista
        AllegatiAdapter adaptera = new AllegatiAdapter(this,allegati);
        rva.setAdapter(adaptera);


    }

    @Override
    public void onBackPressed() {
        // When the user hits the back button set the resultCode
        // to Activity.RESULT_CANCELED to indicate a failure
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 190) { //allegato
            if (resultCode == Activity.RESULT_OK) {
               // String result = data.getStringExtra("result");
                Bundle b = getIntent().getExtras();
                Allegato all = (Allegato) b.getSerializable("newAllegato");
                TPHW.getListallegato().add(all);
                allegati.add(all);
                edited = true;

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.proveactivity);
                Snackbar.make(coordinatorLayout, "Nessun Allegato Salvato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    public void setInit(List<Allegato> init) {
        try {
            Bundle b = getIntent().getExtras();
            TPHW = (Prova) b.getSerializable("Prova");
            if(TPHW!=null) {
                allegati = TPHW.getListallegato();
                ArrayList<String> list = new ArrayList<String>();
                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, list);
                //list.add();


                TipoProve tp = TPHW.getTp();
                tipoprova.setSelection(tp.ordinal());
                Esito e = TPHW.getStato();
                esito.setSelection(e.ordinal());

                String tipoprovar = TPHW.getNomeProva();


                Matricola.setText(TPHW.getNumeroRapportoProva());

                modello.setEnabled(false);
                Matricola.setEnabled(false);
                tipoprova.setEnabled(false);
                esito.setEnabled(false);
                modello.setText(TPHW.getNomeModello());
            }


        }catch (NullPointerException | ClassCastException e){

        }

        try {
            Bundle b = getIntent().getExtras();
            String matricola = (String) b.getString("matricola");
            String model = (String) b.getString("modello");





            if(matricola!=null) {
                TPHW = new Prova();
                Matricola.setText(matricola);

                modello.setEnabled(false);
                Matricola.setEnabled(false);
               // tipoprova.setEnabled(true);
               // esito.setEnabled(true);
                modello.setText(model);

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


            }


        }catch (NullPointerException | ClassCastException e){

        }
    }
}
