package br.edu.ufrpe.uag.projetao.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ufrpe.uag.projetao.abstracts.AbstractPaginator;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Israel Araújo
 * @param <T>
 */
public interface InterfaceController<T extends InterfaceEntity> {

    /**
     * Insere no item atual a chave composta
     */
    void setEmbeddableKeys();

    /**
     * Instancia uma nova chave composta e insere no item atual (getCurrent())
     */
    void initializeEmbeddableKey();

    /**
     *
     * @return Item atualmente ativo na seleção
     */
    T getSelected();

    /**
     *
     * @return Interface de maninulação do banco
     */
    InterfaceFacade<T> getFacade();

    /**
     *
     * @return Paginador de elementos, usado para navegar com 'n' ítens por
     * página
     */
    AbstractPaginator getPagination();

    /**
     * Prepara um lista de itens paginada
     *
     * @return Lista de itens
     */
    List<T> prepareList();

    /**
     * Prepara um item para ser visto
     *
     * @param index do item atual que se deseja retornar
     * @return Item listado no índice index
     */
    T prepareView(int index);

    /**
     * Prepara um novo item para ser criado
     *
     * @return Um novo item instanciado com os campos em branco
     */
    T prepareCreate();

    /**
     * Persiste um ítem no banco de dados
     *
     * @return Item atual (getCurrent()) que foi persistido no banco
     */
    T create();

    /**
     * Prepara a edição do item indicado
     *
     * @param index do item a ser editado
     * @return item editado a ser persistido no banco
     */
    T prepareEdit(int index);

    /**
     * Efetua atualizações de dados de um item
     *
     * @return Item atualizado no banco de dados
     */
    T update();

    /**
     * Destroi um item de índice index
     *
     * @param index do item a ser destruido
     * @return Item destruido
     */
    T destroy(int index);

    /**
     * Destroi um item de índice index e visualiza o proximo item da lista
     *
     * @param index do item a ser destruido
     * @return Item destruido
     */
    T destroyAndView(int index);

    /**
     * Efetiva no banco de dados a destruição de um item
     */
    void performDestroy();

    /**
     * Atualiza o item atual (getCurrent())
     */
    void updateCurrentItem();

    /**
     * Seleciona os item paginados
     *
     * @return 'n' itens paginados
     */
    List<T> getItems();

    /**
     * Recria o modelo de paginação
     */
    void recreateModel();

    /**
     * Recria os dados da paginação
     */
    void recreatePagination();

    /**
     *
     * @return a próxima lista de 'n' itens
     */
    List next();

    /**
     *
     * @return a lista de 'n' itens anteriores
     */
    List previous();

    /**
     *
     * @return a primeira lista de 'n' itens
     */
    List first();

    /**
     *
     * @return a última lista de 'n' itens
     */
    List last();

    /**
     *
     * @return lista todos os elementos selecionáveis do banco de dados
     */
    List<T> getItemsAvailableSelectMany();

    /**
     *
     * @return lista todos os elementos selecionáveis do banco de dados
     */
    List<T> getItemsAvailableSelectOne();

    /**
     * Busca no banco de dados um item de determinado id
     *
     * @param id do item a ser buscado
     * @return Item do banco de dados, caso não encontre retorna
     * <code>null</code>
     */
    T get(Serializable id);

}
