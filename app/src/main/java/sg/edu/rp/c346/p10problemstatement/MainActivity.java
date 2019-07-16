package sg.edu.rp.c346.p10problemstatement;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager viewPager;
    Integer CPage;

    Button btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager1);
        btnRead = findViewById(R.id.btnRead);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());


        adapter = new MyFragmentPagerAdapter(fm, al);

        viewPager.setAdapter(adapter);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent i = new Intent(MainActivity.this, MyReceiver.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(MainActivity.this, 1, i, 0);
                Calendar now = Calendar.getInstance();
                now.add(Calendar.SECOND , 300);
                am.set(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), pIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actprevious) {
            if (viewPager.getCurrentItem() > 0){
                int previousPage = viewPager.getCurrentItem() - 1;
                viewPager.setCurrentItem(previousPage, true);
            }
        } else if (item.getItemId() == R.id.actrandom) {
            Random r = new Random();
            int page = r.nextInt(viewPager.getChildCount());
            viewPager.setCurrentItem(page, true);
        } else if (item.getItemId() == R.id.actnext) {
            int max = viewPager.getChildCount();
            if (viewPager.getCurrentItem() < max-1){
                int nextPage = viewPager.getCurrentItem() + 1;
                viewPager.setCurrentItem(nextPage, true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        CPage = viewPager.getCurrentItem();
        prefEdit.putInt("currentpage", CPage);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        CPage = prefs.getInt("currentpage", 0);
        viewPager.setCurrentItem(CPage);
    }

}
