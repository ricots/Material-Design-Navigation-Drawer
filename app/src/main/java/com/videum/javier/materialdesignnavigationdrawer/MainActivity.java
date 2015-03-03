package com.videum.javier.materialdesignnavigationdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;
import com.videum.javier.materialdesignnavigationdrawer.Utils.CircleTransform;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    Button buttonRedLight, buttonRedDark, buttonIndigoLight, buttonIndigoDark;
    ToggleButton toggleButtonDrawer;
    FrameLayout statusBar;
    LinearLayout linearLayoutDrawerAccount, linearLayoutDrawerMain;
    ImageView imageViewDrawerArrowUpDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup toolbar and statusBar (really FrameLayout)
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Navigation Drawer App");
        statusBar = (FrameLayout) findViewById(R.id.statusBar);

        // Setup navigation drawr
        setupNavigationDrawer();

        // Setup buttons to change theme app
        setupButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupTheme() {
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        switch (sharedPreferences.getString("THEME", "REDLIGHT")) {
            case "REDLIGHT":
                setTheme(R.style.AppThemeRedLight);
                break;
            case "REDDARK":
                setTheme(R.style.AppThemeRedDark);
                break;
            case "INDIGOLIGHT":
                setTheme(R.style.AppThemeIndigoLight);
                break;
            case "INDIGODARK":
                setTheme(R.style.AppThemeIndigoDark);
                break;
        }
    }

    public void setupNavigationDrawer() {

        // Setup Navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Setup Drawer Icon
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        String urlPictureMain, urlCoverMain, urlPictureSecond;
        urlPictureMain = "https://lh4.googleusercontent.com/-LEwBpvgLyOM/AAAAAAAAAAI/AAAAAAAAHJM/CbQbbI7w1Bc/s120-c/photo.jpg";
        urlCoverMain = "https://lh6.googleusercontent.com/-fHOV1IEH9D8/Uo6H1_3Yl3I/AAAAAAAASa8/kfbkrAcRjxY/s921-fcrop64=1,21e946bee045d727/20130810_202949.jpg";
        urlPictureSecond = "https://lh3.googleusercontent.com/-zt_CULx5S7A/U123y1WL3qI/AAAAAAAAAUM/vOd7BJOaUB0/s865-no/f51c268a-715a-462d-9035-9ac79c74d0ed";

        ImageView imageViewPictureMain, imageViewCoverMain, imageViewPictureSecond;
        imageViewPictureMain = (ImageView) findViewById(R.id.imageViewPictureMain);
        imageViewCoverMain = (ImageView) findViewById(R.id.imageViewCover);
        imageViewPictureSecond = (ImageView) findViewById(R.id.imageViewPictureSecond);

        Picasso.with(getApplicationContext()).load(urlPictureMain).transform(new CircleTransform()).into(imageViewPictureMain);
        Picasso.with(getApplicationContext()).load(urlCoverMain).into(imageViewCoverMain);
        Picasso.with(getApplicationContext()).load(urlPictureSecond).transform(new CircleTransform()).into(imageViewPictureSecond);

        TypedValue typedValue = new TypedValue();
        MainActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        final int color = typedValue.data;
        drawerLayout.setStatusBarBackgroundColor(color);

        toggleButtonDrawer = (ToggleButton) findViewById(R.id.toggleButtonDrawer);
        toggleButtonDrawer.setOnClickListener(this);
    }

    public void setupButtons() {
        buttonRedLight = (Button) findViewById(R.id.buttonRedLight);
        buttonRedLight.setOnClickListener(this);
        buttonRedDark = (Button) findViewById(R.id.buttonRedDark);
        buttonRedDark.setOnClickListener(this);
        buttonIndigoLight = (Button) findViewById(R.id.buttonIndigoLight);
        buttonIndigoLight.setOnClickListener(this);
        buttonIndigoDark = (Button) findViewById(R.id.buttonIndigoDark);
        buttonIndigoDark.setOnClickListener(this);
    }

    // All onClick for all views
    @Override
    public void onClick(View v) {
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);

        switch (v.getId()) {
            case R.id.buttonRedLight:
                sharedPreferences.edit().putString("THEME", "REDLIGHT").apply();
                startActivity(intent);
                break;
            case R.id.buttonRedDark:
                sharedPreferences.edit().putString("THEME", "REDDARK").apply();
                startActivity(intent);
                break;
            case R.id.buttonIndigoLight:
                sharedPreferences.edit().putString("THEME", "INDIGOLIGHT").apply();
                startActivity(intent);
                break;
            case R.id.buttonIndigoDark:
                sharedPreferences.edit().putString("THEME", "INDIGODARK").apply();
                startActivity(intent);
                break;
            case R.id.toggleButtonDrawer:
                linearLayoutDrawerAccount = (LinearLayout) findViewById(R.id.linearLayoutDrawerAccounts);
                linearLayoutDrawerMain = (LinearLayout) findViewById(R.id.linearLayoutDrawerMain);
                imageViewDrawerArrowUpDown = (ImageView) findViewById(R.id.imageViewDrawerArrowUpDown);
                if (linearLayoutDrawerAccount.getVisibility() == View.VISIBLE) {
                    linearLayoutDrawerAccount.setVisibility(View.GONE);
                    linearLayoutDrawerMain.setVisibility(View.VISIBLE);
                    Animation animation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(500);
                    imageViewDrawerArrowUpDown.startAnimation(animation);
                    imageViewDrawerArrowUpDown.setBackgroundResource(R.drawable.ic_navigation_arrow_drop_up);
                } else {
                    linearLayoutDrawerAccount.setVisibility(View.VISIBLE);
                    linearLayoutDrawerMain.setVisibility(View.GONE);
                    Animation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(500);
                    imageViewDrawerArrowUpDown.startAnimation(animation);
                    imageViewDrawerArrowUpDown.setBackgroundResource(R.drawable.ic_navigation_arrow_drop_down);
                }
                break;
        }
    }
}
