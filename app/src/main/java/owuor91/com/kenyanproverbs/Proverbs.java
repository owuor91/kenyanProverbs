package owuor91.com.kenyanproverbs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Proverbs extends AppCompatActivity {

     public static ArrayList<String> provStringArrayList;
    public ArrayList<Proverb> proverbArrayList;
     public static ArrayAdapter arrayAdapter;
    public static TextView txtMessage;

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



        //Toast.makeText(this, flingContainer.getTopCardListener().getLastPoint().toString(), Toast.LENGTH_SHORT).show();

    }

    private void swipeCards(){
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        String[] provStringArray = getResources().getStringArray(R.array.proverbsArray);

        Proverb[] arrayOfProverbs = new Proverb[provStringArray.length];

        for (int k=0; k < provStringArray.length; k++){
            Proverb newProv = new Proverb(provStringArray[k]);
            arrayOfProverbs[k] = newProv;
        }

        proverbArrayList = new ArrayList<Proverb>(Arrays.asList(arrayOfProverbs));

        //arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, provStringArrayList);
        final ProverbsAdapter proverbsAdapter = new ProverbsAdapter(this, proverbArrayList);

        flingContainer.setAdapter(proverbsAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                proverbArrayList.remove(0);
                proverbsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                Proverb last = new Proverb("No more Kenyan proverbs left to show");
                proverbArrayList.add(i,last);
                proverbsAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_proverbs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
            case R.id.action_settings:
                tweetShare();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void tweetShare(){
//        String message =  "Wee nimnoma"; //txtMessage.getText().toString();
//        Intent tweet = new Intent(Intent.ACTION_VIEW);
//        tweet.setData(Uri.parse("http://twitter.com/?status=" + Uri.encode(message)));
//        startActivity(tweet);
    }
}
