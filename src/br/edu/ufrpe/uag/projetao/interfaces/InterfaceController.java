package br.edu.ufrpe.uag.projetao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author Israel Araújo
 * @param <T>
 */
public interface InterfaceController<T extends InterfaceEntity> {

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
     * Prepara um lista de itens paginada
     *
     * @return Lista de itens
     */
    List<T> prepareList();

    /**
     * Prepara um item para ser visto
     *
     * @param index
     *            do item atual que se deseja retornar
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
     * @param index
     *            do item a ser editado
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
     * @param index
     *            do item a ser destruido
     * @return Item destruido
     */
    T destroy(int index);

    /**
     * Destroi um item de índice index e visualiza o proximo item da lista
     *
     * @param index
     *            do item a ser destruido
     * @return Item destruido
     */
    T destroyAndView(int index);

    /**
     * Efetiva no banco de dados a destruição de um item
     */
    void performDestroy();

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
     * Seleciona o elemento posterior e seleciona como o idem atual
     * 
     * @return índice do elemento na lista de itens
     */
    int next() throws IllegalArgumentException;

    /**
     * Seleciona o elemento anterior e seleciona como o idem atual
     * 
     * @return índice do elemento na lista de itens
     */
    int previous() throws IllegalArgumentException;

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
     * @param criteria
     *            de consulta
     * @return lista de elementos referentes a uma consulta
     */
    List<T> getItemsFromCriteria(DetachedCriteria criteria);

    /**
     * Busca no banco de dados um item de determinado id
     *
     * @param id
     *            do item a ser buscado
     * @return Item do banco de dados, caso não encontre retorna
     *         <code>null</code>
     */
    T get(Serializable id);

}
