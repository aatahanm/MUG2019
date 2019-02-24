package rokettakimi.mug2019;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {

    private boolean commanderSelected = false;
    private boolean conqueredGaul = false;
    private Animation fadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_activity);

        TextView eventTitle = (TextView) findViewById(R.id.title);

        fadeOut = new AlphaAnimation(1.0f , 0.0f);
        fadeOut.setDuration(9000);
        fadeOut.setFillAfter(true);

        eventTitle.startAnimation(fadeOut);

    }

    public void commanderSelected(View v){
        commanderSelected = true;
        if(!conqueredGaul) {
            setMovable(R.id.gallicCity1Button);
            setMovable(R.id.legion1);
        }else {
            setMovable(R.id.gallicCity1Button);
            setMovable(R.id.romeCityButton);
        }

    }

    public void setMovable(int id){
        ImageView img = (ImageView) findViewById(id);

        switch (id) {
            case R.id.gallicCity1Button:
                if(!conqueredGaul)
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

    public void unselectCommander(View v){
        if(commanderSelected){
            commanderSelected = false;
            returnBack(v);
        }
    }

    public void gatherSoldiers(View view){
        int duration = 1000;
        if(commanderSelected){
            View v = findViewById(R.id.caesarModel);
            final View legion = findViewById(R.id.legion1);

            moveTo((legion.getX()-40),legion.getY(),duration,0,v);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    legion.setVisibility(View.INVISIBLE);
                }
            }, duration);
        }
    }

    public void movetoRoma(View view){
        if(commanderSelected && conqueredGaul){
            View v = findViewById(R.id.caesarModel);

            moveTo(640f, 330f, 500, 0, v);
            moveTo(1100f, 200f, 1500, 500, v);
            moveTo(1400f,455f,1500,2000,v);

        }else
        {
            showCityInfo(view);
        }
    }

    public void showCityInfo(View v){
        Toast.makeText(getApplicationContext(),"city info",Toast.LENGTH_LONG).show();
        //Show city info
    }

    public void moveCaesarToGaul(View view){
        if(commanderSelected) {

            View v = findViewById(R.id.caesarModel);
            conquerGaul(1);

            moveTo(1100f, 200f, 1500, 0, v);
            moveTo(640f, 360f, 1500, 1500, v);
            moveTo(690f, 360f, 500, 3000, v);
        }else{
            showCityInfo(view);
        }

    }

    public void conquerGaul(int count){
        View v = findViewById(R.id.background);
        while (count < 8 && !conqueredGaul){
            if(count == 1){
                setImage(v,R.drawable.gaul_siege1,3500+count*1500);
            }else if (count == 2){
                setImage(v,R.drawable.gaul_siege2,3500+count*1500);
            }else if (count == 3){
                setImage(findViewById(R.id.gallicCity1Button),R.drawable.rome_city2,3500+count*1500);

            }else if (count == 4){
                setImage(v,R.drawable.gaul_siege3,3500+count*1500);
            }else if (count == 5){
                setImage(findViewById(R.id.gallicCity2Button),R.drawable.rome_city3,3500+count*1500);
            }else if (count == 6){
                setImage(v,R.drawable.gaul_siege4,3500+count*1500);
            }else if (count == 7){
                setImage(findViewById(R.id.romeCityButton),R.drawable.city_movable,3500+count*1500);
            }
            count+=1;
        }
        conqueredGaul = true;
    }

    public void setImage(View v,final int id, int delay){
        final ImageView backImg = (ImageView) findViewById(v.getId());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                backImg.setImageResource(id);
            }
        }, delay);
    }

    public void moveTo(float x,float y,int duration,int delay,View v){

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


    public void returnBack(View v){
        ImageView img =  (ImageView)findViewById(R.id.gallicCity1Button);
        if(!conqueredGaul) {
            img.setImageResource(R.drawable.gallic);
        }else{
            img.setImageResource(R.drawable.rome_city2);
        }


        img= (ImageView) findViewById(R.id.legion1);
        img.setImageResource(R.drawable.helmet);

        img = (ImageView) findViewById(R.id.romeCityButton);
        img.setImageResource(R.drawable.rome2);

    }
}
