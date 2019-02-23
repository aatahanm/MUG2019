package rokettakimi.mug2019;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Cagatay on 23.02.2019.
 */

public class NavigationContentManager {

    private ImageView naviagationImgView;
    private TextView pinNameView;
    private TextView pinOriginView;
    private TextView navigationContentView;

    public NavigationContentManager(ImageView navigationImgView,TextView pinNameView,TextView pinOriginView,TextView navigationContentView){

        this.naviagationImgView = navigationImgView;
        this.pinNameView = pinNameView;
        this.pinOriginView = pinOriginView;
        this.navigationContentView = navigationContentView;
    }

    public void setNavigationDrawerContent(int navigationImgID,String pinName,String pinOrigin,String content){

        naviagationImgView.setImageResource(navigationImgID);
        pinNameView.setText(pinName);
        pinOriginView.setText("("+pinOrigin+")");
        navigationContentView.setText(content);
    }


}
