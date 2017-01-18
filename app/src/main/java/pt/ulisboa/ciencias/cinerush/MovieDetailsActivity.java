package pt.ulisboa.ciencias.cinerush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MovieDetailsActivity extends AppCompatActivity {

    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;

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






    private RecyclerView mMoviesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_movie_details);

            // Get post key from intent
            mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
            if (mPostKey == null) {
                throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
            }

            // Initialize Database
            mPostReference = FirebaseDatabase.getInstance().getReference()
                    .child("posts").child(mPostKey);
            mCommentsReference = FirebaseDatabase.getInstance().getReference()
                    .child("post-comments").child(mPostKey);

            // Initialize Views
            mAuthorView = (TextView) findViewById(R.id.post_author);
            mTitleView = (TextView) findViewById(R.id.post_title);
            mBodyView = (TextView) findViewById(R.id.post_body);
            mCommentField = (EditText) findViewById(R.id.field_comment_text);
            mCommentButton = (Button) findViewById(R.id.button_post_comment);
            mMoviesRecycler = (RecyclerView) findViewById(R.id.recycler_comments);

            mCommentButton.setOnClickListener(this);
            mMoviesRecycler.setLayoutManager(new LinearLayoutManager(this));

        }

        @Override
        public void onStart() {
            super.onStart();

            // Add value event listener to the post
            // [START post_value_event_listener]
            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    Post post = dataSnapshot.getValue(Post.class);
                    // [START_EXCLUDE]
                    mAuthorView.setText(post.author);
                    mTitleView.setText(post.title);
                    mBodyView.setText(post.body);
                    // [END_EXCLUDE]
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    // [START_EXCLUDE]
                    Toast.makeText(PostDetailActivity.this, "Failed to load post.",
                            Toast.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }
            };
            mPostReference.addValueEventListener(postListener);
            // [END post_value_event_listener]

            // Keep copy of post listener so we can remove it when app stops
            mPostListener = postListener;

            // Listen for comments
            mAdapter = new CommentAdapter(this, mCommentsReference);
            mMoviesRecycler.setAdapter(mAdapter);
        }

}
