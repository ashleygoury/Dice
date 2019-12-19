package com.example.dice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] diceArray = new int[11];
    public int[] imgdice = {R.id.die1Image, R.id.die2Image, R.id.die3Image, R.id.die4Image, R.id.die5Image,
            R.id.die6Image, R.id.die7Image, R.id.die8Image, R.id.die9Image, R.id.die10Image, R.id.die11Image, R.id.die12Image};
    ArrayList dice = new ArrayList();
    ArrayList<ImageView> imageViews = new ArrayList<>();
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        for (int i = 0; i < 11; i++) {
            imageViews.add((ImageView) findViewById(imgdice[i]));
        }
    }

//    public void show() {
//        final AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
//        d.setTitle("Dice game");
//        d.setMessage("How many dice do you want?");
//        d.setView(dialogView);
//        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
//        numberPicker.setMaxValue(9);
//        numberPicker.setMinValue(1);
//        numberPicker.setWrapSelectorWheel(true);
//
//        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Log.d("TAG", "onClick: " + numberPicker.getValue());
//            }
//        });
//
//        AlertDialog alertDialog = d.create();
//        alertDialog.show();
//    }

    public void rollDice(View view) {
        for (int i = 0; i < 11; i++) {
            diceArray[i] = random.nextInt(6) + 1;
        }

        dice.clear();

        for (int i = 0; i < 11; i++) {
            dice.add(diceArray[i]);
        }

        for (int dieOfSet = 0; dieOfSet < 11; dieOfSet++) {
            String imageName = "die_" + dice.get(dieOfSet) + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                imageViews.get(dieOfSet).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
