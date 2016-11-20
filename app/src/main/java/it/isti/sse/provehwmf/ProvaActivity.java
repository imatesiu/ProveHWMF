package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.isti.sse.provehwmf.adapter.AllegatiAdapter;
import it.isti.sse.provehwmf.pojo.Allegati;
import it.isti.sse.provehwmf.pojo.Esito;
import it.isti.sse.provehwmf.pojo.ProvaHW;
import it.isti.sse.provehwmf.pojo.TipoProve;

public class ProvaActivity extends AppCompatActivity {

    //private Spinner modello;
    private EditText modello;
    private EditText Matricola;
    private Spinner tipoprova;
    private Spinner esito;
    private  Allegati allegati;

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
                i.putExtra("TipoProva",tipoprova.getSelectedItemPosition());
                // i.putExtra("key","value");
                startActivityForResult(i, 190);
            }
        });

        Button buttonSave  = (Button)  findViewById(R.id.savePHW);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                ProvaHW t = new ProvaHW();


                String model = modello.getText().toString();
                t.setModello(model);
                t.setMatricola(Matricola.getText().toString());

                t.setTipo(tipoprova.getSelectedItem().toString());
                Esito e = Esito.values()[esito.getSelectedItemPosition()];

                t.setEsito(e);

               t.setAllegati(allegati);
                String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
                t.setTimeStartPHW(timeStamp);



              //  setResult(Activity.RESULT_OK);


                setResult(Activity.RESULT_OK,getIntent().putExtra("newProva", t));//, intent);
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
                    tipoprova.setEnabled(true);
                    modello.setEnabled(true);
                    Matricola.setEnabled(true);
                    esito.setEnabled(true);
                }
            }

        });

        RecyclerView rva = (RecyclerView)findViewById(R.id.AllegatiProva);
        rva.setHasFixedSize(true);
        LinearLayoutManager llma = new LinearLayoutManager(this);
        llma.setOrientation(LinearLayoutManager.HORIZONTAL);
        rva.setLayoutManager(llma);

        allegati = new Allegati();
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
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.proveactivity);
                Snackbar.make(coordinatorLayout, "Nessun Allegato Salvato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    public void setInit(Allegati init) {
        try {
            Bundle b = getIntent().getExtras();
            ProvaHW TPHW = (ProvaHW) b.getSerializable("Prova");
            if(TPHW!=null) {
                allegati = TPHW.getAllegati();
                ArrayList<String> list = new ArrayList<String>();
                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, list);
                //list.add();


                TipoProve tp = TipoProve.AlimentazioneSenzaVincoloFiscale;
                tipoprova.setSelection(tp.ordinal());
                Esito e = TPHW.getEsito();
                esito.setSelection(e.ordinal());

                String tipoprovar = TPHW.getTipo();


                Matricola.setText(TPHW.getMatricola());

                modello.setEnabled(false);
                Matricola.setEnabled(false);
                tipoprova.setEnabled(false);
                esito.setEnabled(false);
                modello.setText(TPHW.getModello());
            }


        }catch (NullPointerException | ClassCastException e){

        }

        try {
            Bundle b = getIntent().getExtras();
            String matricola = (String) b.getString("matricola");
            String model = (String) b.getString("modello");





            if(matricola!=null) {

                Matricola.setText(matricola);

                modello.setEnabled(false);
                Matricola.setEnabled(false);
               // tipoprova.setEnabled(true);
               // esito.setEnabled(true);
                modello.setText(model);
            }


        }catch (NullPointerException | ClassCastException e){

        }
    }
}
