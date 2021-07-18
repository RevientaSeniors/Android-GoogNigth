package com.example.buenasnoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    String randomt;
    int parametroc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {
        int id=v.getId();
        if(id == R.id.btt1){

            GetRandomId();

            Intent iniciob= new Intent(this,SegundoActiviy.class);
            iniciob.putExtra("id",randomt);
            startActivity(iniciob);

        }

    }

    public void GetRandomId(){

        AdminSQLite admin =new AdminSQLite(this, "dbnoches",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //BUSACAR EL MAXIMO
        Cursor contador = db.rawQuery
                ("select MAX(Id) FROM cancion ", null);

        if (contador.moveToFirst()){
            parametroc = Integer.parseInt(String.valueOf(contador.getString(0)));
        } else {
            Toast.makeText(this,"Error al encontrar el maximo", Toast.LENGTH_SHORT).show();
            db.close();
        }

            int numrandom_int = (int)(Math.random() * parametroc + 1);

            String numrandom = String.valueOf(numrandom_int);
            Cursor fila = db.rawQuery
                    ("select Id FROM cancion WHERE Id=" + numrandom, null);
            if (fila.moveToFirst()) {
                randomt=fila.getString(0);

            } else {
                Toast.makeText(this, "Error al econtrar el id", Toast.LENGTH_SHORT).show();
                db.close();
            }

            db.close();


    }
}