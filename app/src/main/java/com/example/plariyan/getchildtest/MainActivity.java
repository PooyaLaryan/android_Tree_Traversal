package com.example.plariyan.getchildtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import static android.R.attr.streamType;
import static android.R.attr.tag;
import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int GAME_BOARD_COUNT = 9;
    private static final int PEICE1 = 0;
    private static final int PEICE2 = 1;
    private static final int NOT_SELECTED = 0;

    private int[] gameStatus = new int[GAME_BOARD_COUNT];
    private int[] peiceImage = {R.drawable.peice1,R.drawable.peice2};
    private int turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init(false);
    }


    private void Init(boolean reset) {
        LinearLayout playGround = (LinearLayout) findViewById(R.id.play_Ground);
        int tagCounter = 0;
        for (int i = 0; i < playGround.getChildCount(); i++) {
            LinearLayout row = playGround.getChildAt(i) instanceof LinearLayout ?
                    (LinearLayout) playGround.getChildAt(i) : null;

            if (row != null) {
                for (int j = 0; j < row.getChildCount(); j++) {
                    ImageView img = row.getChildAt(j) instanceof ImageView ? (ImageView) row.getChildAt(j) : null;
                    if (img != null) {
                        if (!reset)
                        {
                            img.setOnClickListener(this);
                            img.setTag(tagCounter++);
                        }
                        img.setImageResource(0);
                    }
                }
            }
        }

        Reset();
    }

    @Override
    public void onClick(View v) {
        ImageView view = (ImageView) v;
        DoGame(view);
    }

    private void DoGame(View v)
    {
        ImageView img = (ImageView)v;
        String tag = (String) v.getTag();
        if (gameStatus[Integer.parseInt(tag)] == NOT_SELECTED)
        {
            img.setImageResource(peiceImage[turn]);
            turn = 1 - turn;
        }
    }

    private void Reset()
    {
        for (int i = 0 ;i<gameStatus.length;i++)
        {
            gameStatus[i] = NOT_SELECTED;
        }
        turn = PEICE1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Reset Game").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Init(true);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
