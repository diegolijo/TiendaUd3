package com.jito.tiendaud3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.login);

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }

    }

    // BDHelper helper = new BDHelper(this);


    public void clickLogin(View view) {

        //capturar textTiew
        TextView TVetiqueta = findViewById(R.id.textView_info);
        EditText ETuser = findViewById(R.id.editText_usuario);
        EditText ETpass = findViewById(R.id.editText_Pass);
        String contraseña = ETpass.getText().toString();
        String usuario = ETuser.getText().toString();
        TVetiqueta.setText("");

        if (contraseña.equals("") || usuario.equals("")) {

            TVetiqueta.setText(R.string.camposVacios);

        } else {
            //abrir base de datos
            BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                    BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //

            //select  usuarios
            Usuario usuarioBd = db.Dao().selectUsuario(usuario);

            //  existe usuario  && combrobar contraseña
            if (usuarioBd == null) {
                TVetiqueta.setText(R.string.usuarioNoRegistrado);
            } else {
                if (!usuarioBd.contraseña.equals(contraseña)) {
                    TVetiqueta.setText(R.string.contraseñaIncorrecta);
                } else {
                    if (usuarioBd.admin) {

                        TVetiqueta.setText("");

                        Intent intent = new Intent(this, MenuActivityUser.class);
                        intent.putExtra("usuario", usuarioBd.usuario);
                        intent.putExtra("ocultar_botones", false);
                        startActivity(intent);

                    } else {

                    }
                    Intent intent = new Intent(this, MenuActivity.class);
                    intent.putExtra("usuario", usuarioBd.usuario);
                    intent.putExtra("ocultar_botones", false);
                    startActivity(intent);
                }
            }
        }

    }


    public void clickRexistro(View view) {

        TextView TVetiqueta2 = findViewById(R.id.textView_info);
        TVetiqueta2.setText("");
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

}