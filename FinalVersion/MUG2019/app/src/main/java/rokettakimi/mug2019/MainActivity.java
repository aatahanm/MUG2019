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
import android.widget.TextView;

import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends AppCompatActivity {

    private GifTextView gifImageView;
    private int first = 0;
    private int[] images = new int[3];
    private ImageView view;
    private TextView countryView;
    private Button startButton;

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
        final Button b1 = (Button) findViewById(R.id.startButton);

        gifImageView = (GifTextView) findViewById(R.id.imageViewGIF);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button startButt = (Button) findViewById(R.id.startButton);
                startButt.setEnabled(false);

                //Hide left right buttons
                if (first != 1) {
                    return;
                }
                ImageView leftButt = (ImageView) findViewById(R.id.leftArrow);
                ImageView rightButt = (ImageView) findViewById(R.id.rightArrow);
                leftButt.setVisibility(View.INVISIBLE);
                rightButt.setVisibility(View.INVISIBLE);

                //Play gif
                playGif();

                final Handler handler = new Handler();

                imageView.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);


            }
        });

        images[0] = R.drawable.egypt_main_menu;
        images[1] = R.drawable.rome_main_menu;
        images[2] = R.drawable.aztec_main_menu;
        view = (ImageView) findViewById(R.id.menuBackground);
        countryView = (TextView) findViewById(R.id.country);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setEnabled(false);
    }

    public void playGif() {
        Animation fadeout = new AlphaAnimation(1.f, 1.f);
        fadeout.setDuration(5500); // You can modify the duration here
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

                System.out.println("Intent olusturuluyor");
                Intent intent2 = new Intent(getApplicationContext(), CityDrawerActivity.class);
                startActivity(intent2);

            }
        });
        gifImageView.startAnimation(fadeout);
    }

    //Mısır roma aztec
    public void onClickRight(View v) {
        first = (first + 1) % 3;
        setCountryName(first);
        view.setImageResource(images[first]);
    }

    public void onClickLeft(View v) {

        first = (first - 1) % 3;
        if (first < 0) {
            first = first + 3;
        }
        setCountryName(first);
        view.setImageResource(images[first]);

    }

    public void setCountryName(int select) {
        String countryName = "Mısır Uygarlığı";
        switch (select) {
            case 0:
                countryName = "Mısır Uygarlığı";
                startButton.setEnabled(false);
                break;
            case 1:
                countryName = "Roma İmparatorluğu";
                startButton.setEnabled(true);
                break;
            case 2:
                countryName = "Aztec Uygarlığı";
                startButton.setEnabled(false);
                break;
        }
        countryView.setText(countryName);
    }
}

