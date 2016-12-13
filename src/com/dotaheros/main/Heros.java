package com.dotaheros.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.dotaheros.R;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Heros {
	Class<com.example.dotaheros.R.drawable> cls = R.drawable.class;

	public ArrayList<HashMap<String, Object>> getBardata(Context c)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, NoSuchFieldException {
		ArrayList<HashMap<String, Object>> bar = new ArrayList<HashMap<String, Object>>();
		SQLiteDatabase db = c.openOrCreateDatabase("dotaheros.db",
				Context.MODE_PRIVATE, null);
		Cursor cursor = db.rawQuery("select name,ename from tavern ", null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			String imgname = cursor.getString(1);
			int id = cls.getDeclaredField(imgname).getInt(null);
			hm.put("img", id);
			hm.put("info", cursor.getString(0));
			bar.add(hm);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return bar;
	}

	public ArrayList<HashMap<String, Object>> getHerosdata(Context c,
			String barname) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, NoSuchFieldException {
		ArrayList<HashMap<String, Object>> heros = new ArrayList<HashMap<String, Object>>();
		SQLiteDatabase db = c.openOrCreateDatabase("dotaheros.db",
				Context.MODE_PRIVATE, null);
		Cursor cursor = db
				.rawQuery(
						"select h.name,h.sx,h.info from heros h  inner join tavern t on h.[tid]=t.[tid] where t.[name]=?",
						new String[] { barname });
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			String imgname = "hero_" + cursor.getString(1).toLowerCase();
			int id = cls.getDeclaredField(imgname).getInt(null);
			hm.put("img", id);
			hm.put("info", cursor.getString(0));
			hm.put("heroinfo", cursor.getString(2));
			heros.add(hm);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return heros;
	}

	public ArrayList<HashMap<String, Object>> getEquip(Context c,
			String heroname) throws IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchFieldException {
		ArrayList<HashMap<String, Object>> equipdata = new ArrayList<HashMap<String, Object>>();
		SQLiteDatabase db = c.openOrCreateDatabase("dotaheros.db",
				Context.MODE_PRIVATE, null);
		Cursor cursor = db.rawQuery(
				"select e.name,e.eename from equip e inner join relation r on e.eid=r.eid"
						+ " inner join heros h on h.hid=r.hid where h.name=?",
				new String[] { heroname });
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			String imgname = "equip_" + cursor.getString(1).toLowerCase();
			int id = cls.getDeclaredField(imgname).getInt(null);
			hm.put("img", id);
			hm.put("info", cursor.getString(0));
			equipdata.add(hm);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return equipdata;
	}
}
