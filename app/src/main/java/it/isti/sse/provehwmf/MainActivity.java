package it.isti.sse.provehwmf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import it.isti.sse.provehwmf.adapter.MisuratoriFiscaleAdapter;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menu);
        menuRed.setClosedOnTouchOutside(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_MF);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menu);
                menuRed.close(false);
                Intent i = new Intent(MainActivity.this, MisuratoreFiscaleActivity.class);
                startActivityForResult(i,550);
            }
        });

        FloatingActionButton provaHW = (FloatingActionButton) findViewById(R.id.add_Prova);
        provaHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menu);
                menuRed.close(false);
                Intent i = new Intent(MainActivity.this, ProvaActivity.class);
                startActivityForResult(i,540);
            }
        });






        FloatingActionButton attachHW = (FloatingActionButton) findViewById(R.id.add_Doc);
        attachHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FloatingActionMenu menuRed = (FloatingActionMenu) findViewById(R.id.menu);
                menuRed.close(false);
                Intent i = new Intent(MainActivity.this, AllegatoActivity.class);
                // i.putExtra("key","value");
                startActivityForResult(i,530);
                //startActivity(i);
            }
        });

        RecyclerView rv = (RecyclerView)findViewById(R.id.ElencoMF);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);


        List<String> test = new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("b");test.add("b");test.add("b");
        MisuratoriFiscaleAdapter adapter = new MisuratoriFiscaleAdapter(this,test);
        rv.setAdapter(adapter);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_receive) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 550) { //MF
            if (resultCode == Activity.RESULT_OK) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                Snackbar.make(drawer, "Salvato MF", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //String result = data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                Snackbar.make(drawer, "Nessun MF Salvato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }else{
            if (requestCode == 540) { //Prova
                if(resultCode == Activity.RESULT_OK){
                  //  String result=data.getStringExtra("result");
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    Snackbar.make(drawer, "Nessuna Test HW Salvato", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }else {
                if (requestCode == 530) { //Allegato
                    if(resultCode == Activity.RESULT_OK){
                       // String result=data.getStringExtra("result");
                    }
                    if (resultCode == Activity.RESULT_CANCELED) {
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        Snackbar.make(drawer, "Nessun Allegato Salvato", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        }


    }
}
