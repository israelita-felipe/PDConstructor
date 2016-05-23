/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Juan Augusto
 *
 */
public class ImagemFileFilter extends FileFilter {

	private String extensions[] = { "jpeg", "jpg", "png", "bmp" };

	/**
	 * 
	 */
	public ImagemFileFilter() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		} else {
			String path = f.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "*.imagens";
	}

}
