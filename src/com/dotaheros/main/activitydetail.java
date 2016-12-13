package com.dotaheros.main;

import com.example.dotaheros.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class activitydetail extends Activity {
	private GridView adgridview;
	private Heros hb = new Heros();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitydetail);
		TextView textview = (TextView) findViewById(R.id.adtextView);
		Intent intent = this.getIntent();
		final String barname;
		try {
			barname = hb
					.getBardata(this)
					.get(Integer.valueOf(intent.getExtras().get("barname")
							.toString())).get("info").toString();
			textview.setText(barname);
			adgridview = (GridView) findViewById(R.id.adgridView);
			SimpleAdapter adapter;
			adapter = new SimpleAdapter(this, hb.getHerosdata(this, barname),
					R.layout.aditem, new String[] { "img", "info" }, new int[] {
							R.id.adItemImage, R.id.adItemText });
			adgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
			adgridview.setAdapter(adapter);
			adgridview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent intent = new Intent();
					intent.putExtra("heronum", arg2 + "");
					intent.putExtra("barname", barname);
					intent.setClass(activitydetail.this, activityinfo.class);
					activitydetail.this.startActivity(intent);

				}
			});
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
