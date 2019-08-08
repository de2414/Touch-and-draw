package com.frei.lab14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MySurface> circles = new ArrayList<MySurface>();
    MySurface mCircle;

    private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.root);

        final MySurface surf = new MySurface(this,null,0);
        surf.invalidate();
        ll.addView(surf);

    }




}
