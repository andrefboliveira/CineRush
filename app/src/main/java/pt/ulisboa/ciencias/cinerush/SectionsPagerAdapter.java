package pt.ulisboa.ciencias.cinerush;

/**
 * Created by andre on 10/11/2016.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    protected Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }



    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.

//        switch(position) {
//            case 0:
//                return new ProximityMoviesFragment();
//            case 1:
//                return new CurrentMoviesFragment();
//            case 2:
//                return new PremierMoviesFragment();
//            default:
//                return null;
//        }
//
        return new CurrentMoviesFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();

        switch (position) {
            case 0:
                return "Proximidades";
            case 1:
                return "Em Cartaz";
            case 2:
                return "Estreias";
            default:
                return null;
       }
    }
}