package com.jito.tiendaud3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VerPedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);
        getSupportActionBar().setTitle(R.string.verpedidos);
    }
}
