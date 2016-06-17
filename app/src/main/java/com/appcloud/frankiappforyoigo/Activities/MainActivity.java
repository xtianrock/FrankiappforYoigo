package com.appcloud.frankiappforyoigo.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.appcloud.frankiappforyoigo.Fragments.Terminales;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;

import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context = this;
    String currentFragmentTag;
    Fragment fragment;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switchToFragment(new Terminales(), "Terminales", false);
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
        else if (id == R.id.action_load_csv_into_firebase) {

            CargarDatosAsyncTask cargarDatosAsyncTask = new CargarDatosAsyncTask();
            cargarDatosAsyncTask.execute();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        fragment = null;
        if (id == R.id.nav_camera) {
            fragment = new Terminales();
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            switchToFragment(fragment, item.getTitle().toString(), false);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void switchToFragment(Fragment fragment, String title, boolean backStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = fragment.getClass().getCanonicalName();
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);

        if (currentFragment == null || !TextUtils.equals(tag, currentFragmentTag)) {
            if (backStack) {
                currentFragmentTag = tag;
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_main, fragment, currentFragmentTag)
                        .addToBackStack(tag)
                        .commit();
            } else {
                currentFragmentTag = tag;
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_main, fragment, currentFragmentTag)
                        .commit();
            }
            setTitle(title);
        }
    }




    private class CargarDatosAsyncTask extends AsyncTask<String, Void, ArrayList<OfertaTactica>> {
        ProgressDialog dialog = new ProgressDialog(context);
        public CargarDatosAsyncTask()
        {}

        @Override
        protected ArrayList<OfertaTactica> doInBackground(String... params) {
            try {
                return Commons.importarDatos(context);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPreExecute() {
            dialog.setMessage("Cargando datos en firebase");
            dialog.show();
        }

        protected void onPostExecute( ArrayList<OfertaTactica> ofertas )
        {
            if(ofertas!=null)
            {
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                char[] caracteres = new char[]{'$','-','_','.','+','!','*','(',',',')','\'',' ','/'};
                for (OfertaTactica oferta:ofertas) {
                    String key = oferta.getTerminal();
                    for (char caracter: caracteres) {
                        key = key.replace( caracter,':');
                    }
                    Map<String, Object> postValues = oferta.toMap();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/ofertas_tacticas/" + key, postValues);
                    // childUpdates.put("/comerciales-ofertas/" + getUid() + "/" + keyOferta, postValues);
                    myRef.updateChildren(childUpdates);
                }
            }
            if(dialog.isShowing())
                dialog.dismiss();
        }
    }
}
