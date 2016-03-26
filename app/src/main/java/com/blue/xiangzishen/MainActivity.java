package com.blue.xiangzishen;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends Activity {
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFloatingButtion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        initToolBar();
        initNavigationView();
        initFloatingButtion();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(mNavigationView);
            }
        });
    }

    private void initFloatingButtion() {
        mFloatingButtion = (FloatingActionButton) findViewById(R.id.btn_floatting);
        mFloatingButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "floatingbutton", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_discussion:
                        Toast.makeText(MainActivity.this, "discussion", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String result;
        switch (item.getItemId()) {
            case R.id.action_about:
                result = "about";
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_notification:
                result = "notification";
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                result = "search";
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                result = "settings";
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
