package com.jito.tiendaud3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;

public class MenuActivityUser extends AppCompatActivity {
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle(R.string.menuAdmin);

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("usuario");
        boolean ocultar_botones = extras.getBoolean("ocultar_botones");

        if (ocultar_botones) {
            Button btn1 = findViewById(R.id.button);
            Button btn2 = findViewById(R.id.button2);
            Button btn3 = findViewById(R.id.button3);
            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
        }

        // abrir bd
        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //

        //buscar usuario

        Usuario usuarioBD = db.Dao().selectUsuario(usuario);

        TextView TV_nombre = findViewById(R.id.TextView_nombre);
        TV_nombre.setText(MessageFormat.format("Benvido {0}", usuarioBD.usuario));

        androidx.appcompat.widget.AppCompatImageView foto = findViewById(R.id.view_foto);
        foto.setBackgroundResource(R.drawable.foto_user);


    }

    public void verPedidosTramite(View view) {
        Intent intent = new Intent(this, VerPedidosActivity.class);
        startActivity(intent);
        //  intent.putExtra("usuario", 1);
    }


    public void verPedidosAceptados(View view) {
        Intent intent = new Intent(this, VerPedidosActivity.class);
        startActivity(intent);
        //  intent.putExtra("usuario", 1);

    }

    public void verPedidosRechazados(View view) {
        Intent intent = new Intent(this, VerPedidosActivity.class);
        startActivity(intent);
    }

    public void salir(View view) {
        finish();
    }


}
