package com.example.dice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score, dice1, dice2, dice3;
    ArrayList dice;
    ArrayList<ImageView> imageViews;
    Random random;
    TextView scoretxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        score = 0;

        random = new Random();
        dice = new ArrayList();
        scoretxt = findViewById(R.id.txtScore);

        ImageView die1Image = findViewById(R.id.die1Image);
        ImageView die2Image = findViewById(R.id.die2Image);
        ImageView die3Image = findViewById(R.id.die3Image);

        imageViews = new ArrayList<>();
        imageViews.add(die1Image);
        imageViews.add(die2Image);
        imageViews.add(die3Image);
    }

    public void show() {
        final AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        d.setTitle("Dice game");
        d.setMessage("How many dice do you want?");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMaxValue(9);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(true);

        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("TAG", "onClick: " + numberPicker.getValue());
            }
        });

        AlertDialog alertDialog = d.create();
        alertDialog.show();
    }

    public void rollDice(View view) {
        dice1 = random.nextInt(6)+1;
        dice2 = random.nextInt(6)+1;
        dice3 = random.nextInt(6)+1;

        dice.clear();
        dice.add(dice1);
        dice.add(dice2);
        dice.add(dice3);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++){
            String imageName = "die_" + dice.get(dieOfSet) + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                imageViews.get(dieOfSet).setImageDrawable(d);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        String msg;

        if(dice1 == dice2 && dice1 ==  dice3){
            int scoreDelta = dice1 * 100;
            score += scoreDelta;
            msg = "TRIPLE = " + scoreDelta + "\n" + "POINTS = " + score;
        } else if(dice1 ==  dice2 || dice1 == dice3 || dice2 == dice3){
            msg = "DOUBLE = 50" + "\n" + "PONITS = " + score;
            score += 50;
        } else {
            msg = "No win" + "\n" + "POINTS = " + score;
        }

        scoretxt.setText(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
