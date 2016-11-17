package it.isti.sse.provehwmf;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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

        FloatingActionButton provaHW = (FloatingActionButton) findViewById(R.id.MF_add_Prova1);
        provaHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(MisuratoreFiscaleActivity.this, ProvaActivity.class);
                startActivity(i);
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
