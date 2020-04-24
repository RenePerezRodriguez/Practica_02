package com.example.dados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText entradaDado;
    private TextView estado,numeroAleatorio;
    int puntuacion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entradaDado = findViewById(R.id.txtNumeroUsuario);
        estado = findViewById(R.id.txtVictorias);
        numeroAleatorio = findViewById(R.id.txtNumeroAleatorio);
    }

    public void DevolverNumero(View view)
    {
        int dado = Integer.parseInt(entradaDado.getText().toString());
        entradaDado.setText("");

        //if (entradaDado.length() != 0) {
            if (dado >= 1 && dado <= 10) {
                //llama a la clase
                SqlHelper admin = new SqlHelper(this,
                        "administracion", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                ContentValues valor = new ContentValues();
                if (dado == generarNumeroAleatorio()) {
                    puntuacion++;
                    valor.put("punto", (puntuacion));
                    bd.insert("tablaAumento", null, valor);
                    entradaDado.setText("");
                    bd.close();
                    estado.setText(String.format("Puntuacion %d", puntuacion));
                } else {
                    estado.setText(String.format("Puntuacion %d", puntuacion));
                }
            } else {
                Toast men = Toast.makeText(this, "Debe ingresar un numero valido del 1 al 10", Toast.LENGTH_LONG);

                men.show();
            }
        //}
//        else
//        {
//            Toast menn = Toast.makeText(this, "Debe ingresar un numero valido del 1 al 10", Toast.LENGTH_LONG);

//            menn.show();
//       / }
    }

    public int generarNumeroAleatorio(){
        Random aleatorio = new Random(System.currentTimeMillis());
        int intAletorio = (aleatorio.nextInt(10) + 1);
        numeroAleatorio.setText(String.valueOf(intAletorio));
        return  intAletorio;
    }

    public void reiniciarPunto(View view){

        puntuacion = 0;

        SqlHelper admin = new SqlHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues valor = new ContentValues();
        valor.put("punto" , puntuacion);

        bd.update("tablaAumento", valor,"punto ="+ puntuacion,null);

        bd.close();
    }
    public void cerrar(View view)
    {
        finish();
    }
}
