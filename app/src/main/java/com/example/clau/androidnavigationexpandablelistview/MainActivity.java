package com.example.clau.androidnavigationexpandablelistview;

import android.content.res.Configuration;
import android.support.annotation.Nullable;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.clau.androidnavigationexpandablelistview.Adapter.CustomExpandableListAdapter;
import com.example.clau.androidnavigationexpandablelistview.Interface.NavigationManager;



import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ExpandableListView expListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    ExpandableListAdapter listAdapterExpandable;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    //    agregado
     private NavigationManager navigationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtiene el DrawerLayout.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // obtiene el listview.
        expListView = (ExpandableListView) findViewById(R.id.expandable_list);
        // prepara datos para Header y Listado en ExpandableListView.
        prepareListData();
        // configura Adapter.
        listAdapterExpandable = new CustomExpandableListAdapter(this, listDataHeader, listDataChild);
        // configura Adapter en ExpandableListView.
        expListView.setAdapter(listAdapterExpandable);
        // Puedes expandir los grupos por default.
        int count = listAdapterExpandable.getGroupCount();
        for ( int i = 0; i < count; i++ )
            expListView.expandGroup(i);

        //agregado
        mActivityTitle = getTitle().toString();
        setupDrawer();
    if(savedInstanceState==null)
            selectFirstItemAsDefault();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Plenario");

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header,null,false);
        expListView.addHeaderView(listHeaderView);
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Agrega Encabezados.
        listDataHeader.add("Factoring");
        listDataHeader.add("Préstamos");
        listDataHeader.add("Gestión");
        listDataHeader.add("Contabilidad");

        // Agrega datos.
        List<String> SubItems = new ArrayList<String>();
        SubItems.add("Archivos");
        SubItems.add("Busquedas");
        SubItems.add("Informes");
        SubItems.add("Operaciones");

        listDataChild.put(listDataHeader.get(0), SubItems);
        listDataChild.put(listDataHeader.get(1), SubItems);
        listDataChild.put(listDataHeader.get(2), SubItems);
        listDataChild.put(listDataHeader.get(3), SubItems);
    }

    //agregado
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Regresar");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();

            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
        private void selectFirstItemAsDefault() {
        if (navigationManager != null)
        {
            String firstItem = listDataHeader.get(0);
            navigationManager.showFragment(firstItem);
            getSupportActionBar().setTitle(firstItem);
        }
    }
}
