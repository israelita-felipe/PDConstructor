/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.principal.util;

import java.util.ArrayList;

import org.kairos.FragmentStatePagerAdapter;
import org.kairos.core.Fragment;
import org.kairos.core.FragmentManager;

/**
 * @author israel
 *
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Tab> tabs;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
	super(fragmentManager);
	tabs = new ArrayList<>();
    }

    public void addTab(Tab tab) {
	tabs.add(tab);
    }

    @Override
    public int getCount() {
	return tabs.size();
    }

    @Override
    public Fragment getItem(int i) {
	return tabs.get(i).getFragment();
    }

    @Override
    public String getPageTitle(int i) {
	return tabs.get(i).getTitle();
    }

}