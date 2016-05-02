/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.view;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ufrpe.uag.projetao.annotations.Coluna;
import br.edu.ufrpe.uag.projetao.annotations.Tabela;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * Classe que gera tabela Dinamica apartir de Reflection e Annotation. Adaptada
 * por Israel Araújo em 11-04-2016
 *
 * @author Marcos Vinicius Scholl
 * @author Israel Araújo
 * @version 1.01
 * @param <T>
 */
public class GenericTableModel<T extends InterfaceEntity> extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<T> list;
    private Class<T> clazz;
    private String fieldName;

    public GenericTableModel(List<T> lista) {
	super();
	if (lista != null) {
	    this.list = lista;
	    if (!list.isEmpty()) {
		this.clazz = (Class<T>) list.get(0).getClass();
	    }
	} else {
	    lista = new LinkedList<>();
	}
    }

    public void clear() {
	while (!list.isEmpty()) {
	    deleteItem(0);
	}
    }

    public void addAll(Collection<T> collection) {
	for (T element : collection) {
	    addElement(element);
	}
    }

    public void addElement(T element) {
	if (clazz == null) {
	    this.clazz = (Class<T>) element.getClass();
	}
	addItem();
	this.list.add(element);
    }

    /**
     * Retorna o n�mero de colunas no modelo. A JTable usa esse m�todo para
     * determinar quantas colunas ele deve criar e exibir por padr�o.
     *
     * @return int - O n�mero de colunas no modelo
     */
    @Override
    public int getColumnCount() {
	int colunas = 0;
	if (clazz != null) {
	    for (Field field : clazz.getDeclaredFields()) {
		if (field.isAnnotationPresent(Coluna.class)) {
		    colunas++;
		}
	    }
	}
	return colunas;
    }

    /**
     * Retorna o n�mero de linhas no modelo. A JTable usa esse m�todo para
     * determinar quantas linhas ele deve exibir. Este m�todo deve ser r�pido,
     * como � chamado com freq��ncia durante o processamento.
     *
     * @return int - O n�mero de linhas no modelo
     */
    @Override
    public int getRowCount() {
	return list.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	try {
	    int beforePosition = -1, nowPosition = -1, newPosition = -1;
	    Object object = list.get(rowIndex);

	    for (Field field : clazz.getDeclaredFields()) {
		Coluna c = field.getAnnotation(Coluna.class);

		field.setAccessible(true);

		if (field.isAnnotationPresent(Coluna.class)) {
		    nowPosition = c.colunaPosicao();

		    if (beforePosition == nowPosition) {
			newPosition++;
			nowPosition = newPosition;
		    }

		    if (c != null && nowPosition == columnIndex) {
			if (field.getName().equalsIgnoreCase(field.getName())) {

			    return field.get(object);

			}
		    }
		}

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * Retorna o nome da coluna no columnIndex . Este � usado para inicializar o
     * nome do cabe�alho da coluna da tabela. Nota: este nome n�o precisa ser
     * exclusivo, duas colunas de uma tabela pode ter o mesmo nome.
     *
     * @param column
     *            int - o �ndice de coluna
     * @return String - O nome da coluna.
     */
    @Override
    public String getColumnName(int column) {
	try {
	    int beforePosition = -1, nowPosition = -1, newPosition = -1;

	    for (Field field : clazz.getDeclaredFields()) {
		Coluna c = field.getAnnotation(Coluna.class);

		if (field.isAnnotationPresent(Coluna.class)) {
		    nowPosition = c.colunaPosicao();

		    if (beforePosition == nowPosition) {
			newPosition++;
			nowPosition = newPosition;
		    }

		    if (c != null && nowPosition == column) {
			if (field.getName().equalsIgnoreCase(field.getName())) {
			    return c.colunaNome();
			}
		    }
		}

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "";
    }

    /**
     * Define o valor da c�lula em columnIndex e rowIndex para value .
     *
     * @param value
     *            Object- O novo valor.
     * @param rowIndex
     *            int - A linha cujo valor deve ser consultado.
     * @param columnIndex
     *            int - A coluna cujo valor deve ser consultado.
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
	int pos = -1;
	try {
	    Object object = list.get(rowIndex);
	    proximo: for (Field field : clazz.getDeclaredFields()) {
		Coluna c = field.getAnnotation(Coluna.class);
		Tabela tm = field.getAnnotation(Tabela.class);

		if ((c != null || tm != null || ++pos == columnIndex)) {
		    for (Method method : clazz.getDeclaredMethods()) {
			if (method.getName().equalsIgnoreCase("set" + field.getName())) {
			    method.invoke(object, value);
			    continue proximo;
			}
		    }
		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public int indexOf(T element) {
	return list.indexOf(element);
    }

    public void addItem() {
	int line = list.size() - 1;
	fireTableRowsInserted(line, line);
    }

    public T loadItem(int row) {
	return list.get(row);
    }

    public void deleteItem(int row) {
	list.remove(row);
	fireTableRowsDeleted(row, row);
    }

    public String getName() {
	return fieldName;
    }
}
