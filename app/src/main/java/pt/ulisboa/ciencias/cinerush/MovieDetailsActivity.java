package pt.ulisboa.ciencias.cinerush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import pt.ulisboa.ciencias.cinerush.dados.Cinema;
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

            // Sessoes------------------------------------------------------------------------------

            if ("filmes".equals(child.toLowerCase())){
                // New child entries
                mSessaoReference = FirebaseDatabase.getInstance().getReference();
                mFirebaseSessaoAdapter = new FirebaseRecyclerAdapter<Sessao,
                        SessaoViewHolder>(
                        Sessao.class,
                        R.layout.cardview_sessoes,
                        SessaoViewHolder.class,
                        mSessaoReference.child("Cartaz/Sessoes").child(mFilmeId)) {

                    @Override
                    protected void populateViewHolder(final SessaoViewHolder viewHolder,
                                                      final Sessao sessao, final int position) {
                        mSessaoProgressBar.setVisibility(ProgressBar.INVISIBLE);

                        String cinemaId = String.valueOf(sessao.getNumeroCinema());
                        viewHolder.mScheduleView.setText(sessao.getHorario());
                        viewHolder.mPriceView.setText(sessao.getPreco());





                        final DatabaseReference cinemaReference = FirebaseDatabase.getInstance().getReference()
                                .child("Cartaz/Cinemas/PorID").child(cinemaId);


                        ValueEventListener cinemaListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                // Get Post object and use the values to update the UI
                                Cinema cinema = dataSnapshot.getValue(Cinema.class);


                                viewHolder.mTheatreNameView.setText(cinema.getNome());
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Getting Post failed, log a message
                                Toast.makeText(MovieDetailsActivity.this,
                                        "Falha ao carregar o cinema.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        };

                        cinemaReference.addValueEventListener(cinemaListener);

                    }
                };


                mSessaoRecycler.setLayoutManager(mSessaoLinearLayoutManager);
                mSessaoRecycler.setAdapter(mFirebaseSessaoAdapter);
            }
            else{
                mSessaoProgressBar.setVisibility(ProgressBar.INVISIBLE);
                mSessaoRecycler.setVisibility(RecyclerView.INVISIBLE);
                TextView tagSessoes = (TextView) findViewById(R.id.tag_sessoes);
                tagSessoes.setVisibility(TextView.INVISIBLE);
            }
            //--------------------------------------------------------------------------------------

            // Interpretes--------------------------------------------------------------------------

            // New child entries
            mInterpreteReference = FirebaseDatabase.getInstance().getReference();
            mFirebaseInterpreteAdapter = new FirebaseRecyclerAdapter<Interprete,
                    InterpreteViewHolder>(
                    Interprete.class,
                    R.layout.cardview_interpretes,
                    InterpreteViewHolder.class,
                    //alterar isto em conformidade
                    mInterpreteReference.child("Cartaz/Filmes").child(mFilmeId).child("interpretesInfo")) {

                @Override
                protected void populateViewHolder(InterpreteViewHolder viewHolder,
                                                  Interprete interprete, final int position) {
                    mInterpreteProgressBar.setVisibility(ProgressBar.INVISIBLE);

                    viewHolder.mPersonFunctionView.setText(interprete.getFuncao());
                    viewHolder.mPersonNameView.setText(interprete.getNome());

                }
            };

            mInterpreteRecycler.setLayoutManager(mInterpreteLinearLayoutManager);
            mInterpreteRecycler.setAdapter(mFirebaseInterpreteAdapter);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.individual_mode) {
            Intent intent = new Intent(MovieDetailsActivity.this, IndividualModeActivity.class);
            Bundle extras = getIntent().getExtras();
            extras.putString("value", mFilmeId);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.group_mode) {
            Intent intent = new Intent(MovieDetailsActivity.this, GroupModeActivity.class);
            Bundle extras = new Bundle();
            extras.putString("value", mFilmeId);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
