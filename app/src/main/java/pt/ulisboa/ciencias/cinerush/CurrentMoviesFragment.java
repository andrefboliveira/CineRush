package pt.ulisboa.ciencias.cinerush;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.google.firebase.database.Query;


import java.util.ArrayList;

import pt.ulisboa.ciencias.cinerush.dados.FilmeBasico;

import static android.R.attr.mode;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentMoviesFragment} interface
 * to handle interaction events.
 * Use the {@link CurrentMoviesFragment} factory method to
 * create an instance of this fragment.
 */
public class CurrentMoviesFragment extends MainMoviesFragment {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<FilmeBasico, MovieViewHolder> mFirebaseAdapter;

    private ProgressBar mProgressBar;
    private RecyclerView mMovieRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    public CurrentMoviesFragment() {
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
        mFirebaseAdapter = new FirebaseRecyclerAdapter<FilmeBasico,
                MovieViewHolder>(
                FilmeBasico.class,
                R.layout.cardview_main_movies,
                MovieViewHolder.class,
                mFirebaseDatabaseReference.child("Cartaz/Filmes")) {

            @Override
            protected void populateViewHolder(MovieViewHolder viewHolder,
                                              FilmeBasico filmeBasico, final int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);

                Uri image_uri = filmeBasico.getImagem();

                Glide.with(getContext())
                        .load(image_uri)
                        .into(viewHolder.imagem_filme_imageview);

                String titulo_pt = filmeBasico.getTitulo();
                viewHolder.titulo_pt_textview.setText(titulo_pt);

                String titulo_original = filmeBasico.getTituloOriginal();
                if (!titulo_pt.equals(titulo_original)){
                    viewHolder.titulo_original_textview.setText(titulo_original);
                }

                viewHolder.genero_textview.setText(filmeBasico.getGenero());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String movieNumber = mFirebaseAdapter.getRef(position).getKey();
                        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("type", "Filme");
                        extras.putString("value", movieNumber);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });

                viewHolder.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        // TODO Auto-generated method stub
                        return true;
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
    public static CurrentMoviesFragment newInstance() {
        CurrentMoviesFragment fragment = new CurrentMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.individual_mode:
                Intent intent1 = new Intent(getContext(), IndividualModeActivity.class);

                startActivity(intent1);
//                        finish();
                return true;
            case R.id.group_mode:
                Intent intent2 = new Intent(getContext(), GroupModeActivity.class);

                startActivity(intent2);
//                        finish();
                return true;
            default:
                return false;
        }
    }
}
