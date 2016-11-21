package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
import it.isti.sse.provehwmf.util.Utility;
import android.widget.AdapterView.OnItemSelectedListener;


public class AllegatoActivity extends AppCompatActivity {

    private Spinner matricole;
    private Spinner modello;
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
        modello = (Spinner)  findViewById(R.id.spinner6);

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

                Allegato a = createAllegato(uri,"FILE");
                Intent i = getIntent().putExtra("newAllegato",a);
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
                Intent i = getIntent().putExtra("newAllegato",a);

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
                Intent i = getIntent().putExtra("newAllegato",a);
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
        a.setTipoProva(tipoprova.getSelectedItem().toString());
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
        a.setTime(timeStamp);
        a.setTipo(tipo);
        a.setModello(modello.getSelectedItem().toString());
        //TODO: userid
        a.setUserid("Ge");
        a.setNome("");
        a.setDati("");
        a.setUrl("");
        return  a;
    }

    private Allegato createAllegato(Uri data, String tipo) {
        Allegato a = cAllegato(tipo);
        a.setNome(getFileName(data));
        a.setDati("");
        a.setUrl(data.getPath());
        return  a;
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
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
            ArrayList<String> ListModelliMF = (ArrayList<String> ) b.getSerializable("ListaModelliMF");
            if(ListMF!=null) {
                ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.row, ListMF);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                matricole.setAdapter(adapter);

                matricole.setOnItemSelectedListener(new  OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                            modello.setSelection(position);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                ArrayAdapter adapter2 = new ArrayAdapter<String>(this,
                        R.layout.row, ListModelliMF);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                modello.setAdapter(adapter2);
                modello.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        matricole.setSelection(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
           // tipoprova.setEnabled(true);
           // matricole.setEnabled(true);



        }catch (NullPointerException | ClassCastException e){

        }


        try {
            Bundle b = getIntent().getExtras();
            String Matricola =  b.getString("MatricolaMF");
            String model =  b.getString("ModelloMF");
            if(Matricola!=null) {
                ArrayList<String> ListMF = new ArrayList<>();
                ListMF.add(Matricola);
                ArrayAdapter adapter = new ArrayAdapter<String>(this,
                        R.layout.row, ListMF);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                matricole.setAdapter(adapter);
                matricole.setSelection(0);
                matricole.setEnabled(false);

                if(model!=null){
                    ArrayList<String> ListModel = new ArrayList<>();
                    ArrayAdapter adapters = new ArrayAdapter<String>(this,
                            R.layout.row, ListModel);
                    adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ListModel.add(model);
                    modello.setAdapter(adapters);
                    modello.setSelection(0);
                    modello.setEnabled(false);
                }

                String tpw = (String) b.getString("TipoProva");
                if(tpw!=null) {
                    int tp = Integer.valueOf(tpw);
                    tipoprova.setSelection(tp+1);

                    tipoprova.setEnabled(false);
                }


            }

        }catch (NullPointerException | ClassCastException e){

        }
    }

}
