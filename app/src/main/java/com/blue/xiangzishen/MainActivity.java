package com.blue.xiangzishen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "navigation", Toast.LENGTH_SHORT).show();
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
