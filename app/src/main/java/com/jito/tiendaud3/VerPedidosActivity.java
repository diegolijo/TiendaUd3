package com.jito.tiendaud3;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.jito.tiendaud3.dummy.datos.AdaptadorPedidos;
import com.jito.tiendaud3.dummy.datos.BaseDatos;
import com.jito.tiendaud3.dummy.datos.Pedido;

import java.util.List;

public class VerPedidosActivity extends AppCompatActivity {

    boolean admin;
    int numeroPedido;
    String usuario;
    String estado;
    List<Pedido> pedidoList;
    private RecyclerView recyclerViewPedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);


        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("usuario");
        estado = extras.getString("estado");
        admin = extras.getBoolean("admin");
        String titulo = extras.getString("titulo");

        Toolbar Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(titulo);

        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //

        if (admin) {
            pedidoList = db.Dao().selectAceptados(estado);
        } else {
            pedidoList = db.Dao().selectPedidosEstado(usuario, estado);
        }
        recyclerViewPedidos = findViewById(R.id.rvPedidos);

        recyclerViewPedidos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewPedidos.setLayoutManager(linearLayoutManager);


        recyclerViewPedidos.offsetTopAndBottom(1);

        AdaptadorPedidos recyclerAdapterPedidos = new AdaptadorPedidos(pedidoList);
        recyclerViewPedidos.setAdapter(recyclerAdapterPedidos);


        //dialogo -> actualizar el campo estado en BD
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Aceptar ou rexeitar o pedido");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                        BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //
                db.Dao().updatePedido(numeroPedido, "aceptado");
                refrescarGrid();
                estado = "";
                Toast.makeText(VerPedidosActivity.this, "Pedido aceptado", Toast.LENGTH_SHORT).show();


            }
        });

        builder.setNegativeButton("Rexeitar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                        BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //
                db.Dao().updatePedido(numeroPedido, "rechazado");
                refrescarGrid();
                estado = "";
                Toast.makeText(VerPedidosActivity.this, "Pedido rexeitado", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                estado = "";
            }
        });

        recyclerAdapterPedidos.evento(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (admin && estado.equals("")) {

                    numeroPedido = pedidoList.get(recyclerViewPedidos.getChildAdapterPosition(v)).numero;


                    AlertDialog dialogo = builder.create();
                    dialogo.show();

                }

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }



    private void refrescarGrid() {

        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //

        //refresxamos el recylerView
        pedidoList = db.Dao().selectAceptados("");
        AdaptadorPedidos recyclerAdapterPedidos = new AdaptadorPedidos(pedidoList);
        recyclerViewPedidos.setAdapter(recyclerAdapterPedidos);
    }

}
