/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.principal.util;

import org.kairos.core.Fragment;

/**
 * @author israel
 *
 */
public class Tab {

    private String title;
    private Fragment fragment;

    public Tab(String title, Fragment fragment) {
	this.title = title;
	this.fragment = fragment;
    }

    /**
     * @return the fragment
     */
    public Fragment getFragment() {
	return fragment;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param fragment
     *            the fragment to set
     */
    public void setFragment(Fragment fragment) {
	this.fragment = fragment;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

}