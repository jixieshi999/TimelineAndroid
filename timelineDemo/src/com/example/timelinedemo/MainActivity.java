package com.example.timelinedemo;

import java.util.ArrayList;
import java.util.List;

import com.android.timeline.TimeLineView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listview=(ListView)findViewById(android.R.id.list);
		List<String> list = new ArrayList<String>();
		list.add("test");
		list.add("name");
		list.add("asdasd");
		list.add("dfg");
		list.add("asdasfdhnd");
		list.add("dghn");
		listview.setAdapter(new ArrayAdapter<String>(this,R.layout.item_time,R.id.item_txt,list){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view= super.getView(position, convertView, parent);
				TimeLineView timelineitem=(TimeLineView)view;
				if(0==position){
					timelineitem.setPositionType(TimeLineView.POSITIONTYPEFIRST);
				}else if(getCount()-1==position){
					timelineitem.setPositionType(TimeLineView.POSITIONTYPELAST);
				}else{
					timelineitem.setPositionType(TimeLineView.POSITIONTYPEMIDDLE);
				}
				timelineitem.setRadioBackgroundColor(Color.parseColor("#444444"));
				if(position%2==0){
					timelineitem.setBackgroundResource(R.drawable.button_info_selector);
					timelineitem.setZoneType(TimeLineView.ZONETYPEITEM);
				}else{
					timelineitem.setBackgroundResource(R.drawable.button_inverse_selector);
					timelineitem.setZoneType(TimeLineView.ZONETYPELINE);
				}
				return view;
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
