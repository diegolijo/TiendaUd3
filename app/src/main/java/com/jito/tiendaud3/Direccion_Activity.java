package com.jito.tiendaud3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class Direccion_Activity extends AppCompatActivity {

    String usuario;
    String categoria;
    String producto;
    int cantidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion_);
        getSupportActionBar().setTitle(R.string.direccion);

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("usuario");
        categoria = extras.getString("categoria");
        producto = extras.getString("producto");
        cantidade = Integer.parseInt(extras.getString("cantidade"));
    }


    public void finalizar(View view) {

        EditText ETrua = findViewById(R.id.editText_direccion);
        EditText ETcidade = findViewById(R.id.editText_cidade);
        EditText ETcp = findViewById(R.id.editText_cp);

        String rua = ETrua.getText().toString();
        String cidade = ETcidade.getText().toString();
        int cp = Integer.parseInt(ETcp.getText().toString());


        //abrir base de datos
        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build();

        Pedido pedido = new Pedido();
        pedido.categoria = categoria;
        pedido.cidade = cidade;
        pedido.cp = cp;
        pedido.cantidade = cantidade;
        pedido.usuario = usuario;
        pedido.producto = producto;
        pedido.rua = rua;

        db.Dao().insertPedido(pedido);

        finish();
    }


}
