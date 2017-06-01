package com.mobile.lab08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Adapter adapter;

    ArrayList<Fruit> list = new ArrayList<>();


    GridView gridView;
    GridAdapter gridAdapter;
    AddFruit addFruit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        gridView = (GridView) findViewById(R.id.gridview);
        list.add(new Fruit("아보카도", Fruit.imageList[0]));
        list.add(new Fruit("바나나", Fruit.imageList[1]));
        list.add(new Fruit("체리", Fruit.imageList[2]));
        list.add(new Fruit("크랜베리", Fruit.imageList[3]));
        list.add(new Fruit("포도", Fruit.imageList[4]));
        gridAdapter = new GridAdapter(this, list);
        gridView.setAdapter(gridAdapter);

        addFruit = (AddFruit) findViewById(R.id.addFruitBar);
        addFruit.setOnCustomAddListener(new AddFruit.OnAddListener() {
            @Override
            public void onAdd(String name, int imageNum) {
                Toast.makeText(MainActivity.this, name + "" + imageNum, Toast.LENGTH_SHORT).show();
                gridAdapter.addFruit(new Fruit(name, imageNum));
            }
        });
    }
}
