package it.isti.sse.provehwmf;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.isti.sse.provehwmf.adapter.AllegatiAdapter;
import it.isti.sse.provehwmf.adapter.ProveAdapter;
import it.isti.sse.provehwmf.util.Utility;

public class MisuratoreFiscaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misuratore_fiscale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMF);
        setSupportActionBar(toolbar);

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
                //i.putExtra("key","value");
                startActivity(i);

            }
        });

        FloatingActionButton notaHW = (FloatingActionButton) findViewById(R.id.MF_add_note1);
        notaHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuMF);
                menuRed.close(false);
                Intent i = new Intent(MisuratoreFiscaleActivity.this, NoteActivity.class);
               // i.putExtra("key","value");
                startActivity(i);
            }
        });

        FloatingActionButton cameraHW = (FloatingActionButton) findViewById(R.id.MF_add_Camera1);
        cameraHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menuMF);
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
                // startActivity(i);
            }
        });


        RecyclerView rv = (RecyclerView)findViewById(R.id.cardListProve);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);


        List<String> test = new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("b");test.add("b");test.add("b");
        ProveAdapter adapter = new ProveAdapter(this,test);
        rv.setAdapter(adapter);

        RecyclerView rva = (RecyclerView)findViewById(R.id.Allegati);
        rva.setHasFixedSize(true);
        LinearLayoutManager llma = new LinearLayoutManager(this);
        llma.setOrientation(LinearLayoutManager.HORIZONTAL);
        rva.setLayoutManager(llma);

        AllegatiAdapter adaptera = new AllegatiAdapter(this,test);
        rva.setAdapter(adaptera);


    }

}
