package mx.iteso.tarea5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

import mx.iteso.tarea5.tools.Constants;

public class ActivityMain extends AppCompatActivity {
    private ViewPager viewPager;
    FragmentTechnology fragmentTechnology = null;
    SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_activity_main_logout:
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                intent = new Intent(ActivityMain.this, ActivityLogin.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.menu_activity_main_privacy_policy:
                intent = new Intent(ActivityMain.this, ActivityPrivacyPolicy.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                fragmentTechnology.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {super(fm);}
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                default:
                    if(fragmentTechnology == null) {
                        fragmentTechnology = new FragmentTechnology();
                    }
                    return fragmentTechnology;
            }
        }
        @Override
        public int getCount() {
            return 1;
        }
    }
}
