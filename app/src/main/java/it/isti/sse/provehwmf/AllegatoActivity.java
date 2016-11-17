package it.isti.sse.provehwmf;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
                // Intent i = new Intent(ProvaActivity.this, NoteActivity.class);
                // i.putExtra("key","value");
                // startActivity(i);
            }
        });

    }

}
