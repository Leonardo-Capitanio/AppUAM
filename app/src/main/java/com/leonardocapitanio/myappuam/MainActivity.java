package com.leonardocapitanio.myappuam;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    EditText txtEntradaDados;
    TextView txtSaidaDados;
    MyDBHandler dbHandler;

//======================================================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtEntradaDados = (EditText) findViewById(R.id.entradaDados);
        txtSaidaDados = (TextView) findViewById(R.id.saidaDados);
        dbHandler = new MyDBHandler(this, null, null, 1);
        imprimirDB();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
        }
//        })}

//==================================================================================================
        //Adicionar um produto à DB:
        public void addButtonClicked(View view)
        {
            Produtos p = new Produtos(txtEntradaDados.getText().toString());
            dbHandler.adicProduto(p);
            imprimirDB();

            Context context = getApplicationContext();
            CharSequence text = "Produto " + p.get_nomeproduto() + " inserido na lista com sucesso!";
            Toast toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
            toast.show();

        }
//==================================================================================================
        //Delete items da DB:
        public void deleteButtonClicked(View view) {
            String inputText = txtEntradaDados.getText().toString();
            dbHandler.deletarProduto(inputText);
            imprimirDB();

            Context context = getApplicationContext();
            CharSequence text = "Produto " + inputText + " retirado da lista com sucesso!";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();

        }
//==================================================================================================
        //Imprimir a base de dados
        public void imprimirDB(){
            String dbString = dbHandler.dbToString();
            txtSaidaDados.setText(dbString);
            txtEntradaDados.setText("");
        }
//======================================================================================================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //======================================================================================================================================================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Context context = getApplicationContext();
        CharSequence text = "Universidade Anhembi Morumbi\nComputação Móvel\n\nDesenvolvido por: \nLeonardo M. Capitanio - RA: 20634515";
        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            toast.show();

            MediaPlayer player;
            player = MediaPlayer.create(MainActivity.this,R.raw.pokehighfive);
            player.start();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
