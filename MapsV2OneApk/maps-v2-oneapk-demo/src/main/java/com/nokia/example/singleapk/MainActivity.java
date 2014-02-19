package com.nokia.example.singleapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
    private static final String HERE_LIBRARY = "com.here.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment;
        if (hasHere()) {
            fragment = new MapsFragmentHere();
        } else {
            fragment = new MapsFragmentGoogle();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.map, fragment).commit();
    }

    /**
     * Checks if HERE library (com.here.android) is available on the device
     *
     * @return true if HERE library is found
     */
    private boolean hasHere() {
        String[] systemSharedLibraryNames = getPackageManager().getSystemSharedLibraryNames();
        for (String library : systemSharedLibraryNames) {
            if (HERE_LIBRARY.equals(library))
                return true;
        }

        return false;
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}