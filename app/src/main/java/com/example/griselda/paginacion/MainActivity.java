package com.example.griselda.paginacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Novedad> novedades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarNovedades();
        //inflarNovedades();
        RecyclerView contenedor = (RecyclerView) findViewById(R.id.contenedor);
        // se setea al contenedor un layoutmanager y el adapter
        contenedor.setLayoutManager(new LinearLayoutManager(this));
        contenedor.setAdapter(new NovedadAdapter());

    }


    public void inicializarNovedades() {
        novedades = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Novedad n = new Novedad();
            n.setImageRestId(R.drawable.condorito);
            n.setContenido("Contenido " + i);
            n.setTitulo("Titulo " + i);
            novedades.add(n);
        }
    }


    public void inflarNovedades() {

        LinearLayout contenedor = (LinearLayout) findViewById(R.id.contenedor);

        for (Novedad n : novedades) {
            View raiz = LayoutInflater.from(this).inflate(R.layout.novedad, contenedor, false);
            ImageView imagen = (ImageView) raiz.findViewById(R.id.imagen);
            TextView titulo = (TextView) raiz.findViewById(R.id.titulo);
            TextView contenido = (TextView) raiz.findViewById(R.id.contenido);
            imagen.setImageResource(n.getImageRestId());
            titulo.setText(n.getTitulo());
            contenido.setText(n.getContenido());
            Log.e("ERROR", String.valueOf(n.getTitulo()));
            contenedor.addView(raiz);
        }
    }

    private class NovedadesHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView titulo;
        TextView contenido;

        public NovedadesHolder(View itemView) { //item view es el layout a insertar
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            contenido = (TextView) itemView.findViewById(R.id.contenido);
        }
    }

    class NovedadAdapter extends RecyclerView.Adapter<NovedadesHolder> {

        @Override
        public NovedadesHolder onCreateViewHolder(ViewGroup parent, int viewType) {//parent es el contenedor
            // crear holder
            View raiz = LayoutInflater.from(MainActivity.this).inflate(R.layout.novedad, parent, false);
            return new NovedadesHolder(raiz);
        }


        @Override
        public void onBindViewHolder(NovedadesHolder holder, int position) {
            //se bindea los datos con la vista
            Novedad n = novedades.get(position);
            holder.imagen.setImageResource(n.getImageRestId());
            holder.titulo.setText(n.getTitulo());
            holder.contenido.setText(n.getContenido());
        }

        @Override
        public int getItemCount() {
            return novedades.size();
        } //cantidad de elementos a mostrar
    }

}
