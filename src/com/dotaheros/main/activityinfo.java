package com.dotaheros.main;

import com.example.dotaheros.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class activityinfo extends Activity {
	private Heros hb = new Heros();
	private GridView equipview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityinfo);
		Intent intent = this.getIntent();
		int heronum = Integer.valueOf(intent.getExtras().getString("heronum")
				.toString());
		String barname = intent.getExtras().getString("barname");
		try {
			String heroname = hb.getHerosdata(this, barname).get(heronum)
					.get("info").toString();
			String heroinfo = hb.getHerosdata(this, barname).get(heronum)
					.get("heroinfo").toString();
			ImageView iv = (ImageView) findViewById(R.id.infoimage);
			TextView tv = (TextView) findViewById(R.id.infotext);
			TextView tvinfo = (TextView) findViewById(R.id.info);
			iv.setImageResource((Integer) hb.getHerosdata(this, barname)
					.get(heronum).get("img"));
			tv.setText(heroname);
			tvinfo.setText(heroinfo);
			equipview = (GridView) findViewById(R.id.equipView);
			SimpleAdapter adapter;
			adapter = new SimpleAdapter(this, hb.getEquip(this, heroname),
					R.layout.equipitem, new String[] { "img", "info" },
					new int[] { R.id.equipImage, R.id.equipText });
			equipview.setAdapter(adapter);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}