package pt.ulisboa.ciencias.cinerush;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pt.ulisboa.ciencias.cinerush.dados.Estreia;
import pt.ulisboa.ciencias.cinerush.dados.Estreia;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PremierMoviesFragment} interface
 * to handle interaction events.
 * Use the {@link PremierMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PremierMoviesFragment extends MainMoviesFragment {

    private DatabaseReference mFirebaseDatabaseReference;
    private com.firebase.ui.database.FirebaseRecyclerAdapter<Estreia, MovieViewHolder> mFirebaseAdapter;

    private ProgressBar mProgressBar;
    private RecyclerView mMovieRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    public PremierMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mMovieRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setStackFromEnd(true);

        // New child entries
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new com.firebase.ui.database.FirebaseRecyclerAdapter<Estreia,
                MovieViewHolder>(
                Estreia.class,
                R.layout.cardview_main_movies,
                MovieViewHolder.class,
                mFirebaseDatabaseReference.child("Cartaz/Estreias")) {

            @Override
            protected void populateViewHolder(MovieViewHolder viewHolder,
                                              Estreia estreia, final int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);

                Uri image_uri = estreia.getImagem();

                Glide.with(getContext())
                        .load(image_uri)
                        .into(viewHolder.imagem_filme_imageview);

                String titulo_pt = estreia.getTitulo();
                viewHolder.titulo_pt_textview.setText(titulo_pt);

                String titulo_original = estreia.getTituloOriginal();
                if (!titulo_pt.equals(titulo_original)){
                    viewHolder.titulo_original_textview.setText(titulo_original);
                }

                viewHolder.genero_textview.setText(estreia.getGenero());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String movieNumber = mFirebaseAdapter.getRef(position).getKey();
                        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("type", "Estreia");
                        extras.putString("value", movieNumber);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });

            }
        };

//
        mMovieRecyclerView.setHasFixedSize(true);//talvez remover
        mMovieRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMovieRecyclerView.setAdapter(mFirebaseAdapter);

        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PremierMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PremierMoviesFragment newInstance() {
        PremierMoviesFragment fragment = new PremierMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

}
