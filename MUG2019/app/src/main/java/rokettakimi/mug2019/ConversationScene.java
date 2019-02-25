package rokettakimi.mug2019;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ConversationScene extends AppCompatActivity {

    private boolean conversationOver;
    private boolean questionOver;
    private int conversationState;
    private int questionState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.conversation_scene);

        conversationOver = false;
        questionOver = false;
        conversationState = 1;
        questionState = 1;

        Typewriter writer = (Typewriter) findViewById(R.id.charTextView);
        //Add a character every 100ms
        writer.setCharacterDelay(30);
        writer.animateText("Merhaba, Regulus! Bugün de yüzünden\nçok yorgun olduğun anlaşılıyor.\nAma biliyorum ki sen en güçlü ve\nyetenekli komutanlardan birisin!");

        final Button changeCharTextButton = (Button) findViewById(R.id.charTextViewNextButton);
        final Typewriter changeSelectText1 = (Typewriter) findViewById(R.id.selectText1);
        final Typewriter changeSelectText2 = (Typewriter) findViewById(R.id.selectText2);
        final Typewriter changeSelectText3 = (Typewriter) findViewById(R.id.selectText3);

        //disable changeCharText
        changeCharTextButton.setEnabled(false);
        changeCharTextButton.setVisibility(View.INVISIBLE);

        //disabled CardViewSelects
        final LinearLayout changeCharSelectTextView = (LinearLayout) findViewById(R.id.charSelectTextView);
        changeCharSelectTextView.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //enable changeCharText
                changeCharTextButton.setVisibility(View.VISIBLE);
                changeCharTextButton.setEnabled(true);
            }
        }, 5500);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(changeCharTextButton)) {
                    if(conversationState == 1){
                        Typewriter writer = (Typewriter)findViewById(R.id.charTextView);
                        writer.setCharacterDelay(30);
                        writer.animateText("Sana da merhaba, Gallus! Biliyorsun\nki ben ordumla sefere çıktığımda çok\nuzun yollar gidebiliyorum. Ne kadar\ngidebildiğimizi tahmin edebilir misin?");

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                changeCharSelectTextView.setVisibility(View.VISIBLE);

                                Typewriter writer2 = (Typewriter)findViewById(R.id.questionInfo);
                                writer2.setCharacterDelay(0);
                                writer2.animateText("Bir tahminde bulun!");

                                Typewriter writer3 = (Typewriter)findViewById(R.id.selectText1);
                                writer3.setCharacterDelay(0);
                                writer3.animateText("60 kilometre olabilir mi?");

                                Typewriter writer4 = (Typewriter)findViewById(R.id.selectText2);
                                writer4.setCharacterDelay(0);
                                writer4.animateText("Yaklaşık 40 kilometre kadar mı?");

                                Typewriter writer5 = (Typewriter)findViewById(R.id.selectText3);
                                writer5.setCharacterDelay(0);
                                writer5.animateText("Emin değilim ama en fazla 5 kilometredir!");
                            }
                        }, 6000);

                        //disable changeCharText
                        changeCharTextButton.setVisibility(View.INVISIBLE);
                        changeCharTextButton.setEnabled(false);


                        System.out.println("aga");
                        //enable selectTexts
                        changeSelectText1.setEnabled(true);
                        changeSelectText2.setEnabled(true);
                        changeSelectText3.setEnabled(true);
                    }
                    else if(conversationState == 2){
                        Typewriter writer = (Typewriter)findViewById(R.id.charTextView);
                        writer.setCharacterDelay(30);
                        writer.animateText("Aferin! Doğru bildin. Ordumdan ve\nkendimden çok gurur duyuyorum.\nÇünkü hiç bir olaya geç kalmıyoruz!");

                        Typewriter writer2 = (Typewriter)findViewById(R.id.questionInfo);
                        writer2.setCharacterDelay(0);
                        writer2.animateText("");

                        //disable changeCharText
                        changeCharTextButton.setVisibility(View.INVISIBLE);
                        changeCharTextButton.setEnabled(false);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //enable changeCharText
                                changeCharTextButton.setVisibility(View.VISIBLE);
                                changeCharTextButton.setEnabled(true);
                            }
                        }, 3000);

                        conversationState++;
                    }
                    else if(conversationState == 3) {
                        Typewriter writer = (Typewriter)findViewById(R.id.charTextView);
                        writer.setCharacterDelay(30);
                        writer.animateText("Ben de sizle çok gurur duyuyorum.\nÖzür dilerim ama bu konuşmayı\nkısa keseceğim çünkü yılın en büyük\nolayına yetişmem lazım!");

                        //disable changeCharText
                        changeCharTextButton.setVisibility(View.INVISIBLE);
                        changeCharTextButton.setEnabled(false);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //enable changeCharText
                                changeCharTextButton.setVisibility(View.VISIBLE);
                                changeCharTextButton.setEnabled(true);
                            }
                        }, 3000);

                        conversationState++;
                    }
                    else if(conversationState == 4) {
                        Typewriter writer = (Typewriter)findViewById(R.id.charTextView);
                        writer.setCharacterDelay(40);
                        writer.animateText("Çok heyecanlı ve sabırsız\ngözüküyordun zaten. Bu kadar büyük\nolan olay ne olabilir ki?");

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Typewriter writer2 = (Typewriter)findViewById(R.id.questionInfo);
                                writer2.setCharacterDelay(0);
                                writer2.animateText("Bir tahminde bulun!");

                                Typewriter writer3 = (Typewriter)findViewById(R.id.selectText1);
                                writer3.setCharacterDelay(0);
                                writer3.animateText("Circus Maximus'daki at arabası yarışları");

                                Typewriter writer4 = (Typewriter)findViewById(R.id.selectText2);
                                writer4.setCharacterDelay(0);
                                writer4.animateText("Marcellus Tiyatro Sahnesi'nde Pandomim gösterisi");

                                Typewriter writer5 = (Typewriter)findViewById(R.id.selectText3);
                                writer5.setCharacterDelay(0);
                                writer5.animateText("Kolezyum'daki Gladiyator savaşları");
                            }
                        }, 3000);

                        //disable changeCharText
                        changeCharTextButton.setVisibility(View.INVISIBLE);
                        changeCharTextButton.setEnabled(false);

                        //enable selectTexts
                        changeSelectText1.setEnabled(true);
                        changeSelectText2.setEnabled(true);
                        changeSelectText3.setEnabled(true);
                    }
                    else if (conversationState == 5) {
                        changeCharSelectTextView.setVisibility(View.INVISIBLE);

                        Typewriter writer = (Typewriter)findViewById(R.id.charTextView);
                        writer.setCharacterDelay(30);
                        writer.animateText("Circus Maximus'daki at arabası yarışları\ntabii ki! Bugün 250 Bin kişiden daha fazla\nkatılım olacağı düşünülüyor. Neyse,\ngörüşmek üzere!");

                        Typewriter writer2 = (Typewriter)findViewById(R.id.questionInfo);
                        writer2.setCharacterDelay(0);
                        writer2.animateText("");

                        //disable changeCharText
                        changeCharTextButton.setVisibility(View.INVISIBLE);
                        changeCharTextButton.setEnabled(false);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //enable changeCharText
                                changeCharTextButton.setVisibility(View.VISIBLE);
                                changeCharTextButton.setEnabled(true);
                            }
                        }, 4000);

                        conversationState++;
                    }
                    else if (conversationState == 6 && !conversationOver) {
                        Typewriter writer = (Typewriter)findViewById(R.id.charTextView);
                        writer.setCharacterDelay(30);
                        writer.animateText("Görüşürüz, Gallus.");
                        conversationOver = true;
                    }
                    else if (conversationOver) {
                        finish();
                        //exit to the other scene
                    }
                }
                else if (v.equals(changeSelectText1)) {
                    if(questionState == 1){
                        System.out.println("WTF");
                        Typewriter writer = (Typewriter)findViewById(R.id.questionInfo);
                        writer.setCharacterDelay(30);
                        writer.animateText("Lütfen bir daha deneyin.");
                    }
                    else if(questionState == 2){
                        Typewriter writer = (Typewriter)findViewById(R.id.questionInfo);
                        writer.setCharacterDelay(30);
                        writer.animateText("Doğru seçenek!");

                        Typewriter writer3 = (Typewriter)findViewById(R.id.selectText1);
                        writer3.setCharacterDelay(0);
                        writer3.animateText("");

                        Typewriter writer4 = (Typewriter)findViewById(R.id.selectText2);
                        writer4.setCharacterDelay(0);
                        writer4.animateText("");

                        Typewriter writer5 = (Typewriter)findViewById(R.id.selectText3);
                        writer5.setCharacterDelay(0);
                        writer5.animateText("");

                        questionState++;
                        conversationState++;
                        questionOver = true;

                        //enable changeCharText
                        changeCharTextButton.setVisibility(View.VISIBLE);
                        changeCharTextButton.setEnabled(true);
                    }
                }
                else if (v.equals(changeSelectText2)) {
                    if(questionState == 1){
                        Typewriter writer = (Typewriter)findViewById(R.id.questionInfo);
                        writer.setCharacterDelay(30);
                        writer.animateText("Doğru seçenek!");

                        Typewriter writer3 = (Typewriter)findViewById(R.id.selectText1);
                        writer3.setCharacterDelay(0);
                        writer3.animateText("");

                        Typewriter writer4 = (Typewriter)findViewById(R.id.selectText2);
                        writer4.setCharacterDelay(0);
                        writer4.animateText("");

                        Typewriter writer5 = (Typewriter)findViewById(R.id.selectText3);
                        writer5.setCharacterDelay(0);
                        writer5.animateText("");

                        //enable changeCharText
                        changeCharTextButton.setVisibility(View.VISIBLE);
                        changeCharTextButton.setEnabled(true);

                        questionState++;
                        conversationState++;
                    }
                    else if(questionState == 2){
                        Typewriter writer = (Typewriter)findViewById(R.id.questionInfo);
                        writer.setCharacterDelay(30);
                        writer.animateText("Lütfen bir daha deneyin.");
                    }
                }
                else if (v.equals(changeSelectText3)) {
                    if(questionState == 1){
                        Typewriter writer = (Typewriter)findViewById(R.id.questionInfo);
                        writer.setCharacterDelay(30);
                        writer.animateText("Lütfen bir daha deneyin.");
                    }
                    else if(questionState == 2){
                        Typewriter writer = (Typewriter)findViewById(R.id.questionInfo);
                        writer.setCharacterDelay(30);
                        writer.animateText("Lütfen bir daha deneyin.");
                    }
                }
            }
        };

        changeCharTextButton.setOnClickListener(listener);
        changeSelectText1.setOnClickListener(listener);
        changeSelectText2.setOnClickListener(listener);
        changeSelectText3.setOnClickListener(listener);
    }

    public void onClick(View v){

        System.out.println("clicked");

    }
}

