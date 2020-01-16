package com.jito.tiendaud3;


public final class EstructuraBD {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private EstructuraBD() {
    }

    /* Inner class that defines the table contents */

    public static final String TABLE_USER = "Usuario";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Nombre";
    public static final String COLUMN_NAME_2 = "Apellidos";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_USERNAME = "NombreUsuario";
    public static final String COLUMN_PASS = "Contraseña";
    public static final String COLUMN_ADMIN = "Admin";


    public static final String SQL_CREATE_TABLES =
            "CREATE TABLE " + EstructuraBD.TABLE_USER + " (" +
             //       EstructuraBD.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EstructuraBD.COLUMN_NAME + " TEXT PRIMARY KEY ," +
                    EstructuraBD.COLUMN_NAME_2 + " TEXT ," +
                    EstructuraBD.COLUMN_EMAIL + " TEXT ," +
                    EstructuraBD.COLUMN_USERNAME + " TEXT ," +
                    EstructuraBD.COLUMN_PASS + " TEXT ," +
                    EstructuraBD.COLUMN_ADMIN + " INTEGER )";

    //

    public static final String SQL_DELETE_TABLES =
            "DROP TABLE IF EXISTS " + EstructuraBD.TABLE_USER;


    public static final String SQL_SELECT =
            "DROP TABLE IF EXISTS " + EstructuraBD.TABLE_USER;

}

