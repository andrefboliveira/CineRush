package pt.ulisboa.ciencias.cinerush;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static java.security.AccessController.getContext;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMoviesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMoviesFragment newInstance(String param1, String param2) {
        MainMoviesFragment fragment = new MainMoviesFragment();
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

        //LISTA DE FILMES
        ListView movieList = (ListView) getActivity().findViewById(R.id.movie_list);
        ArrayList<String> movies = getMovies();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, movies);
        movieList.setAdapter(aa);
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
//                        goToMovieDescription(view);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private ArrayList<String> getMovies(){

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_movies, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
