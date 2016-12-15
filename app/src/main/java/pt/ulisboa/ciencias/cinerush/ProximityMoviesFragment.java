package pt.ulisboa.ciencias.cinerush;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProximityMoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProximityMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProximityMoviesFragment extends MainMoviesFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProximityMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProximityMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProximityMoviesFragment newInstance(String param1, String param2) {
        ProximityMoviesFragment fragment = new ProximityMoviesFragment();
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
    protected ArrayList<String> getMovies() {

        String[] ttt = new String[]{"Filme 1", "Filme 2", "Filme 3"};

        ArrayList<String> filmes = new ArrayList<String>(Arrays.asList(ttt));
        /*filmes.add("Filme 1");
        filmes.add("Filme 2");
        filmes.add("Filme 3");
        filmes.add("Filme 4");
        filmes.add("Filme 5");
        filmes.add("Filme 6");
        filmes.add("Filme 7");
        filmes.add("Filme 8");
        filmes.add("Filme 9");
        filmes.add("Filme 10");
        filmes.add("Filme 11");
        filmes.add("Filme 12");*/
        return filmes;
    }
}
