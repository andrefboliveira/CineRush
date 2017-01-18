package pt.ulisboa.ciencias.cinerush;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.bumptech.glide.Glide;


import pt.ulisboa.ciencias.cinerush.dados.FilmeBasico;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentMoviesFragment} interface
 * to handle interaction events.
 * Use the {@link CurrentMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentMoviesFragment extends MainMoviesFragment {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<FilmeBasico, MovieViewHolder> mFirebaseAdapter;

    private ProgressBar mProgressBar;
    private RecyclerView mMovieRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CurrentMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentMoviesFragment newInstance(String param1, String param2) {
        CurrentMoviesFragment fragment = new CurrentMoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mMovieRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mMovieRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMovieRecyclerView.setAdapter(mFirebaseAdapter);

        return view;
    }
}
