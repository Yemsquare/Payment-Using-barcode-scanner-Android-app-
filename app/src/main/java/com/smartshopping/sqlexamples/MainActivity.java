package com.smartshopping.sqlexamples;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    ViewPager viewPager;
    ImageView btn;
    /*Button btn1,btn2;n
    TextView txtv;
    EditText txt1,txt2,txt3;
    MyDBHandler dbHandler;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (ImageView) findViewById(R.id.topupp);
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //creating an object for the class ViewPagerAdapter

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        //setting the object to the adapter
        viewPager.setAdapter(viewPagerAdapter);
        //noinspection deprecation

        btn.setOnClickListener((View.OnClickListener)this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.addcards) {
            Intent i = new Intent(this, AddCards.class);
            startActivity(i);
        }
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
