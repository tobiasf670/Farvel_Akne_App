package com.example.farvel.farvel_akne;

import android.app.FragmentManager;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Farvel Akne");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getFragmentManager().beginTransaction().replace(R.id.content_frame1, new NewKonsulationFragment()).commit();
        btn1 = (Button) findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(getFragmentManager().findFragmentById(R.id.content_frame1).getClass().equals(IndboxFragment.class))) {
                    getFragmentManager().beginTransaction().replace(R.id.content_frame1, new IndboxFragment()).addToBackStack(null).commit();
                }
                }
        });
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(getFragmentManager().findFragmentById(R.id.content_frame1).getClass().equals(BehandlingFragment.class))) {
                    getFragmentManager().beginTransaction().replace(R.id.content_frame1, new BehandlingFragment()).addToBackStack(null).commit();
                }
            }
        });
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(getFragmentManager().findFragmentById(R.id.content_frame1).getClass().equals(ProfilFragment.class))) {
                    getFragmentManager().beginTransaction().replace(R.id.content_frame1, new ProfilFragment()).addToBackStack(null).commit();
                }
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setColorOnBtn(int c){

        switch (c) {
            case R.layout.fragment_profil:
                btn1.setBackgroundResource(R.drawable.onclick);
                btn2.setBackgroundResource(R.drawable.onclick);
                btn3.setBackgroundColor(getResources().getColor(R.color.colorGray));
                break;
            case R.layout.messages_layout:
                btn1.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btn2.setBackgroundResource(R.drawable.onclick);
                btn3.setBackgroundResource(R.drawable.onclick);
                break;
            case R.layout.indbox:
                btn1.setBackgroundResource(R.drawable.onclick);
                btn2.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btn3.setBackgroundResource(R.drawable.onclick);
                break;
            default:
                break;
        }
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



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManger = getFragmentManager();


        if (id == R.id.nav_first_layout) {

            if(!(getFragmentManager().findFragmentById(R.id.content_frame1).getClass().equals(NewKonsulationFragment.class))){
                fragmentManger.beginTransaction().replace(R.id.content_frame1, new NewKonsulationFragment()).addToBackStack(null).commit();

        }else {
            Toast.makeText(this, "Du er allerede på Min Ny Konsulation", Toast.LENGTH_SHORT).show();}

            // Handle the camera action
        } else if (id == R.id.nav_secound_layout) {
            if(!(getFragmentManager().findFragmentById(R.id.content_frame1).getClass().equals(IndboxFragment.class))){
                fragmentManger.beginTransaction().replace(R.id.content_frame1, new IndboxFragment()).addToBackStack(null).commit();

        }else {
            Toast.makeText(this, "Du er allerede på Min Beskeder", Toast.LENGTH_SHORT).show();}

           // fragmentManger.beginTransaction().replace(R.id.content_frame, new IndboxFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_third_layout) {
            if(!(getFragmentManager().findFragmentById(R.id.content_frame1).getClass().equals(BehandlingFragment.class))){
                fragmentManger.beginTransaction().replace(R.id.content_frame1, new BehandlingFragment()).addToBackStack(null).commit();

        }else {
            Toast.makeText(this, "Du er allerede på Min BehandlingFragment", Toast.LENGTH_SHORT).show();}




        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
