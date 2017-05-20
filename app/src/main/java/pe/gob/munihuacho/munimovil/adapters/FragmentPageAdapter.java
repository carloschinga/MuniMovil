package pe.gob.munihuacho.munimovil.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alexisholyoak on 22/04/2017.
 */

public class FragmentPageAdapter extends FragmentPagerAdapter {
    private String[] nTabTitle;
    public FragmentPageAdapter(FragmentManager fm,String[] mTabTitle) {
        super(fm);
        this.nTabTitle=mTabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
