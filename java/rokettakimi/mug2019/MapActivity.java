package rokettakimi.mug2019;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MapActivity extends AppCompatActivity {

    private boolean commanderSelected = false;
    private boolean conqueredGaul = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_activity_main);
    }

    public void commanderSelected(View v){
        commanderSelected = true;
        if(!conqueredGaul) {
            setMovable(R.id.gallicCity1Button);
            setMovable(R.id.legion1);
        }

    }

    public void setMovable(int id){
        ImageView img = findViewById(id);

        switch (id) {
            case R.id.gallicCity1Button:
                img.setImageResource(R.drawable.gallic_movable);
                break;
            case R.id.legion1:
                img.setImageResource(R.drawable.helmet_movable);
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

    public void moveCaesarToGaul(View view){
        if(commanderSelected) {
            View v = findViewById(R.id.caesarModel);

            conquerGaul(1);

            moveTo(1100f, 200f, 1500, 0, v);
            moveTo(640f, 330f, 1500, 1500, v);
            moveTo(640f, 300f, 500, 3000, v);
        }

    }

    public void conquerGaul(int count){
        View v = findViewById(R.id.background);
        while (count < 7){
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
            }
            count+=1;
        }
        conqueredGaul = true;
    }

    public void setImage(View v,final int id, int delay){
        final ImageView backImg = findViewById(v.getId());
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
        ImageView img = findViewById(R.id.gallicCity1Button);
        if(!conqueredGaul) {
            img.setImageResource(R.drawable.gallic);
        }

        img= findViewById(R.id.legion1);
        img.setImageResource(R.drawable.helmet);

    }
}
