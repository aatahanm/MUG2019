package rokettakimi.mug2019;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

public class MapDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private boolean commanderSelected = false;
    private boolean conqueredGaul = false;
    private Animation fadeOut, fadeOutText, fadeIn;
    private boolean getTroops = false;
    private MediaPlayer ring;

    //Navigation related instances
    private DrawerLayout drawer;
    private NavigationContentManager navContentManager;
    public final int FADE_OUT_DURATİON = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Hiding action bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map_drawer);

        drawer = (DrawerLayout) findViewById(R.id.map_drawer_layout);

        //Navigation View
        NavigationView navigationView = (NavigationView) findViewById(R.id.map_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //View headerLayout = navigationView.getHeaderView(0);

        //Getting references to navigation items
        View headerLayout = navigationView.getHeaderView(0);
        ImageView navigationImageView = (ImageView) findViewById(R.id.mapNavigationImg);
        TextView pinNameView = (TextView) findViewById(R.id.mapPinName);
        TextView pinOriginView = (TextView) findViewById(R.id.mapPinOrigin);
        TextView navigationContent = (TextView) findViewById(R.id.mapNavigationContent);

        //Constructing navigation content manager
        navContentManager = new NavigationContentManager(navigationImageView, pinNameView, pinOriginView, navigationContent);
        ////////////////////////////////////////////////////////////////////////////////////////
        //Fadeout animation
        TextView eventTitle = (TextView) findViewById(R.id.title);

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);

        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(9000);
        fadeOut.setFillAfter(true);

        fadeOutText = new AlphaAnimation(1.0f, 0.0f);
        fadeOutText.setDuration(2000);
        fadeOutText.setFillAfter(true);

        TextView text = (TextView) findViewById(R.id.narratorText);
        text.setText(getString(R.string.narrator_map));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.narrator).startAnimation(fadeOutText);
                findViewById(R.id.narratorText).startAnimation(fadeOutText);
            }
        }, 10000);


        eventTitle.startAnimation(fadeOut);

        ring = MediaPlayer.create(getApplicationContext(), R.raw.narrator_map1);
        if (!ring.isPlaying())
            ring.start();
    }

    @Override
    public void onDestroy() {
        ring.stop();
        super.onDestroy();
    }

    public void showCityInfo(View v) {
        System.out.println("clicked");
        System.out.println(v.getId());
        switch (v.getId()) {
            case R.id.pomepeiiCityButton:

                drawer.openDrawer(GravityCompat.START);
                System.out.println("drawer acildi");
                navContentManager.setNavigationDrawerContent(R.drawable.pompeii, "Pompei", "Roma İmparatorluğu", getString(R.string.pompeii_info));
                break;
            case R.id.romeCityButton:

                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.barracks, "Roma", "Roma İmparatorluğu", getString(R.string.rome_info));
                break;
            case R.id.gallicCity1Button:

                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.alestia, "Alestia", "Galya", getString(R.string.alestia_info));
                break;
            case R.id.gallicCity2Button:

                drawer.openDrawer(GravityCompat.START);
                navContentManager.setNavigationDrawerContent(R.drawable.arverni, "Arverni", "Galya", getString(R.string.arverni_info));
                break;
        }
    }

    public void commanderSelected(View v) {
        commanderSelected = true;
        if (!conqueredGaul) {
            setMovable(R.id.gallicCity1Button);
            setMovable(R.id.legion1);
        } else {
            setMovable(R.id.gallicCity1Button);
            setMovable(R.id.romeCityButton);
        }

    }

    public void setMovable(int id) {
        ImageView img = (ImageView) findViewById(id);

        switch (id) {
            case R.id.gallicCity1Button:
                if (!conqueredGaul)
                    img.setImageResource(R.drawable.gallic_movable);
                else
                    img.setImageResource(R.drawable.city_movable);
                break;
            case R.id.legion1:
                img.setImageResource(R.drawable.helmet_movable);
                break;
            case R.id.romeCityButton:
                img.setImageResource(R.drawable.city_movable);
                break;
        }
    }

    public void unselectCommander(View v) {
        if (commanderSelected) {
            commanderSelected = false;
            returnBack(v);
        }
    }

    public void gatherSoldiers(View view) {
        getTroops = true;
        int duration = 1000;
        if (commanderSelected) {
            View v = findViewById(R.id.caesarModel);
            final View legion = findViewById(R.id.legion1);

            moveTo((legion.getX() - 40), legion.getY(), duration, 0, v);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    legion.setVisibility(View.INVISIBLE);
                }
            }, duration);
        }
    }

    public void movetoRoma(View view) {
        if (commanderSelected && conqueredGaul) {
            View v = findViewById(R.id.caesarModel);

            moveTo(640f, 330f, 500, 0, v);
            moveTo(1100f, 200f, 1500, 500, v);
            moveTo(1280f, 415f, 1500, 2000, v);

        } else {
            showCityInfo(view);
        }
    }


    public void moveCaesarToGaul(View view) {
        if (commanderSelected) {

            View v = findViewById(R.id.caesarModel);
            conquerGaul(1);

            moveTo(1100f, 200f, 1500, 0, v);
            moveTo(640f, 330f, 1500, 1500, v);
            moveTo(640f, 300f, 500, 3000, v);
        } else {
            showCityInfo(view);
        }

    }

    public void conquerGaul(int count) {
        if (getTroops) {
            View v = findViewById(R.id.background);
            while (count < 7 && !conqueredGaul) {
                if (count == 1) {
                    setImage(v, R.drawable.gaul_siege1, 3500 + count * 1500);
                } else if (count == 2) {
                    setImage(v, R.drawable.gaul_siege2, 3500 + count * 1500);
                } else if (count == 3) {
                    setImage(findViewById(R.id.gallicCity1Button), R.drawable.rome_city2, 3500 + count * 1500);

                } else if (count == 4) {
                    setImage(v, R.drawable.gaul_siege3, 3500 + count * 1500);
                } else if (count == 5) {
                    setImage(findViewById(R.id.gallicCity2Button), R.drawable.rome_city3, 3500 + count * 1500);
                } else if (count == 6) {
                    setImage(v, R.drawable.gaul_siege4, 3500 + count * 1500);
                }
                count += 1;
            }
            conqueredGaul = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TextView text = (TextView) findViewById(R.id.narratorText);
                    text.setText(getString(R.string.campaign_finished));
                    findViewById(R.id.narrator).startAnimation(fadeIn);
                    findViewById(R.id.narratorText).startAnimation(fadeIn);

                    ring = MediaPlayer.create(getApplicationContext(), R.raw.campaing_finished);
                    ring.start();
                }
            }, 13000);

            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.narrator).startAnimation(fadeOutText);
                    findViewById(R.id.narratorText).startAnimation(fadeOutText);
                }
            }, 24000);


        }
    }

    public void setImage(View v, final int id, int delay) {
        final ImageView backImg = (ImageView) findViewById(v.getId());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                backImg.setImageResource(id);
            }
        }, delay);
    }

    public void moveTo(float x, float y, int duration, int delay, View v) {

        ObjectAnimator animX = ObjectAnimator.ofFloat(v, "x", x);
        ObjectAnimator animY = ObjectAnimator.ofFloat(v, "y", y);
        final AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.setDuration(duration);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animSetXY.start();
            }
        }, delay);

    }


    public void returnBack(View v) {
        ImageView img = (ImageView) findViewById(R.id.gallicCity1Button);
        if (!conqueredGaul) {
            img.setImageResource(R.drawable.gallic);
        } else {
            img.setImageResource(R.drawable.rome_city2);
        }


        img = (ImageView) findViewById(R.id.legion1);
        img.setImageResource(R.drawable.helmet);

        img = (ImageView) findViewById(R.id.romeCityButton);
        img.setImageResource(R.drawable.rome2);

    }

    //////////////////////////////////////NAVIGATION DRAWER METHODS///////////////////////////////////
    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
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
