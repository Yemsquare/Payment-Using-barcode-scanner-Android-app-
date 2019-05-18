package com.smartshopping.sqlexamples;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import static com.android.volley.toolbox.Volley.newRequestQueue;


public class MyDBHandler{
    public static MyDBHandler minstance;
    private RequestQueue requestQueue;
    private static Context mctx;


    //MyDBHANDLER CONSTRUCTOR


    private MyDBHandler(Context context){
        mctx=context;
        requestQueue=getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized MyDBHandler getInstance(Context contxt){
        if(minstance==null){
            minstance=new MyDBHandler(contxt);
        }
        return minstance;
    }
    public <T>void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}

    /*private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cards.db";
    public static final String TABLE_CARDS = "cards";
    public static final String COLUMN_ID = "cardnod";
    public static final String COLUMN_ID2 = "ammountd";
    public static final String COLUMN_BANKNAME = "bankname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE" + TABLE_CARDS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_ID2 + "INTEGER" +
                COLUMN_BANKNAME + "TEXT" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CARDS);
        onCreate(db);
    }

    public void addcards(cards cards) {
        ContentValues values = new ContentValues();
       // values.put(COLUMN_BANKNAME, cards.getBankname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CARDS, null, values);
        db.close();
    }

    //print data
    public String databasetostring() {
        String dbstring="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+TABLE_CARDS + "WHERE 1";
        //cursor point to a location in your results
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("bankname"))!=null){
                dbstring+=c.getString(c.getColumnIndex("bankname"));
                dbstring="\n";


        }

        }
        db.close();
        return dbstring;
    }
}
*/