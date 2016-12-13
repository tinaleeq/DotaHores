package com.dotaheros.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.dotaheros.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private GridView gridview;
	private Heros hb = new Heros();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			File dir = null;
			File dirDatabase = null;
			Context cont = getApplicationContext();
			File file = cont.getFilesDir();
			File parentFile = file.getParentFile();
			if (parentFile.exists()) {
				System.out.println("2");
				String path = parentFile.getPath() + "/databases";
				dir = new File(path);
				if (!dir.exists())
					System.out.println("3");
				try {
					dir.mkdir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String path2 = parentFile.getPath() + "/databases/dotaheros.db";
				dirDatabase = new File(path2);
				if (!dirDatabase.exists())
					System.out.println("3");
				try {
					dirDatabase.createNewFile();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			InputStream is = getResources().openRawResource(R.raw.dotaheros);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(dirDatabase);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] buffer = new byte[8192];
			int count = 0;
			// 开始复制dotaheros.db文件
			try {
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			gridview = (GridView) findViewById(R.id.gridView);
		
			SimpleAdapter adapter;
			adapter = new SimpleAdapter(this, hb.getBardata(this),
					R.layout.item, new String[] { "img", "info" }, new int[] {
							R.id.ItemImage, R.id.ItemText });
			gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gridview.setAdapter(adapter);
			gridview.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent intent = new Intent();
					intent.putExtra("barname", arg2);
					intent.setClass(MainActivity.this, activitydetail.class);
					MainActivity.this.startActivity(intent);

				}
			});
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
