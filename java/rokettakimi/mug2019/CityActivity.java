package rokettakimi.mug2019;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Hiding action bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_city);

        Intent intent = new Intent(this, CityDrawerActivity.class);
        startActivity(intent);





    }

    public void onClick(View view){
        int viewID = view.getId();

        switch(viewID){
            case R.id.colosseumButton:
                Toast.makeText(getApplicationContext(),"Colosseum clicked",Toast.LENGTH_SHORT).show();
            case R.id.stadiumButton:
                Toast.makeText(getApplicationContext(),"Stadium clicked",Toast.LENGTH_SHORT).show();
        }

    }
}
