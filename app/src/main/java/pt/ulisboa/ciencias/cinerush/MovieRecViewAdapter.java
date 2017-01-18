package pt.ulisboa.ciencias.cinerush;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.List;

import pt.ulisboa.ciencias.cinerush.dados.FilmeBasico;

public class MovieRecViewAdapter extends RecyclerView.Adapter<MovieRecViewAdapter.MovieViewHolder> {
    /*
    public List<? extends FilmeBasico> moviesList;


    public MovieRecViewAdapter(List<? extends FilmeBasico> moviesList){
        this.moviesList = moviesList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MovieViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Uri image_uri = moviesList.get(position).getImagem();

        Glide.with()
                .load(image_uri)
                .into(holder.imagem_filme_imageview);

        String titulo_pt = moviesList.get(position).getTitulo();
        holder.titulo_pt_textview.setText(titulo_pt);

        String titulo_original = moviesList.get(position).getTituloOriginal();
        if (!titulo_pt.equals(titulo_original)){
            holder.titulo_original_textview.setText(titulo_original);
        }

        holder.genero_textview.setText(moviesList.get(position).getGenero());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
    */

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imagem_filme_imageview;
        TextView titulo_pt_textview;
        TextView titulo_original_textview;
        TextView genero_textview;
        public MovieViewHolder(View itemView) {
            super(itemView);
            imagem_filme_imageview =  (ImageView) itemView.findViewById(R.id.movie_image);
            titulo_pt_textview  = (TextView)itemView.findViewById(R.id.movie_title_pt);
            titulo_original_textview  = (TextView)itemView.findViewById(R.id.movie_title_original);
            genero_textview  = (TextView)itemView.findViewById(R.id.movie_genre);
        }
    }

}