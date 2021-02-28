package fr.informabox.nootropiques;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class MoreDetails extends AppCompatActivity {
    int valPOS = 0;
    int valLINE = 0;
    MediaPlayer mediaPlayer;
    //Spinner spinmol;
    boolean playstopstat = false;

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //spinmol = (Spinner) findViewById(R.id.planets2_spinner);
        // inflate the layout
        View myLayout = LayoutInflater.from(this).inflate(R.layout.activity_main_details,null);
        // load the text view
        //TextView myView = (TextView) myLayout.findViewById(R.id.MY_VIEW);
        //spinmol = (Spinner) myLayout.findViewById(R.id.planets2_spinner);

        Bundle NPOS = getIntent().getExtras();
        if(NPOS != null) {
            valPOS = NPOS.getInt("position");
            valLINE = NPOS.getInt("line");
        }

        String texte="NO SELECTED!";
        //Spinner spinmol = (Spinner) findViewById(R.id.planets2_spinner);
        String textpin = GlobalV.moleculeselected;//spinmol.getSelectedItem().toString();

        //exemple pour l'implementation
        //if (textpin.equals("adrafinil")){ texte = getResources().getString(R.string.adrafinil); }
        //if (textpin.equals("alphagpc") ){ texte = getResources().getString(R.string.alphagpc); }

        ((TextView)findViewById(R.id.mymol)).setText(texte);

    }

    public void playstop(View v){
        this.playstopstat = !(this.playstopstat);
        String textpin = GlobalV.moleculeselected;//spinmol.getSelectedItem().toString();
        if (this.playstopstat) {

            //exemple pour l'implementation
            //if (textpin.equals("adrafinil")){ mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.adrafinil); }
            //if (textpin.equals("alphagpc")){ mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alpha_gpc); }

            mediaPlayer.start();
        }
        else {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
