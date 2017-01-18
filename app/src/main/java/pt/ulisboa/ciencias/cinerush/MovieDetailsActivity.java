package pt.ulisboa.ciencias.cinerush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pt.ulisboa.ciencias.cinerush.dados.EstreiaDescricao;
import pt.ulisboa.ciencias.cinerush.dados.FilmeBasico;
import pt.ulisboa.ciencias.cinerush.dados.FilmeDescricao;

public class MovieDetailsActivity extends AppCompatActivity {

    private String child;
    private DatabaseReference mFilmeReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mType;
    private String mFilmeId;

    private ImageView mPosterView;
    private TextView mTitleView;
    private TextView mOriginalTitleView;
    private TextView mPremierDateView;
    private TextView mGenreView;
    private TextView mDurationView;
    private TextView mDescriptionView;

    private TextView mPersonFunctionView;
    private TextView mPersonNameView;

    private TextView mTeathreNameView;
    private TextView mScheduleView;
    private TextView mPriceView;





    private RecyclerView mInterpretesRecycler;
    private RecyclerView mSessoesRecycler;

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

        mSessoesRecycler = (RecyclerView) findViewById(R.id.sessoes_recyclerview);
        mInterpretesRecycler = (RecyclerView) findViewById(R.id.interpretes_recyclerview);

        mSessoesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mInterpretesRecycler.setLayoutManager(new LinearLayoutManager(this));

        }

        @Override
        public void onStart() {
            super.onStart();

            // Add value event listener to the post
            // [START post_value_event_listener]
            ValueEventListener movieInfoListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("KEY:"+ dataSnapshot.getRef());
                    System.out.println("KEY:"+ dataSnapshot.getRef());



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
            // [END post_value_event_listener]

            // Keep copy of post listener so we can remove it when app stops
            mPostListener = movieInfoListener;

            // Listen for comments
//            mAdapter = new CommentAdapter(this, mCommentsReference);
//            mSessoesRecycler.setAdapter(mAdapter);
        }

}
