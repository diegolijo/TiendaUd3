package com.jito.tiendaud3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jito.tiendaud3.dummy.datos.BaseDatos;
import com.jito.tiendaud3.dummy.datos.Usuario;

import java.text.MessageFormat;

public class MenuActivityAdmin extends AppCompatActivity {
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        Toolbar Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(R.string.menu);

        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("usuario");

        // abrir bd
        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //
        //buscar usuario
        Usuario usuarioBD = db.Dao().selectUsuario(usuario);


        TextView TV_nombre = findViewById(R.id.TextView_nombre);
        TV_nombre.setText(MessageFormat.format("Benvido {0}", usuarioBD.nombre+" "+usuarioBD.apellidos));

        androidx.appcompat.widget.AppCompatImageView foto = findViewById(R.id.view_foto);
        foto.setBackgroundResource(R.drawable.foto_admin);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuadmin, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.menu_pedido_tamite:
                clickverPedidoMenu();
                return true;
            case R.id.menu_ver_aceptados:
                clickveracAptadossMenu();
                return true;
            case R.id.menu_ver_rechazados:
                clickverRechazadosMenu();
                return true;
            case R.id.menu_salir:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    public void verPedidosTramite(View view) {
        clickverPedidoMenu();
    }


    public void verPedidosAceptados(View view) {

        clickveracAptadossMenu();
    }

    public void verPedidosRechazados(View view) {
        clickverRechazadosMenu();
    }

    public void salir(View view) {
        finish();
    }




    private void clickverRechazadosMenu() {
        Intent intent = new Intent(this, VerPedidosActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("estado", "rechazado");
        intent.putExtra("admin", true);
        intent.putExtra("titulo", "Pedidos rexeitados");
        startActivity(intent);
    }

    private void clickveracAptadossMenu() {
        Intent intent = new Intent(this, VerPedidosActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("estado", "aceptado");
        intent.putExtra("admin", true);
        intent.putExtra("titulo", "Pedidos aceptados");
        startActivity(intent);
    }

    private void clickverPedidoMenu() {
        Intent intent = new Intent(this, VerPedidosActivity.class);
        intent.putExtra("admin", true);
        intent.putExtra("usuario", usuario);
        intent.putExtra("estado", "");
        intent.putExtra("titulo", "Pedidos en tramite");
        startActivity(intent);
    }

}
