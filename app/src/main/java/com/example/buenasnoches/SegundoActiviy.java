package com.example.buenasnoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SegundoActiviy extends AppCompatActivity {

    private TextView frase_vw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo_activiy);

        frase_vw=(TextView)findViewById(R.id.txtv_1);

    }

    public void siguiente1(View view1){
        AdminSQLite admin =new AdminSQLite(this, "dbnoches",null,1);

        SQLiteDatabase db = admin.getWritableDatabase();
        int numrandom_int = (int)(Math.random()*5+1);

      String numrandom=String.valueOf(numrandom_int) ;

        Cursor fila = db.rawQuery
                ("select Nombre,Id FROM cancion WHERE Id="+numrandom, null);

        if (fila.moveToFirst()){
            frase_vw.setText(fila.getString(0));

            db.close();
        } else {
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

}