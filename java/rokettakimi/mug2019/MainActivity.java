package rokettakimi.mug2019;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends AppCompatActivity {

    private GifTextView gifImageView;
    private int first = 0;
    private int[] images = new int[3];
    private ImageView view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);


        final ImageView imageView = (ImageView) findViewById(R.id.menuBackground);
        final Button b1=(Button)findViewById(R.id.startButton);

        gifImageView = (GifTextView) findViewById(R.id.imageViewGIF);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGif();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setVisibility(View.INVISIBLE);
                        b1.setVisibility(View.INVISIBLE);
                    }
                }, 10);

                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //change to first scene
                        Intent intent2 = new Intent(getApplicationContext(), CityDrawerActivity.class);
                        startActivity(intent2);
                    }
                }, 7500);
            }
        });

        images[0] = R.drawable.egypt_main_menu;
        images[1] = R.drawable.rome_main_menu;
        images[2] = R.drawable.aztec_main_menu;
        view = (ImageView) findViewById(R.id.menuBackground);
    }

    public void playGif(){
        Animation fadeout = new AlphaAnimation(1.f, 1.f);
        fadeout.setDuration(2500); // You can modify the duration here
        fadeout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                gifImageView.setBackgroundResource(R.drawable.earth_zooming);//your gif file
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }
        });
        gifImageView.startAnimation(fadeout);
    }

    public void onClickRight(View v){
        first += 1;
        int select = first % 3;
        view.setImageResource(images[select]);

    }


    public void onClickLeft(View v){
        first -= 1;
        int select = first % 3;
        view.setImageResource(images[select]);
    }
}

