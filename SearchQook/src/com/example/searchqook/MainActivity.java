package com.example.searchqook;

import java.util.ArrayList;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.actionbarcompat.ActionBarActivity;
import com.hhdd.messi.Naver;
import com.hhdd.messi.event.*;
import com.hhdd.messi.naver.object.search.*;


public class MainActivity extends ActionBarActivity implements NaverEventListener.OnEncycListener{

	private Naver open_api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       open_api = new Naver();
       open_api.setEncycListener(this);
       open_api.setSearchKey("a868354ee2b5ee74ab78c84616a61480");
       
        Button btn = (Button) findViewById(R.id.bt1);
        btn.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick (View v) {
        		EditText editText = (EditText)findViewById(R.id.et1);
        		open_api.EncycSearch(editText.getText().toString());
        	} });
        
     
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
	public void onFaultResult(int arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResult(ArrayList<EncycObject> arg0) {
		ListView listView = (ListView) findViewById(R.id.lv1);
		ListAdapter b1_adapter = new ListAdapter(this, R.layout.item_row, arg0);
		listView.setAdapter(b1_adapter);
		
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
	
	public class ListAdapter extends ArrayAdapter<EncycObject>{
    	public ArrayList<EncycObject> items;
    	private Context mcontext;
    	private int count = 0;
    	
    	public ListAdapter(Context context, int textViewResourceId, ArrayList<EncycObject> items) {
            super(context, textViewResourceId, items);
            mcontext = context;
    		this.items = items;
    	}
    	
    	@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.item_row, null);
            }
            
            EncycObject Info = items.get(position);
            
            if (Info != null) {
            	ImageView iv1 = (ImageView) v.findViewById(R.id.iv1);
            	TextView tv1 = (TextView) v.findViewById(R.id.tv1);
            	TextView tv2 = (TextView) v.findViewById(R.id.tv2);
            	Info.BindThunbmail(iv1);
            	tv1.setText(Info.getTitle());
            	tv2.setText(Info.getDescription());
            }
            return v;
        }
    	
    }
}
