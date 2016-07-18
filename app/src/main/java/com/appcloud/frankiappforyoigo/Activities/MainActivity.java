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
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Fragments.HomeFragment;
import com.appcloud.frankiappforyoigo.Fragments.Reservas;
import com.appcloud.frankiappforyoigo.Fragments.Terminales;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;

import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.appcloud.frankiappforyoigo.Utils.PicassoRoundedTransformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context = this;
    private String currentFragmentTag;
    private Fragment fragment;
    private Toolbar toolbar;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseSingleton.getDatabase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        prepareNavigation();

        switchToFragment(new HomeFragment(), "Yoigo Holea Hueva", false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                //super.onBackPressed();
                if (snackbar != null && snackbar.isShown())
                    finish();
                else {
                    snackbar = Snackbar.make(toolbar, "Pulse de nuevo para salir", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
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
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        }else if(id == R.id.nav_terminales) {
            fragment = new Terminales();
        }else if(id == R.id.nav_reservas) {
        fragment = new Reservas();
    }
        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            switchToFragment(fragment, item.getTitle().toString(), false);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareNavigation(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView tvUserName = (TextView)header.findViewById(R.id.nav_user_name);
        TextView tvUserEmail = (TextView)header.findViewById(R.id.nav_user_email);
        ImageView ivUserPhoto = (ImageView)header.findViewById(R.id.nav_user_photo);
        navigationView.setNavigationItemSelectedListener(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userName = "";
            String userPhoto = "";
            List<UserInfo> providers = (List<UserInfo>) user.getProviderData();
            for (UserInfo userInfo : providers) {
                if (userInfo.getProviderId().equals("google.com")) {
                    userName = userInfo.getDisplayName();
                    if(userInfo.getPhotoUrl()!=null){
                        userPhoto = userInfo.getPhotoUrl().toString();
                    }
                }
            }
                tvUserEmail.setText(user.getEmail());
            tvUserName.setText(userName);
            Picasso.with(this).load(userPhoto).transform(new PicassoRoundedTransformation()).into(ivUserPhoto);
        }


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

        protected void onPostExecute(final ArrayList<OfertaTactica> ofertas )
        {
            if(ofertas!=null)
            {
                final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("ofertas_tacticas");

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String, Object> updateActivo = new HashMap();
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            updateActivo.put(snapshot.getKey()+"/activo",false);
                        }
                        myRef.updateChildren(updateActivo, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                char[] caracteres = new char[]{'$','-','_','.','+','!','*','(',',',')','\'',' ','/'};
                                for (OfertaTactica oferta:ofertas) {
                                    String key = oferta.getTerminal();
                                    for (char caracter: caracteres) {
                                        key = key.replace( caracter,':');
                                    }
                                    Map<String, Object> asd = oferta.toMap();
                                    myRef.child(key).updateChildren(asd);
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
            if(dialog.isShowing())
                dialog.dismiss();
        }
    }


}
