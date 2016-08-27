package owuor91.com.kenyanproverbs.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import owuor91.com.kenyanproverbs.adapters.ProverbsAdapter;
import owuor91.com.kenyanproverbs.R;
import owuor91.com.kenyanproverbs.models.Proverb;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Proverbs extends AppCompatActivity {

    public static ArrayList<String> provStringArrayList;
    public ArrayList<Proverb> proverbArrayList;
    public static ArrayAdapter arrayAdapter;
    static String message;
    String[] provStringArray;
    FloatingActionButton fabAddKp;
    EditText etAddKp;
    TextView tvCancel;
    Button btnAddKp;
    public String newProv="";
    public static Dialog dialog;

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

        fabAddKp = (FloatingActionButton)findViewById(R.id.fabAddKp);
        fabAddKp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void swipeCards(){
        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        provStringArray = getResources().getStringArray(R.array.proverbsArray);

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
                proverbArrayList.add(i, last);
                proverbsAdapter.notifyDataSetChanged();
                i++;
            }

            @Override
            public void onScroll(float v) {
                View view = (View) findViewById(flingContainer.getId());
                TextView txt = (TextView) view.findViewById(R.id.helloText);
                message = txt.getText().toString();
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

    private void showDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add your proverb");
        View view = inflater.inflate(R.layout.addproverb, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        etAddKp = (EditText) dialog.findViewById(R.id.etAddKp);
        btnAddKp = (Button) dialog.findViewById(R.id.btnAddKp);
        btnAddKp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProv = etAddKp.getText().toString();
                boolean error = false;
                if (newProv.equals("")){
                    error = true;
                    etAddKp.setError("Field can't be empty");
                }
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private String currentProverb(){
        int index=0;
        for (int p=0; p <provStringArray.length; p++){
            if (provStringArray[p]==message){
                index = p+1;
            }
        }
        String currentProv = provStringArray[index];
        return currentProv;
    }

    private void tweetShare(){
        String tweet = "\""+ currentProverb()+"\" - Kenyan Proverb";
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, tweet);
        tweetIntent.setType("text/plain");

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packageManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;

        for (ResolveInfo resolveInfo : resolvedInfoList ){
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")){
                tweetIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }
        if (resolved){
            startActivity(tweetIntent);
        }
        else {
            Toast.makeText(this, "Sorry, Twitter app not found", Toast.LENGTH_SHORT).show();
        }

    }

}
