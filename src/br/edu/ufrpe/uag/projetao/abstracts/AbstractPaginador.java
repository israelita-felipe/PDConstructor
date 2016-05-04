/**
 * 
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.util.List;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * @author israel
 *
 */
public abstract class AbstractPaginador<L extends InterfaceEntity, T extends InterfaceEntity, C extends InterfaceEntity, A extends InterfaceEntity> {

    private enum Status {
	EDITANDO, INSERINDO
    }

    private int indice;
    private Status status;
    private InterfaceController<T> controller;
    private List<T> listadeClassificacao;
    private List<A> listaDeAlocacao;
    private List<C> listaDeClasses;
    private L liberacao;

    /**
     * Classe de paginação para classificação de bases
     * 
     * @param controller
     *            controlador de dados de classificação
     * @param liberacao
     *            liberação da base para o usuário em questão
     * @param listaDeClasses
     *            lista de possíveis classes que o usuário pode escolher
     * @param listaDeAlocacao
     *            alocação de mídias para a base, oque será utilizando para
     *            preenchimento dos campos da view
     * @param listaDeClassificacao
     *            classificações previamente feitas pelo usuário, geralmente já
     *            estão persistidas no banco de dados
     */
    public AbstractPaginador(InterfaceController<T> controller, L liberacao, List<C> listaDeClasses,
	    List<A> listaDeAlocacao, List<T> listaDeClassificacao) {
	this.controller = controller;
	this.liberacao = liberacao;
	this.listaDeClasses = listaDeClasses;
	this.listaDeAlocacao = listaDeAlocacao;
	this.listadeClassificacao = listaDeClassificacao;
	preencheVazio();
	this.indice = buscaPrimeiraClassificacaoEfetivada();
    }

    /**
     * Busca o primeiro elemento não classificado pelo escravo
     * 
     * @param classificacoes
     *            lista de classificações
     * @return índice do primeiro elemento sem classificação
     */
    public abstract int buscaPrimeiraClassificacaoEfetivada();

    public T getItemAtual() {
	return controller.prepareView(indice);
    }

    public A getAlocacaoAtual() {
	return listaDeAlocacao.get(indice);
    }

    public void setEditando() {
	this.status = Status.EDITANDO;
    }

    public void setInserindo() {
	this.status = Status.INSERINDO;
    }

    public Status getStatus() {
	return this.status;
    }

    public void criar() {
	this.controller.create();
    }

    public void atualizar() {
	this.controller.update();
    }

    public void gravaAlteracoes() {
	TransactionManager.begin();
	if (this.status.equals(Status.EDITANDO)) {
	    atualizar();
	} else {
	    criar();
	}
	TransactionManager.end();
    }

    /**
     * Seleciona o índice anterior a ser processado pelo paginador
     * 
     * @return o elemento anterior
     * @throws IllegalArgumentException
     *             caso seja o primeiro elemento é lançado a exceção
     */
    public T proximo() throws IllegalArgumentException {
	this.indice++;
	if (indice >= getListaDeClassificacao().size()) {
	    this.indice--;
	    throw new IllegalArgumentException("Último índice");
	}
	return getItemAtual();
    }

    /**
     * Seleciona o próximo índice a ser processado pelo paginador
     * 
     * @return o próximo elemento
     * @throws IllegalArgumentException
     *             caso seja o último elemento é lançado a exceção
     */
    public T anterior() throws IllegalArgumentException {
	this.indice--;
	if (indice < 0) {
	    this.indice++;
	    throw new IllegalArgumentException("Primeiro índice");
	}
	return getItemAtual();
    }

    public List<T> getListaDeClassificacao() {
	return this.listadeClassificacao;
    }

    public List<C> getListaDeClasses() {
	return this.listaDeClasses;
    }

    public List<A> getListaDeAlocacoes() {
	return this.listaDeAlocacao;
    }

    public InterfaceController<T> getController() {
	return this.controller;
    }

    public L getLiberacao() {
	return this.liberacao;
    }

    /**
     * Preenche as classificações que ainda não foram inseridas no banco para
     * que elas sejam inicializadas corretamente
     */
    public abstract void preencheVazio();
}
