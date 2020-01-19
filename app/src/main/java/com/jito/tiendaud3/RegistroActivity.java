package com.jito.tiendaud3;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.jito.tiendaud3.dummy.datos.BaseDatos;
import com.jito.tiendaud3.dummy.datos.Usuario;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle( R.string.registro);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    public void ClickRexistro(View view) {

        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //


        EditText ETnome = findViewById(R.id.editText_nombre);
        EditText ETapellidos = findViewById(R.id.editText_apellidos);
        EditText ETemail = findViewById(R.id.editText_email);
        EditText ETuser = findViewById(R.id.editText_NomeUsuario);
        EditText ETpass = findViewById(R.id.editText_contraseña);
        EditText ETpass2 = findViewById(R.id.editText_contraseña2);
        TextView TVaviso = findViewById(R.id.textView_info);


        String nome = ETnome.getText().toString();
        String apellidos = ETapellidos.getText().toString();
        String email = ETemail.getText().toString();
        String user = ETuser.getText().toString();
        String pass = ETpass.getText().toString();
        String pass2 = ETpass2.getText().toString();


        if (nome.equals("drop table") || apellidos.equals("drop table")|| email.equals("drop table") || pass.equals("drop table")|| pass2.equals("drop table")) {
            TVaviso.setText(R.string.camposVacios);
        }



        if (nome.equals("") || apellidos.equals("") || email.equals("") || pass.equals("") || pass2.equals("")) {
            TVaviso.setText(R.string.camposVacios);
        } else if(!email.contains("@") || !email.contains(".com")) {
            TVaviso.setText(R.string.emailIncorrecto);
        }else{


            CheckBox checkAdmin = findViewById(R.id.checkBox_Admin);
            boolean ckAdmin = false;
            if (checkAdmin.isChecked()) {
                ckAdmin = true;
            } else {

            }

            //crompobamos  contraseñas son iguales
            if (!pass.equals(pass2)) {
                TVaviso.setText(R.string.ContraseñaDiferente);
            } else {
                TVaviso.setText("");

                Usuario usuario = new Usuario();
                usuario.nombre = nome;
                usuario.apellidos = apellidos;
                usuario.email = email;
                usuario.usuario = user;
                usuario.contraseña=pass;
                usuario.admin = ckAdmin;



                // comprobamos usuario y mail
                Usuario usuarioBD = db.Dao().selectUsuario(user);
                Usuario usuarioMail = db.Dao().selectUsuario(email);

                if (usuarioBD != null) {
                    TVaviso.setText(R.string.usuarioregistado);
                    //mail
                } else if (usuarioMail != null) {
                    TVaviso.setText(R.string.emailregistrado);
                } else {

                    TVaviso.setText("");
                    db.Dao().insertUsuario(usuario);

                    Usuario usuarioNuevo = db.Dao().selectUsuario(user);

                    Toast toastregistrado = Toast.makeText(this, String.format("Usuario registrado\n%s\n%s\n%s\n%s", usuarioNuevo.usuario, usuarioNuevo.email, usuarioNuevo.nombre, usuarioNuevo.apellidos), Toast.LENGTH_LONG);
                    toastregistrado.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toastregistrado.show();

                    finish();


                }
            }
        }
    }
}