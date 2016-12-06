package it.isti.sse.provehwmf;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import isti.cnr.sse.rest.data.Allegato;
import isti.cnr.sse.rest.data.ModelloMF;
import isti.cnr.sse.rest.data.Prova;
import it.isti.sse.provehwmf.adapter.MisuratoriFiscaleAdapter;
import it.isti.sse.provehwmf.adapter.MyClickListener;

import it.isti.sse.provehwmf.util.JsonFactory;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<ModelloMF> LMF;
    private MisuratoriFiscaleAdapter adapter;
    private String URLbase = "192.168.1.30"; //146.48.84.52

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requestPermission();
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
                Pair<ArrayList<String>, ArrayList<String>> ListMFM = getNomeNumRapport(LMF);
                i.putExtra("ListaMatricoleMF",ListMFM.first);
                i.putExtra("ListaModelliMF",ListMFM.second);
                //i.putExtra("key","value");
                startActivityForResult(i,530);
                //startActivity(i);
            }
        });

        RecyclerView rv = (RecyclerView)findViewById(R.id.ElencoMF);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        LMF = readData();

        adapter = new MisuratoriFiscaleAdapter(this,LMF);


        adapter.setMyClickListener(new MyClickListener() {

            @Override
            public void onItemClick(int position, ModelloMF MF) {
                Intent i = new Intent(MainActivity.this, MisuratoreFiscaleActivity.class);
                i.putExtra("MF",MF);
                startActivityForResult(i,550);
            }
        });
        rv.setAdapter(adapter);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private Pair<ArrayList<String>,ArrayList<String>> getNomeNumRapport(List<ModelloMF> lmf) {
        ArrayList<String> ListaMF = new ArrayList<>();
        ListaMF.add("");
        ArrayList<String> ListaM = new ArrayList<>();
        ListaM.add("");
        for (ModelloMF MF:lmf) {
            ListaMF.add(MF.getNumeroRapportoProva());
            ListaM.add(MF.getNomeModello());
        }
        return new Pair<>(ListaMF,ListaM);
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
            

            SendGetData();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPause(){
        saveData(LMF);
        super.onPause();
    }

    private void SendGetData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://"+URLbase+":9090/cnr/sse/testhw/misuratorifiscali/";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Type type = new TypeToken<ArrayList<ModelloMF>>() {}.getType();
                        Gson gson = new Gson();
                        ArrayList<ModelloMF> list = gson.fromJson(response, type);
                        LMF.clear();
                        LMF.addAll(list);
                        adapter.notifyDataSetChanged();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        Snackbar.make(drawer, "OK Dati Scaricati", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                Snackbar.make(drawer, "Problema\\nNessun MF Downloadato", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void postSendData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://"+URLbase+":9090/cnr/sse/testhw/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                Snackbar.make(drawer, "Problema\\nNessun Invio Effettuto", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }

        }){
            @Override
            public byte[] getBody()  {
                try {
                    Gson gson = new Gson();
                    String str = gson.toJson(LMF);
                    return str == null ? null : str.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };
    }
    private void saveData(List<ModelloMF> LMF){
        try {
            FileOutputStream fos = openFileOutput("dataLMF.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(LMF);
            fos.close();
        }catch (IOException e){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            Snackbar.make(drawer, "Problema\\nNessun MF Salvato", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

    private List<ModelloMF> readData() {
        try {
            FileInputStream streamIn = openFileInput("dataLMF.ser");
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            List<ModelloMF> readCase = (List<ModelloMF>) objectinputstream.readObject();
            return readCase;
        } catch (Exception e) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            Snackbar.make(drawer, "Problema\\nNessun MF Caricato", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        return new ArrayList<>();
    }

    private void requestPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,Manifest.permission.INTERNET
                            ,Manifest.permission.READ_EXTERNAL_STORAGE
                            ,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION},
                    900);
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 900: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted

                } else {
                    //TODO:close app
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 550) { //MF
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Bundle b = data.getExtras();
                    ModelloMF MF = (ModelloMF) b.getSerializable("newMF");
                    LMF.add(MF);
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    Snackbar.make(drawer, "Salvato MF", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }catch (NullPointerException | ClassCastException e){
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    Snackbar.make(drawer, "Nessun MF Salvato", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

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
                    try {
                        Bundle b = data.getExtras();
                        Prova NPHW = (Prova) b.getSerializable("newProva");
                        insert(LMF, NPHW);
                        adapter.notifyDataSetChanged();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        Snackbar.make(drawer, "Prova Salvata", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }catch (NullPointerException | ClassCastException e){

                    }
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
                        try {
                            Bundle b = data.getExtras();
                            Allegato a = (Allegato) b.getSerializable("newAllegato");
                            insertAllegato(LMF, a);
                            adapter.notifyDataSetChanged();

                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            Snackbar.make(drawer, "Allegato Salvato", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }catch (NullPointerException | ClassCastException e){

                        }

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

    private void insertAllegato(List<ModelloMF> lmf, Allegato a) {
        for (ModelloMF m :lmf) {
            if(m.getNumeroRapportoProva().equals(a.getNumeroRapportoProva())){
                for(Prova p : m.getProve()) {
                    if(p.getTp().toString().equals(a.getTipoprova())) {
                        p.getListallegato().add(a);
                        break;
                    }
                }
            }
        }
    }

    private void insert(List<ModelloMF> lmf, Prova nphw) {
        for (ModelloMF m :lmf) {
            if(m.getNumeroRapportoProva().equals(nphw.getNumeroRapportoProva())){
                m.getProve().add(nphw);
                break;
            }
        }
    }
}
