package com.jito.tiendaud3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeviceDao {

    @Query("SELECT * FROM usuario")
    List<Usuario> selectUsusarios();


    @Query("SELECT * FROM usuario WHERE usuario LIKE :usuario  LIMIT 1")
    Usuario selectUsuario(String usuario);

    @Query("SELECT * FROM usuario WHERE email LIKE :email  LIMIT 1")
    Usuario selectEmail(String email);



    @Query("SELECT * FROM pedido")
    List<Pedido> selectPedido();

    @Query("SELECT * FROM pedido WHERE aceptado = :aceptado")
    Usuario selectAceptados(String aceptado);



    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    void insertUsuario(Usuario user);

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    void insertPedido(Pedido pedido);


    @Insert
    void insertBothUsers(Usuario user1, Usuario user2);

    @Insert
    void insertUsersAndFriends(Usuario user, List<Usuario> friends);


    @Delete
    void delete(Usuario user);

}