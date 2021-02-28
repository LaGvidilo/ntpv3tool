package fr.informabox.nootropiques;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainDetails extends AppCompatActivity {
    Spinner spincat;
    Spinner spinmol;
    int POSN = 0;
    int POSL = 0;
    ImageView img;
    ImageButton Fav;
    boolean FavStar = false;
    boolean vs = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);
        spincat = (Spinner) findViewById(R.id.planets_spinner);

        img = (ImageView) findViewById(R.id.imageMol);

        Fav = (ImageButton) findViewById(R.id.FavBtn);

        spinmol = (Spinner) findViewById(R.id.planets2_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spincat.setAdapter(adapter);
        spincat.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {

                        POSN = position;
                        if (position==0){
                            setpos0();
                            //arrpostruc = Arrays.asList(getResources().getStringArray(R.array.cat_memory_array));
                        }
                        if (position==1){
                            setpos1();
                            //arrpostruc = Arrays.asList(getResources().getStringArray(R.array.cat_focus_array));
                        }
                        if (position==2){
                            setpos2();
                            //arrpostruc = Arrays.asList(getResources().getStringArray(R.array.cat_regenerate_array));
                        }
                        if (position==3){
                            setpos3();
                            //arrpostruc = Arrays.asList(getResources().getStringArray(R.array.cat_energy_array));
                        }
                        try {
                            refreshimg();
                        }catch (Exception e){}
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        spinmol.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        POSL = position;
                        try {
                            refreshimg();
                        }catch (Exception e){}
                        //if (vs) {moredata();}
                        //vs = true;

                    }
  
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }

    private void refreshimg(){
        boolean isfaved = false;
        try {
            isfaved = this.is_favorited();
        }
        catch (Exception e){
        }
        if (isfaved){
            this.Fav.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else{
            this.Fav.setImageResource(android.R.drawable.btn_star_big_off);
        }
        String textpin = spinmol.getSelectedItem().toString();
        GlobalV.moleculeselected = textpin;

        //exemple pour l'implementation
        //if (textpin.equals("adrafinil")){ this.img.setImageResource(R.drawable.adrafinilimg); }
        //if (textpin.equals("alphagpc")){ this.img.setImageResource(R.drawable.l_alpha_gpc); }

    }

    public void setpos1(){
        String[] tab_names = getResources().getStringArray(R.array.cat_memory_array);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tab_names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinmol.setAdapter(dataAdapter);
    }
    public void setpos0(){
        String[] tab_names = getResources().getStringArray(R.array.cat_focus_array);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tab_names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinmol.setAdapter(dataAdapter);
    }
    public void setpos2(){
        String[] tab_names = getResources().getStringArray(R.array.cat_regenerate_array);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tab_names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinmol.setAdapter(dataAdapter);
    }
    public void setpos3(){
        String[] tab_names = getResources().getStringArray(R.array.cat_energy_array);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tab_names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinmol.setAdapter(dataAdapter);
    }
    public void moredata(View v){
        Intent intent = new Intent(this, MoreDetails.class);
        Bundle NPOS = new Bundle();
        NPOS.putInt("position", POSN); //Your id
        NPOS.putInt("line", POSL);
        intent.putExtras(NPOS); //Put your id to your next Intent
        startActivity(intent);
    }

    public void onStarClick(View v){
        String idunik = Integer.toString(POSN) +"-"+ Integer.toString(POSL);
        FavStar = !(FavStar);
        if (FavStar){
            this.Fav.setImageResource(android.R.drawable.btn_star_big_on);
            writeToFile(idunik,this);
            Log.d("WRITE","TO FILE.");
        }
        else{
            this.Fav.setImageResource(android.R.drawable.btn_star_big_off);
            removeLineFromFile("favoris.txt", idunik);
        }
    }


    private boolean is_favorited() throws IOException{
        String idunik = Integer.toString(POSN) +"-"+ Integer.toString(POSL);
        return isokay(idunik);
    }

    private boolean isokay(String ligne) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get("favoris.txt"), StandardCharsets.UTF_8);
        boolean res = false;
        if(lines.contains(ligne)) res = true;
        return res;
    }

    private boolean isexfile(String namefile){
        File tempFile = new File(namefile);
        return tempFile.exists();
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("favoris.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private boolean delfile(String filename){
        File dir = getFilesDir();
        File file = new File(dir, filename);
        return file.delete();
    }

    public void removeLineFromFile(String file, String lineToRemove) {
        // All the important information
        String inputFileName = "favoris.txt";
        String outputFileName = "favoris.txt.tmp";
        // The traps any possible read/write exceptions which might occur
        try {
            File inputFile = new File(inputFileName);
            File outputFile = new File(outputFileName);
            // Open the reader/writer, this ensure that's encapsulated
            // in a try-with-resource block, automatically closing
            // the resources regardless of how the block exists
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                // Read each line from the reader and compare it with
                // with the line to remove and write if required
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (!line.equals(lineToRemove)) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            // This is some magic, because of the compounding try blocks
            // this section will only be called if the above try block
            // exited without throwing an exception, so we're now safe
            // to update the input file

            // If you want two files at the end of his process, don't do
            // this, this assumes you want to update and replace the
            // original file

            // Delete the original file, you might consider renaming it
            // to some backup file
            if (inputFile.delete()) {
                // Rename the output file to the input file
                if (!outputFile.renameTo(inputFile)) {
                    throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
                }
            } else {
                throw new IOException("Could not delete original input file " + inputFileName);
            }
        } catch (IOException ex) {
            // Handle any exceptions
            ex.printStackTrace();
        }

        boolean isdead = delfile("favoris.txt");
        File dir = Environment.getExternalStorageDirectory();
        if(dir.exists()){
            File from = new File(dir,"favoris.txt.tmp");
            File to = new File(dir,"favoris.txt");
            if(from.exists())
                from.renameTo(to);
        }

    }

}
