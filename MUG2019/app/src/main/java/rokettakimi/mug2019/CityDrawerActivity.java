package rokettakimi.mug2019;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CityDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationContentManager navContentManager;
    private Animation fadeOut;
    private Animation fadeOutNarrator;
    private TextView cityTitle;
    private MediaPlayer ring;
    private boolean isPlayed;


    public final int FADE_OUT_DURATİON = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Hiding action bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_city_drawer);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Navigation View
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //View headerLayout = navigationView.getHeaderView(0);

        //Getting references to navigation items
        View headerLayout = navigationView.getHeaderView(0);
        ImageView navigationImageView = (ImageView) findViewById(R.id.navigationImg);
        TextView pinNameView = (TextView) findViewById(R.id.pinName);
        TextView pinOriginView = (TextView) findViewById(R.id.pinOrigin);
        TextView navigationContent = (TextView) findViewById(R.id.navigationContent);

        //Reference to city title
        cityTitle = (TextView) findViewById(R.id.cityTitle);

        //Constructing navigation content manager
        navContentManager = new NavigationContentManager(navigationImageView, pinNameView, pinOriginView, navigationContent);

        //Fadeout animation
        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(FADE_OUT_DURATİON);
        fadeOut.setFillAfter(true);
        cityTitle.startAnimation(fadeOut);

        fadeOutNarrator = new AlphaAnimation(1.0f, 0.0f);
        fadeOutNarrator.setDuration(2000);
        fadeOutNarrator.setFillAfter(true);


        TextView text = (TextView) findViewById(R.id.narratorText);
        text.setText(getString(R.string.narrator_greeting));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.narrator).startAnimation(fadeOutNarrator);
                findViewById(R.id.narratorText).startAnimation(fadeOutNarrator);

            }
        }, 10000);
        isPlayed = false;
        ring = MediaPlayer.create(getApplicationContext(), R.raw.narrator_greeting);
        if (!ring.isPlaying()) {
            if (!isPlayed) {
                ring.start();
                isPlayed = true;
            }

        }
    }


    @Override
    public void onDestroy() {
        ring.stop();
        super.onDestroy();
    }


    public void onClick(View view) {
        int viewID = view.getId();

        switch (viewID) {
            case R.id.colosseumButton:
                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.colosseum, "Kolezyum", "Roma İmparatorluğu", getString(R.string.colosseum_desc));
                break;
            case R.id.stadiumButton:
                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.stadium, "Domitian Stadyumu", "Roma İmparatorluğu", getString(R.string.stadium_desc));
                break;
            case R.id.pantheonButton:
                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.pantheon, "Pantheon Tapınağı", "Roma İmparatorluğu", getString(R.string.pantheon_desc));
                break;
            case R.id.templeButton:
                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.temple_of_jupiter, "Jüpiter Tapınağı", "Roma İmparatorluğu", getString(R.string.temple_desc));
                break;
            case R.id.barracksButton:
                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.barracks, "Kışla", "Roma İmparatorluğu", getString(R.string.barracks_desc));
                break;
            case R.id.scrollButton:
                System.out.println("scroll clicked");
                Intent intent = new Intent(this, ConversationScene.class);
                ring.stop();
                startActivity(intent);

                break;
            case R.id.mapButton:
                System.out.println("map clicked");
                Intent intent2 = new Intent(this, MapDrawerActivity.class);
                ring.stop();
                startActivity(intent2);

                break;
        }
    }


    //////////////////////////////////////NAVIGATION DRAWER METHODS///////////////////////////////////
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.city_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
