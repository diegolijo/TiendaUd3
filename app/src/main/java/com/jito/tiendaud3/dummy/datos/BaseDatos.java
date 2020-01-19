package com.jito.tiendaud3.dummy.datos;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class, Pedido.class}, version = 1)
public abstract class BaseDatos extends RoomDatabase {
    public abstract DeviceDao Dao();
}