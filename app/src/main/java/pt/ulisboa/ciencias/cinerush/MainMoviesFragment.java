package pt.ulisboa.ciencias.cinerush;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pt.ulisboa.ciencias.cinerush.dados.FilmeBasico;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * { MainMoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMoviesFragment#} factory method to
 * create an instance of this fragment.
 */
public abstract class MainMoviesFragment extends Fragment {


    //Selected movies
    private SparseBooleanArray selectedMovies;

//    public MainMoviesFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MainMoviesFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MainMoviesFragment newInstance(String param1, String param2) {
//        MainMoviesFragment fragment = new MainMoviesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_movies, container, false);
        //LISTA DE FILMES

//        MovieRecViewAdapter adapter = new MovieRecViewAdapter(moviesList);
//        RecyclerView myView =  (RecyclerView) view.findViewById(R.id.recyclerview);
//        myView.setHasFixedSize(true);
//        myView.setAdapter(adapter);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        myView.setLayoutManager(llm);

        /*
        final ListView movieList = (ListView) view.findViewById(R.id.movie_list);
        ArrayList<String> movies = getMovies();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_activated_1, movies);
        movieList.setAdapter(aa);
        movieList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        movieList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
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
                    case R.id.action_settings:
//                        selectedMovies = movieList.getCheckedItemPositions();
                        //   deleteSelectedItems();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.long_press, menu);
                //getActivity().getActionBar().hide();
                return true;
            }


            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
                //getActivity().getActionBar().show();
            }


            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    default:
                        break;
                }
            }
        });
        */
        return view;
    }

    public SparseBooleanArray getSelectedMovies(){
        return selectedMovies;
    }
}
