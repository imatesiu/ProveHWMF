package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import it.isti.sse.provehwmf.adapter.ProveAdapter;
import it.isti.sse.provehwmf.util.Utility;

public class MisuratoreFiscaleActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


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
                startActivityForResult(i, 150);
                // startActivity(i);
            }
        });

        Button buttonSave  = (Button)  findViewById(R.id.saveMF);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(MisuratoreFiscaleActivity.this, MainActivity.class);
                setResult(Activity.RESULT_OK);//, intent);
                finish();
            }

        });


        RecyclerView rv = (RecyclerView) findViewById(R.id.cardListProve);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);


        List<String> test = new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("b");
        test.add("b");
        test.add("b");
        ProveAdapter adapter = new ProveAdapter(this, test);
        rv.setAdapter(adapter);

        RecyclerView rva = (RecyclerView) findViewById(R.id.Allegati);
        rva.setHasFixedSize(true);
        LinearLayoutManager llma = new LinearLayoutManager(this);
        llma.setOrientation(LinearLayoutManager.HORIZONTAL);
        rva.setLayoutManager(llma);

        AllegatiAdapter adaptera = new AllegatiAdapter(this, test);
        rva.setAdapter(adaptera);



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 50) { //prova
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.misuraotoreactivity);
                Snackbar.make(coordinatorLayout, "Nessun Test HW salvato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        if (requestCode == 150) { //Allegato
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
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
}
