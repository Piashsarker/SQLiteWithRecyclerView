package com.example.pt.sqlitewithrecyclerview.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pt.sqlitewithrecyclerview.model.Instructor;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String LOG = "DatabaseHelper";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UniversityDatabase";
	private static final String TABLE_INSTRUCTOR = "instructor";
	private static final String TABLE_COURSE = "course";
	private static final String TABLE_TEACHES = "teaches";

	private static final String CREATE_TABLE_INSTRUCTOR = "create table if not exists "
			+ TABLE_INSTRUCTOR
			+ " (id integer primary key autoincrement,"
			+ " name varchar(30)," +" email varchar(30), "+ " dept_name varchar(30)," + "salary int )";
	private static final String CREATE_TABLE_COURSE = "create table if not exists"
			+ " "
			+ TABLE_COURSE
			+ "(course_id  varchar(30) primary key,"
			+ "course_name varchar(30)," + "credit integer)";
	private static final String CREATE_TABLE_TEACHES = "create table if not exists"
			+ " " + TABLE_TEACHES + " (id int," + "course_id varchar(30))";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_INSTRUCTOR);
		db.execSQL(CREATE_TABLE_COURSE);
		db.execSQL(CREATE_TABLE_TEACHES);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTRUCTOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHES);

		onCreate(db);

	}

	public void addInstructor(Instructor instructor) {
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", instructor.getName());
		values.put("email", instructor.getEmail()) ;
		values.put("dept_name", instructor.getDept_name());
		values.put("salary", instructor.getSalary());
		db.insert(TABLE_INSTRUCTOR, null, values);
		db.close();
	}

	Instructor getInstructor(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_INSTRUCTOR, new String[] { "id", "name",
				"dept_name", "salary" }, "id" + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		Instructor in = new Instructor(cursor.getString(0),
				cursor.getString(1), cursor.getString(2),
				Integer.parseInt(cursor.getString(3)));
		return in;
	}

	public List<Instructor> getAllContacts() {
		List<Instructor> instructorList = new ArrayList<Instructor>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_INSTRUCTOR;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Instructor instructor = new Instructor();
                instructor.setId(cursor.getString(0));
				instructor.setName(cursor.getString(1));
				instructor.setEmail(cursor.getString(2));
				instructor.setDept_name(cursor.getString(3));
				instructor.setSalary(Integer.parseInt(cursor.getString(4)));

				// Adding contact to list
				instructorList.add(instructor);
			} while (cursor.moveToNext());
		}

		// return contact list
		return instructorList;
	}

	public int updateContact(Instructor instructor) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", instructor.getName());
		values.put("email" ,instructor.getEmail());
		values.put("dept_name", instructor.getDept_name());
		values.put("salary", instructor.getSalary());

		// updating row
		return db.update(TABLE_INSTRUCTOR, values, "id" + " = ?",
				new String[] { String.valueOf(instructor.getName()) });
	}

	// Deleting single contact
	public void deleteContact(Instructor instructor) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_INSTRUCTOR, "id" + " = ?",
				new String[] { String.valueOf(instructor.getName()) });

		db.close();
	}

	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_INSTRUCTOR;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	public void deleteAllInstructor() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from " + TABLE_INSTRUCTOR);
		db.close();
	}

	public void getInstructorBySelected(String name) {
		SQLiteDatabase db = this.getWritableDatabase();

	}

	public List<Instructor> searchContact(String name) {
		List<Instructor> instructorList = new ArrayList<Instructor>();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;

		cursor = db.rawQuery("SELECT * FROM " + TABLE_INSTRUCTOR + " WHERE "
				+"name" + " " + " LIKE  '" + name + "%'" , null);
		if (cursor.moveToFirst()) {
			do {
				Instructor instructor = new Instructor();
				;
				instructor.setName(cursor.getString(1));
				instructor.setEmail(cursor.getString(2));
				instructor.setDept_name(cursor.getString(3));
				instructor.setSalary(Integer.parseInt(cursor.getString(4)));
				// Adding contact to list
				instructorList.add(instructor);
			} while (cursor.moveToNext());
		}
		return instructorList;

	}

}
