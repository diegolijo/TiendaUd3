package com.jito.tiendaud3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    public int numero = 0;

    @ColumnInfo(name = "idusuario")
    public String usuario;

    @ColumnInfo(name = "categoria")
    public String categoria;

    @ColumnInfo(name = "producto")
    public String producto;

    @ColumnInfo(name = "cantidade")
    public int cantidade;

    @ColumnInfo(name = "rua")
    public String rua;

    @ColumnInfo(name = "cidade")
    public String cidade;

    @ColumnInfo(name = "cp")
    public int cp;

    @ColumnInfo(name = "aceptado")
    public Boolean aceptado;
}
