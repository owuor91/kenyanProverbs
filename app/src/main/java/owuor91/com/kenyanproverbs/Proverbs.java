package owuor91.com.kenyanproverbs;

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

public class Proverbs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proverbs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        String[] provArray = getResources().getStringArray(R.array.proverbsArray);
        List<String> provArrayList = Arrays.asList(provArray);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, provArrayList);

        flingContainer.setAdapter(arrayAdapter);
        
    }

}
