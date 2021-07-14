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
    private TextView contadort,randomt,estadotv;
    private int IdCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {
        int id=v.getId();
        if(id == R.id.btt1){
            Intent iniciob= new Intent(this,SegundoActiviy.class);
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
            contadort.setText(contador.getString(0));
        } else {
            Toast.makeText(this,"Error al encontrar el maximo", Toast.LENGTH_SHORT).show();
            db.close();
        }
        //GENERAR NUMERO ALEATORIO CON EL MAXIMO
        int parametroc = Integer.parseInt(String.valueOf(contadort));
        int numrandom_int = (int)(Math.random()*parametroc+1);

        String numrandom= String.valueOf(numrandom_int);

        Cursor fila = db.rawQuery
                ("select Id,Estado FROM cancion WHERE Id="+numrandom, null);
        if (contador.moveToFirst()){
            randomt.setText(contador.getString(0));
            estadotv.setText(contador.getString(1));

        } else {
            Toast.makeText(this,"Error al econtrar el id", Toast.LENGTH_SHORT).show();
            db.close();
        }

        int estado_int = Integer.parseInt(String.valueOf(estadotv));


    }
}