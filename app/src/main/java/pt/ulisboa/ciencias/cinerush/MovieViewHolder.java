package pt.ulisboa.ciencias.cinerush;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asus on 18/01/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView imagem_filme_imageview;
        TextView titulo_pt_textview;
        TextView titulo_original_textview;
        TextView genero_textview;
        public MovieViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            imagem_filme_imageview =  (ImageView) itemView.findViewById(R.id.movie_image);
            titulo_pt_textview  = (TextView)itemView.findViewById(R.id.movie_title_pt);
            titulo_original_textview  = (TextView)itemView.findViewById(R.id.movie_title_original);
            genero_textview  = (TextView)itemView.findViewById(R.id.movie_genre);
        }
}
