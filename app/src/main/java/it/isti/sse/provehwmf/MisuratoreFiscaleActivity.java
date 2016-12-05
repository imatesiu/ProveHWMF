package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isti.cnr.sse.rest.data.Allegato;
import isti.cnr.sse.rest.data.ModelloMF;
import isti.cnr.sse.rest.data.Prova;
import it.isti.sse.provehwmf.adapter.AllegatiAdapter;
import it.isti.sse.provehwmf.adapter.MyClickListenerProve;
import it.isti.sse.provehwmf.adapter.ProveAdapter;

import it.isti.sse.provehwmf.util.Utility;

public class MisuratoreFiscaleActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private EditText modello;
    private EditText produttore;
    private EditText Matricola;
    private List<Prova> PHW;
    private List<Allegato> allegati;
    private ProveAdapter adapter;
    private AllegatiAdapter adaptera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misuratore_fiscale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMF);
        setSupportActionBar(toolbar);

        modello = (EditText)findViewById(R.id.editText);
        produttore = (EditText)findViewById(R.id.editText2);
        Matricola = (EditText)findViewById(R.id.editText3);


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.MF_add_Prova1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuMF);
        menuRed.setClosedOnTouchOutside(true);

        FloatingActionButton provaHW = (FloatingActionButton) findViewById(R.id.MF_add_Prova1);
        provaHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuMF);
                menuRed.close(false);
                Intent i = new Intent(MisuratoreFiscaleActivity.this, ProvaActivity.class);
                i.putExtra("matricola".toString(),Matricola.getText().toString());
                i.putExtra("modello".toString(),modello.getText().toString());

                startActivityForResult(i, 50);

            }
        });


        FloatingActionButton attachHW = (FloatingActionButton) findViewById(R.id.MF_add_Doc1);
        attachHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuMF);
                menuRed.close(false);
                // Intent i = new Intent(ProvaActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
                Intent i = new Intent(MisuratoreFiscaleActivity.this, AllegatoActivity.class);
                // i.putExtra("key","value");
                i.putExtra("MatricolaMF",Matricola.getText().toString());
                i.putExtra("ModelloMF",modello.getText().toString());
                startActivityForResult(i, 150);
                // startActivity(i);
            }
        });

        Button buttonSave  = (Button)  findViewById(R.id.saveMF);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                ModelloMF MF = new ModelloMF();
                String model = modello.getText().toString();
                MF.setNomeDitta(model);
                MF.setNumeroRapportoProva(Matricola.getText().toString());
                MF.setNomeDitta(produttore.getText().toString());
                MF.setProve(PHW);
                //String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
               // MF.setTimeMFStart(timeStamp);



                setResult(Activity.RESULT_OK, getIntent().putExtra("newMF", MF));
                finish();
            }

        });

        Button buttonEdit  = (Button)  findViewById(R.id.editMF);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                if(modello.isEnabled()){
                    modello.setEnabled(false);
                    produttore.setEnabled(false);
                    Matricola.setEnabled(false);
                }else {
                    modello.setEnabled(true);
                    produttore.setEnabled(true);
                    Matricola.setEnabled(true);
                }
            }

        });


        RecyclerView rv = (RecyclerView) findViewById(R.id.cardListProve);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);





        PHW = new ArrayList<>();
        allegati = new ArrayList<>();
        try {
            Bundle b = getIntent().getExtras();
            ModelloMF MF = (ModelloMF) b.getSerializable("MF");
            produttore.setText(MF.getNomeDitta());
            modello.setText(MF.getNomeModello());
            Matricola.setText(MF.getNumeroRapportoProva());

            modello.setEnabled(false);
            produttore.setEnabled(false);
            Matricola.setEnabled(false);

          // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            //imm.hideSoftInputFromWindow( getWindow().getCurrentFocus().getWindowToken(),  InputMethodManager.HIDE_NOT_ALWAYS);


            PHW =  MF.getProve();

            allegati= new ArrayList<>();
            for (Prova p : PHW){
                allegati.addAll(p.getListallegato());
            }



        }catch (NullPointerException | ClassCastException e){

        }

        adapter = new ProveAdapter(this, PHW);
        rv.setAdapter(adapter);
        adapter.setMyClickListener(new MyClickListenerProve() {
            @Override
            public void onItemClick(Prova provaHW) {
                Intent i = new Intent(MisuratoreFiscaleActivity.this, ProvaActivity.class);
                i.putExtra("Prova",provaHW);
                startActivityForResult(i, 50);
            }
        });

        RecyclerView rva = (RecyclerView) findViewById(R.id.Allegati);
        rva.setHasFixedSize(true);
        LinearLayoutManager llma = new LinearLayoutManager(this);
        llma.setOrientation(LinearLayoutManager.HORIZONTAL);
        rva.setLayoutManager(llma);

        adaptera = new AllegatiAdapter(this, allegati);
        rva.setAdapter(adaptera);



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 50) { //prova
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Bundle b = data.getExtras();
                    Prova NPHW = (Prova) b.getSerializable("newProva");

                    PHW.add(NPHW);
                    adapter.notifyDataSetChanged();
                    allegati.addAll(NPHW.getListallegato());
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.misuraotoreactivity);
                    Snackbar.make(coordinatorLayout, "Test HW Salvato", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();



                }catch (NullPointerException | ClassCastException e){
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.misuraotoreactivity);
                    Snackbar.make(coordinatorLayout, "Nessun Test HW salvato", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.misuraotoreactivity);
                Snackbar.make(coordinatorLayout, "Nessun Test HW salvato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        if (requestCode == 150) { //Allegato
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Bundle b = data.getExtras();
                    Allegato a = (Allegato) b.getSerializable("newAllegato");

                    insertAllegato(PHW, a);

                    allegati.add(a);
                    adapter.notifyDataSetChanged();
                    adaptera.notifyDataSetChanged();


                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.misuraotoreactivity);
                    Snackbar.make(coordinatorLayout, "Allegato Salvato", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }catch (NullPointerException | ClassCastException e){

                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.misuraotoreactivity);
                Snackbar.make(coordinatorLayout, "Nessun Allegato Salvato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        // When the user hits the back button set the resultCode
        // to Activity.RESULT_CANCELED to indicate a failure
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    private void insertAllegato(List<Prova> PHW, Allegato a) {

                for(Prova p :PHW) {
                    if(p.getTp().toString().equals(a.getTipoprova())) {
                        p.getListallegato().add(a);
                        break;
                    }
                }

    }
}
