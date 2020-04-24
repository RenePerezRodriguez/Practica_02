package com.example.dados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
    private Button lanzar;
    private TextView estado,numeroAleatorio;
    int puntuacion=0;

    //private static final int num = 10;
    //int ramdon = (int)((Math.random()* num)+1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        entradaDado = findViewById(R.id.txtNumeroUsuario);

        lanzar = findViewById(R.id.btnJugar);


        estado = findViewById(R.id.txtVictorias);
        numeroAleatorio = findViewById(R.id.txtNumeroAleatorio);


    }


    public void DevolverNumero(View view)
    {

        int dado = Integer.parseInt(entradaDado.getText().toString());

        if (dado >= 1 && dado <= 10){

            //llama a la clase
            SqlHelper admin = new SqlHelper(this,
                    "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            ContentValues valor = new ContentValues();
            valor.put("punto" , puntuacion);



            if (dado == generarNumeroAleatorio())
            {
                puntuacion++;
                bd.insert("tablaAumento",null , valor);
                bd.close();
                estado.setText(String.format("Puntuacion %d", puntuacion));
            }
            else{

                estado.setText(String.format("Puntuacion %d", puntuacion));
            }

        }

        else {

            Toast.makeText(getApplicationContext(),"Debe ingresar un numero valido del 1 al 10",Toast.LENGTH_LONG);
        }
    }



    public int generarNumeroAleatorio(){


        Random aleatorio = new Random(System.currentTimeMillis());
        // Producir nuevo int aleatorio entre 0 y 9
        int intAletorio = (aleatorio.nextInt(10) + 1);



        // Refrescar datos aleatorios
        aleatorio.setSeed(System.currentTimeMillis());

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

        bd.update("tablaAumento", valor,"punto",null);

        bd.close();


    }
}
