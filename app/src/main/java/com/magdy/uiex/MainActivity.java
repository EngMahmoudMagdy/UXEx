package com.magdy.uiex;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.magdy.uiex.animation.AnimationFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewAnimator viewAnimator ;
    ImageButton searchFlag  ;
    LinearLayout shadbar ;
    List <ImageView> triangle;
     List <ImageView> clickView  ;

    LinearLayout leftdownclickview ,rightdownclickview ;
    // linear layout for clicks

    TextView leftDownText , leftDownNumber , rightDownText, rightDownNumber ;
    // texts and numbers for every textview in the layout

    ImageView circle;
    ViewFlipper viewFlipper ;
    int triIndex = 0 ;
    int [] triViewsId   = {
            R.id.triup,
            R.id.tridown,
            R.id.trileftup,
            R.id.trileftdown,
            R.id.trirightup,
            R.id.trirightdown

    };

    int [] clickViewsId   = {
            R.id.triupclick,
            R.id.tridownclick,
            R.id.trileftupcilck,
            R.id.trirightupclick,
            R.id.trileftupcilck2,
            R.id.trileftdownclick2,
            R.id.trirightupclick2,
            R.id.trirightdownclick2,


    };
    int [] shadebarDrawable   = {
            R.drawable.glowbarorange,
            R.drawable.glowbardarkblue,
            R.drawable.glowbargreen,
            R.drawable.glowbarskyblue,
            R.drawable.glowbarred,
            R.drawable.glowbarblue
    };
    int [] trishadedDrawable   = {
            R.drawable.trangleupshaded,
            R.drawable.trangledownshaded,
            R.drawable.trangleleftupshaded,
            R.drawable.trangleleftdownshaded,
            R.drawable.tranglerightupshaded,
            R.drawable.tranglerightdownshaded

    };
    int [] triDrawable   = {
            R.drawable.trangleup,
            R.drawable.trangledown,
            R.drawable.trangleleftup,
            R.drawable.trangleleftdown,
            R.drawable.tranglerightup,
            R.drawable.tranglerightdown
    };
    int [] iconDrawable   = {
            R.drawable.iconabout,
            R.drawable.iconmessage,
            R.drawable.iconplane,
            R.drawable.iconsearch,
            R.drawable.iconsound,
            R.drawable.icontools,

    };
    int [] circleDrawable   = {
            R.drawable.circleup,
            R.drawable.circledown,
            R.drawable.circlerleftup,
            R.drawable.circlerleftdown,
            R.drawable.circlerightup,
            R.drawable.circlerightdown,
            R.drawable.defaultcircle,

    };

    boolean flaget = false;


    Animation zoomAnimation;
    ImageView inv ;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //here is to choose the layout for small screens
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        if (width > 480)
      setContentView(R.layout.activity_main); // for new and ordinary mobiles above 480p in width
        else
            setContentView(R.layout.activity_mainv2);//for small mobiles





        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // for toolbar





        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();

        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.kid);
        RoundedImage roundedImage  = new RoundedImage(bm);
        ImageView imageViewheader =(ImageView) headerLayout.findViewById(R.id.imageheader);
        imageViewheader.setImageDrawable(roundedImage);

        // We can now look up items within the header if needed


        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        // Setup drawer view
        setupDrawerContent(nvDrawer);


        searchFlag = (ImageButton) findViewById(R.id.flag);
        shadbar = (LinearLayout) findViewById(R.id.shadbar) ;
        clickView = new ArrayList<ImageView>();
        circle = (ImageView)findViewById(R.id.circleid);
        circle.setImageResource(circleDrawable[6]);
        triangle = new ArrayList< ImageView>();
        for (int i =0; i<triViewsId.length-2; i ++) {

            ImageView temp =(ImageView) findViewById(clickViewsId[i]);
            temp.setOnClickListener(this);
            temp.setImageDrawable(getResources().getDrawable(iconDrawable[i]));
            clickView.add(temp) ;
            ImageView temp2 =(ImageView) findViewById(triViewsId[i]);
            triangle.add(temp2);
        }

        ImageView temp2 =(ImageView) findViewById(triViewsId[4]);
        triangle.add(temp2);

        temp2 =(ImageView) findViewById(triViewsId[5]);
        triangle.add(temp2);

        for (int i =4; i<8; i ++) {
            ImageView temp = (ImageView) findViewById(clickViewsId[i]);
            temp.setOnClickListener(this);
            clickView.add(temp);
        }

        leftdownclickview =(LinearLayout) findViewById(R.id.trileftdownclick); // for texts
        rightdownclickview =(LinearLayout) findViewById(R.id.trirightdownclick); // left and right


        //the text initialization
        //left texts
        leftDownText = (TextView) findViewById(R.id.textleftdown);
        leftDownNumber = (TextView) findViewById(R.id.textnumberleftdown); // numbers

        //right texts
        rightDownText = (TextView) findViewById(R.id.textrightdown);
        rightDownNumber = (TextView) findViewById(R.id.textnumberrightdown); //numbers


        zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        inv = (ImageView)findViewById(R.id.tridownclick1);
        inv.setOnClickListener(this);


        searchFlag.setOnClickListener(this);
        viewAnimator = (ViewAnimator)this.findViewById(R.id.viewFlipper);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);






    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
     //   drawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
     /*   // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();*/

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        //setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i =0; i<triViewsId.length; i ++) {
            triangle.get(i).setBackgroundResource(triDrawable[i]);
            circle.setImageResource(circleDrawable[6]);
        }

        flaget=false ;

    }

    private void triViewClicker(View v) throws InterruptedException {
        if (v == clickView.get(0))
        {
            shadbar.setBackgroundResource(shadebarDrawable[0]);
            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
            triangle.get(0).setBackgroundResource(trishadedDrawable[0]);
            triIndex = 0 ;
            circle.setImageResource(circleDrawable[0]);
            clickView.get(0).startAnimation(zoomAnimation);

            /*Intent i = new Intent(this,DetailsActivity.class);
            i.putExtra("icon",iconDrawable[0]);
            startActivity(i);
*/

        }
        else if (v==clickView.get(1)||v==inv||v==viewFlipper)

        {
            shadbar.setBackgroundResource(shadebarDrawable[1]);
            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
            triangle.get(1).setBackgroundResource(trishadedDrawable[1]);
            triIndex = 1 ;
            circle.setImageResource(circleDrawable[1]);
//            AnimationFactory.fadeInAnimation(100,100);
            AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);

        }
        else if (v == clickView.get(2) || v==clickView.get(4))
        {
            shadbar.setBackgroundResource(shadebarDrawable[2]);
            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
            triangle.get(2).setBackgroundResource(trishadedDrawable[2]);
            triIndex = 2 ;
            circle.setImageResource(circleDrawable[2]);
            Intent i = new Intent(this,DetailsActivity.class);
            i.putExtra("icon",iconDrawable[2]);
            startActivity(i);

//            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
        }

        else if (v == leftdownclickview|| v==clickView.get(5))
        {
            shadbar.setBackgroundResource(shadebarDrawable[3]);
            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
            triangle.get(3).setBackgroundResource(trishadedDrawable[3]);
            triIndex = 3;
            circle.setImageResource(circleDrawable[3]);
            /*Intent i = new Intent(this,DetailsActivity.class);
            i.putExtra("icon",iconDrawable[3]);
            startActivity(i);*/

//            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
        }
        else
        if (v == clickView.get(3)|| v==clickView.get(6))
        {
            shadbar.setBackgroundResource(shadebarDrawable[4]);
            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
            triangle.get(4).setBackgroundResource(trishadedDrawable[4]);
            triIndex = 4 ;
            circle.setImageResource(circleDrawable[4]);
            Intent i = new Intent(this,DetailsActivity.class);
            i.putExtra("icon",iconDrawable[4]);
            startActivity(i);

//            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
        }
        else if (v ==rightdownclickview || v==clickView.get(7))
        {
            shadbar.setBackgroundResource(shadebarDrawable[5]);
            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
            triangle.get(5).setBackgroundResource(trishadedDrawable[5]);
            triIndex = 5 ;
            circle.setImageResource(circleDrawable[5]);
          /*  Intent i = new Intent(this,DetailsActivity.class);
            i.putExtra("icon",iconDrawable[5]);
            startActivity(i);*/

//            triangle.get(triIndex).setBackgroundResource(triDrawable[triIndex]);
        }

    }


    @Override
    public void onClick(View view) {

        try {
            triViewClicker(view);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(view == searchFlag)
        {
            mDrawer.openDrawer(GravityCompat.START);

            shadbar.setBackgroundResource(R.drawable.glowbarblue);

        }
    }
}

