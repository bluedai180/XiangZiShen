package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.widget.Toolbar;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.AboutFragment;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.FavoritesFragment;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.FindFragment;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.HomeFragment;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.MessageFragment;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.Notification;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.RadarFragment;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.SearchFragment;
import com.blue.xiangzishen.com.blue.xiangzishen.fragment.SettingsFragment;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    private ArrayList<Fragment> fragmentArrayList;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        initToolBar();
        initNavigationView();
        initFragment();
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
                        changeFragment(0);
                        break;
                    case R.id.nav_find:
                        Toast.makeText(MainActivity.this, "find", Toast.LENGTH_SHORT).show();
                        changeFragment(1);
                        break;
                    case R.id.nav_message:
                        Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                        changeFragment(2);
                        break;
                    case R.id.nav_radar:
                        changeFragment(3);
                        break;
                    case R.id.nav_favorites:
                        changeFragment(4);
                        break;
                    case R.id.nav_settings:
                        changeFragment(5);
                        break;
                }
                return true;
            }
        });
    }

    private void initFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new FindFragment());
        fragmentArrayList.add(new MessageFragment());
        fragmentArrayList.add(new RadarFragment());
        fragmentArrayList.add(new FavoritesFragment());
        fragmentArrayList.add(new SettingsFragment());
        fragmentArrayList.add(new SearchFragment());
        fragmentArrayList.add(new Notification());
        fragmentArrayList.add(new AboutFragment());

        changeFragment(0);

    }

    private void changeFragment(int index) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        Fragment fragment = getFragmentManager().findFragmentByTag(fragmentArrayList.get(index).getClass().getName());
        if (fragment == null) {
            fragment = fragmentArrayList.get(index);
        }
        mCurrentFragment = fragment;
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment, fragment, fragment.getClass().getName());
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
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
                changeFragment(8);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_notification:
                changeFragment(7);
                result = "notification";
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                changeFragment(6);
                result = "search";
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                changeFragment(5);
                result = "settings";
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
