package com.example.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


public class menu extends AppCompatActivity {
    private EditText et_nombre, et_codigo,et_descipcion;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        et_codigo = (EditText) findViewById(R.id.Txt_codigo);
        et_nombre = (EditText) findViewById(R.id.Txt_nombre);
        et_descipcion = (EditText) findViewById(R.id.Txt_descripcion);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.pngegg);
    }
    public void registrar(View view) {
        mp = MediaPlayer.create(this, R.raw.boton);
        mp.start();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"miBBDD",null,1);
        SQLiteDatabase Bdb =admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String description = et_descipcion.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !description.isEmpty()){
            ContentValues registro =new ContentValues();
            registro.put("codigo",codigo);
            registro.put("description",description);
            registro.put("nombre",nombre);

            Bdb.insert("productos",null,registro);
            Bdb.close();
            et_codigo.setText("");
            et_nombre.setText("");
            et_descipcion.setText("");
            Toast.makeText(this, "Regstrado", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "faltan campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscar(View view) {
        mp = MediaPlayer.create(this, R.raw.boton);
        mp.start();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"miBBDD",null,1);
        SQLiteDatabase Bdb =admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = Bdb.rawQuery
                    ("select description, nombre from productos where codigo =" + codigo,null);
            if (fila.moveToFirst()){
                et_descipcion.setText(fila.getString(0));
                et_nombre.setText(fila.getString(1));
                Bdb.close();
            } else {
                Toast.makeText(this, "el producto no existe ", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "no has escrito codigo", Toast.LENGTH_SHORT).show();
        }

    }


    public void modificar(View view) {
        mp = MediaPlayer.create(this, R.raw.boton);
        mp.start();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"miBBDD",null,1);
        SQLiteDatabase Bdb =admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String description = et_descipcion.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !description.isEmpty()){
            ContentValues registro =new ContentValues();
            registro.put("codigo",codigo);
            registro.put("description",description);
            registro.put("nombre",nombre);
            int cantidad = Bdb.update("productos",registro,"codigo ="+codigo,null);
            Bdb.close();
            et_codigo.setText("");
            et_nombre.setText("");
            et_descipcion.setText("");
            if (cantidad ==1) {
                Toast.makeText(this, "el producto ha sido modificado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "el producto NO ha sido modificado", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "faltan campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(View view) {
        mp = MediaPlayer.create(this, R.raw.boton);
        mp.start();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"miBBDD",null,1);
        SQLiteDatabase Bdb =admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            int cantidad = Bdb.delete("productos","codigo ="+codigo,null);
            if (cantidad ==1) {
                Toast.makeText(this, "el producto ha sido eliminado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "el producto NO ha sido eliminado", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "no has escrito codigo", Toast.LENGTH_SHORT).show();
        }

    }
    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}