package pt.ulisboa.ciencias.cinerush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pt.ulisboa.ciencias.cinerush.dados.EstreiaDescricao;
import pt.ulisboa.ciencias.cinerush.dados.FilmeDescricao;
import pt.ulisboa.ciencias.cinerush.dados.Interprete;
import pt.ulisboa.ciencias.cinerush.dados.Sessao;

public class MovieDetailsActivity extends AppCompatActivity {

    private String child;
    private DatabaseReference mFilmeReference;
    private ValueEventListener movieInfoListener;
    private String mType;
    private String mFilmeId;

    private ImageView mPosterView;
    private TextView mTitleView;
    private TextView mOriginalTitleView;
    private TextView mPremierDateView;
    private TextView mGenreView;
    private TextView mDurationView;
    private TextView mDescriptionView;

    private RecyclerView mInterpreteRecycler;
    private RecyclerView mSessaoRecycler;

    private LinearLayoutManager mInterpreteLinearLayoutManager;
    private LinearLayoutManager mSessaoLinearLayoutManager;


    private DatabaseReference mInterpreteReference;
    private DatabaseReference mSessaoReference;

    private FirebaseRecyclerAdapter<Interprete, InterpreteViewHolder> mFirebaseInterpreteAdapter;
    private FirebaseRecyclerAdapter<Sessao, SessaoViewHolder> mFirebaseSessaoAdapter;


    private ProgressBar mInterpreteProgressBar;
    private ProgressBar mSessaoProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get post key from intent
        mType = getIntent().getStringExtra("type");
        if ("filme".equals(mType.toLowerCase())) {
            child = "Filmes";
        }
        else if("estreia".equals(mType.toLowerCase())){
            child = "Estreias";
        }
        mFilmeId = getIntent().getStringExtra("value");


        // Initialize Database
        mFilmeReference = FirebaseDatabase.getInstance().getReference()
                .child("Cartaz").child(child).child(mFilmeId);

        // Initialize Views
        mTitleView = (TextView) findViewById(R.id.titulo);
        mPosterView = (ImageView) findViewById(R.id.movieImage);
        mOriginalTitleView = (TextView) findViewById(R.id.tituloOriginal);
        mPremierDateView = (TextView) findViewById(R.id.estreia);
        mGenreView = (TextView) findViewById(R.id.genero);
        mDurationView = (TextView) findViewById(R.id.duracao);
        mDescriptionView = (TextView) findViewById(R.id.descricao);

        mSessaoRecycler = (RecyclerView) findViewById(R.id.sessoes_recyclerview);
        mInterpreteRecycler = (RecyclerView) findViewById(R.id.interpretes_recyclerview);

        mInterpreteProgressBar = (ProgressBar) findViewById(R.id.progressBarInterpretes);
        mInterpreteRecycler = (RecyclerView) findViewById(R.id.interpretes_recyclerview);
        mInterpreteLinearLayoutManager = new LinearLayoutManager(MovieDetailsActivity.this);
        mInterpreteLinearLayoutManager.setStackFromEnd(true);

        mSessaoProgressBar = (ProgressBar) findViewById(R.id.progressBarSessoes);
        mSessaoRecycler = (RecyclerView) findViewById(R.id.sessoes_recyclerview);
        mSessaoLinearLayoutManager = new LinearLayoutManager(MovieDetailsActivity.this);
        mSessaoLinearLayoutManager.setStackFromEnd(true);
        }

        @Override
        public void onStart() {
            super.onStart();

            // Add value event listener to the post
            // [START post_value_event_listener]
            movieInfoListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Get Post object and use the values to update the UI
                    if(child.equals("Filmes")) {
                        FilmeDescricao detalhes = dataSnapshot.getValue(FilmeDescricao.class);
                        mTitleView.setText(detalhes.getTitulo());
                        mOriginalTitleView.setText(detalhes.getTituloOriginal());
                        mDescriptionView.setText(detalhes.getDescricao());
                        mDurationView.setText(detalhes.getDuracao());
                        mGenreView.setText(detalhes.getGenero());
                        Glide.with(MovieDetailsActivity.this)
                                .load(detalhes.getImagem())
                                .into(mPosterView);
                    }
                    else if(child.equals("Estreias")) {
                        EstreiaDescricao detalhes = dataSnapshot.getValue(EstreiaDescricao.class);
                        mTitleView.setText(detalhes.getTitulo());
                        mOriginalTitleView.setText(detalhes.getTituloOriginal());
                        mDescriptionView.setText(detalhes.getDescricao());
                        mDurationView.setText(detalhes.getDuracao());
                        mPremierDateView.setText(detalhes.getEstreia());
                        mGenreView.setText(detalhes.getGenero());
                        Glide.with(MovieDetailsActivity.this)
                                .load(detalhes.getImagem())
                                .into(mPosterView);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Toast.makeText(MovieDetailsActivity.this,
                            "Falha ao carregar informações adicionais.",
                            Toast.LENGTH_SHORT).show();
                }
            };
            mFilmeReference.addValueEventListener(movieInfoListener);


            // Popular o recycler views

            // Sessoes

            // New child entries
            mSessaoReference = FirebaseDatabase.getInstance().getReference();
            mFirebaseSessaoAdapter = new FirebaseRecyclerAdapter<Sessao,
                    SessaoViewHolder>(
                    Sessao.class,
                    R.layout.cardview_sessoes,
                    SessaoViewHolder.class,
                    mSessaoReference.child("Cartaz/Sessoes").child(mFilmeId)) {

                @Override
                protected void populateViewHolder(SessaoViewHolder viewHolder,
                                                  Sessao sessao, final int position) {
                    mSessaoProgressBar.setVisibility(ProgressBar.INVISIBLE);

                    //VAI DAR MERDA
                    viewHolder.mTheatreNameView.setText(sessao.getCinema().getNome());

                    viewHolder.mScheduleView.setText(sessao.getHorario());

                    viewHolder.mPriceView.setText(sessao.getPreco());
                }
            };

            mFirebaseSessaoAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    int friendlyMessageCount = mFirebaseSessaoAdapter.getItemCount();
                    int lastVisiblePosition =
                            mSessaoLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                    // If the recycler view is initially being loaded or the
                    // user is at the bottom of the list, scroll to the bottom
                    // of the list to show the newly added message.
                    if (lastVisiblePosition == -1 ||
                            (positionStart >= (friendlyMessageCount - 1) &&
                                    lastVisiblePosition == (positionStart - 1))) {
                        mSessaoRecycler.scrollToPosition(positionStart);
                    }
                }
            });

            mSessaoRecycler.setLayoutManager(mSessaoLinearLayoutManager);
            mSessaoRecycler.setAdapter(mFirebaseSessaoAdapter);

        }

    public static class InterpreteViewHolder extends RecyclerView.ViewHolder {
        View mView;

        private TextView mPersonFunctionView;
        private TextView mPersonNameView;

        public InterpreteViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mPersonFunctionView = (TextView)itemView.findViewById(R.id.interprete_funcao);
            mPersonNameView = (TextView)itemView.findViewById(R.id.interprete_nome);
        }
    }

    public static class SessaoViewHolder extends RecyclerView.ViewHolder {
        View mView;

        private TextView mTheatreNameView;
        private TextView mScheduleView;
        private TextView mPriceView;

        public SessaoViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mTheatreNameView = (TextView)itemView.findViewById(R.id.nome_cinema);;
            mScheduleView = (TextView)itemView.findViewById(R.id.horario);
            mPriceView = (TextView)itemView.findViewById(R.id.preco);
        }
    }
}
