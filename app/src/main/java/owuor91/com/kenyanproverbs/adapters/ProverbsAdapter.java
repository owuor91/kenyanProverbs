package owuor91.com.kenyanproverbs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import owuor91.com.kenyanproverbs.R;
import owuor91.com.kenyanproverbs.models.Proverb;

/**
 * Created by johno_000 on 3/24/2016.
 */
public class ProverbsAdapter extends ArrayAdapter<Proverb> {
    public static TextView txtProverb;

    public ProverbsAdapter(Context context, ArrayList<Proverb> proverbs){
        super(context,0,proverbs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Proverb proverb = getItem(position);

        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        txtProverb = (TextView)convertView.findViewById(R.id.helloText);
        txtProverb.setText(proverb.text);
        return convertView;
    }
}
