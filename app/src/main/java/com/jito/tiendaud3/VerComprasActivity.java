package com.jito.tiendaud3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VerComprasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_compras);
        getSupportActionBar().setTitle(R.string.vercompras);
    }
}
