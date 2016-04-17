/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.control.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 *
 * @author israel
 */
public class XMLUtil {

	/**
	 *
	 * @param object
	 * @param path
	 * @throws java.io.FileNotFoundException
	 */
	public static void toXML(Object object, String path) throws FileNotFoundException {
		XStream stream = new XStream(new StaxDriver());		
		stream.toXML(object, new FileOutputStream(path));
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws java.io.FileNotFoundException
	 */
	public static Object fromXML(String path) throws FileNotFoundException {
		XStream stream = new XStream(new StaxDriver());		
		return stream.fromXML(new File(path));
	}
}
