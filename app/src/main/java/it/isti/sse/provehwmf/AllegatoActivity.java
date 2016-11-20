package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.isti.sse.provehwmf.pojo.Allegato;
import it.isti.sse.provehwmf.pojo.TipoProve;
import it.isti.sse.provehwmf.util.Utility;

public class AllegatoActivity extends AppCompatActivity {

    private Spinner matricole;
    private Spinner tipoprova;
    private File fileUriCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allegato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        matricole = (Spinner)  findViewById(R.id.spinner3);
        tipoprova = (Spinner)  findViewById(R.id.spinner4);


        FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuAHW);
        menuRed.setClosedOnTouchOutside(true);

        setInit();
        com.github.clans.fab.FloatingActionButton notaHW = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.A_add_note);
        notaHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuAHW);
                menuRed.close(false);
                Intent i = new Intent(AllegatoActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
                startActivityForResult(i,10);
            }
        });

        com.github.clans.fab.FloatingActionButton cameraHW = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.A_add_Camera);
        cameraHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuAHW);
                menuRed.close(false);
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                takePictureIntent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 2 * 1024 * 1024);
                fileUriCamera = Utility.getOutputMediaFile(); // create a file to save the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUriCamera); // set the image file name


                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 100);
                    // startActivity(takePictureIntent);
                }
                // Intent i = new Intent(ProvaActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
                // startActivity(i);
            }
        });

        com.github.clans.fab.FloatingActionButton attachHW = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.A_add_Doc);
        attachHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuAHW);
                menuRed.close(false);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("*/*");      //all files
                //intent.setType("text/xml");   //XML file only
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 1);

                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(view.getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
                // Intent i = new Intent(ProvaActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
                // startActivity(i);
            }
        });

        Button buttonSave  = (Button)  findViewById(R.id.saveAllegato);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                setResult(Activity.RESULT_OK);//, intent);
                finish();
            }

        });

        buttonSave.setEnabled(false);
        buttonSave.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) { //File
            if(resultCode == Activity.RESULT_OK){
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "File Caricato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Uri uri = data.getData();

                Allegato a = createAllegato(new File(uri.getPath()),"FILE");
                Intent i = getIntent().putExtra("newAllegatoFile",a);
                //TODO:caricafile
                setResult(Activity.RESULT_OK,i);//, intent);
                finish();
              //  String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "Nessun allegato selezionato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        if (requestCode == 10) { // Note
            if(resultCode == Activity.RESULT_OK){
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "Nota Salvata", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Allegato a = createAllegato(data,"NOTA");
                Intent i = getIntent().putExtra("newAllegatoNote",a);

                setResult(Activity.RESULT_OK,i);//, intent);
                finish();
               // String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "Nessuna Nota salvata", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        }
        if (requestCode == 100) { //camera
            if(resultCode == Activity.RESULT_OK){
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "Immagine Salvata", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Allegato a = createAllegato(fileUriCamera,"JPG");
                Intent i = getIntent().putExtra("newAllegatoFoto",a);
                setResult(Activity.RESULT_OK,i);//, intent);
                finish();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "Nessuna immagine selezionata", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Allegato createAllegato(Intent data, String tipo) {
        Allegato a = cAllegato(tipo);
        Bundle b = data.getExtras();
        String nota =  b.getString("nota");
        a.setNome("NOTA");
        a.setDati(nota);

        return  a;
    }

    private Allegato cAllegato(String tipo) {
        Allegato a = new Allegato();
        String mtricolafiscale = matricole.getSelectedItem().toString();
        a.setMatricola(mtricolafiscale);
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
        a.setTime(timeStamp);
        a.setTime(tipo);
        //TODO: userid
        a.setUserid("Ge");
        a.setNome("");
        a.setDati("");
        a.setUrl("");
        return  a;
    }

    private Allegato createAllegato(File data, String tipo) {
        Allegato a = cAllegato(tipo);
        a.setNome(data.getName());
        a.setDati("");
        a.setUrl(data.getPath());
        return  a;
    }

    @Override
    public void onBackPressed() {
        // When the user hits the back button set the resultCode
        // to Activity.RESULT_CANCELED to indicate a failure
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }


    public void setInit() {


        try {
            Bundle b = getIntent().getExtras();
            ArrayList<String> ListMF = (ArrayList<String> ) b.getSerializable("ListaMatricoleMF");

            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, ListMF);

            matricole.setAdapter(adapter);


           // tipoprova.setEnabled(true);
           // matricole.setEnabled(true);



        }catch (NullPointerException | ClassCastException e){

        }


        try {
            Bundle b = getIntent().getExtras();
            String Matricola =  b.getString("MatricolaMF");

            ArrayList<String> ListMF = new ArrayList<>();
            ListMF.add(Matricola);
            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, ListMF);

            matricole.setAdapter(adapter);

            String prova =  b.getString("ProvaMF");

            TipoProve tp = (TipoProve) b.getSerializable("TipoProva");
            
            tipoprova.setSelection(tp.ordinal());

            // tipoprova.setEnabled(true);
            // matricole.setEnabled(true);



        }catch (NullPointerException | ClassCastException e){

        }
    }

}
