package fr.informabox.nootropiques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Start(View v){
        Intent intent = new Intent(this, MainDetails.class);
        startActivity(intent);
    }
    public void boutique(View v){
        Intent intent = new Intent(this, LaGvidiloProducts.class);
        startActivity(intent);
    }
    public void faq(View v){
        Intent intent = new Intent(this, FAQ.class);
        startActivity(intent);
    }
    public void favoris(View v){
        Intent intent = new Intent(this, Favoris.class);
        startActivity(intent);
    }
    public void about(View v){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}
