package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.io.File;

import it.isti.sse.provehwmf.util.Utility;

public class AllegatoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allegato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuAHW);
        menuRed.setClosedOnTouchOutside(true);


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
                startActivity(i);
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
                intent.setType("*/*");      //all files
                //intent.setType("text/xml");   //XML file only
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



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "Nessun allegato selezionato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        if (requestCode == 100) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.allegatoactivity);
                Snackbar.make(coordinatorLayout, "Nessun allegato selezionato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
