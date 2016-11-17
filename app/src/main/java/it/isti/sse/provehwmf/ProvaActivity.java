package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.isti.sse.provehwmf.adapter.AllegatiAdapter;
import it.isti.sse.provehwmf.util.Utility;

public class ProvaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPHW);
        setSupportActionBar(toolbar);

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


        FloatingActionButton notaHW = (FloatingActionButton) findViewById(R.id.P_add_note);
        notaHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
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
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
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
        });

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
            }
        });

        Button buttonSave  = (Button)  findViewById(R.id.savePHW);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                setResult(Activity.RESULT_OK);//, intent);
                finish();
            }

        });

        RecyclerView rva = (RecyclerView)findViewById(R.id.AllegatiProva);
        rva.setHasFixedSize(true);
        LinearLayoutManager llma = new LinearLayoutManager(this);
        llma.setOrientation(LinearLayoutManager.HORIZONTAL);
        rva.setLayoutManager(llma);

        List<String> test = new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("b");test.add("b");test.add("b");
        AllegatiAdapter adaptera = new AllegatiAdapter(this,test);
        rva.setAdapter(adaptera);
    }

}
