package owuor91.com.kenyanproverbs;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Proverbs extends AppCompatActivity {

     public static ArrayList<String> provArrayList;
     public static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proverbs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        swipeCards();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/ArchitectsDaughter.ttf").setFontAttrId(R.attr.fontPath).build());


    }

    private void swipeCards(){
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        String[] provArray = getResources().getStringArray(R.array.proverbsArray);
        provArrayList = new ArrayList<String>(Arrays.asList(provArray));

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, provArrayList);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                provArrayList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                provArrayList.add("No more Kenyan Proverbs to show at this moment");
                arrayAdapter.notifyDataSetChanged();
                i++;
            }

            @Override
            public void onScroll(float v) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
