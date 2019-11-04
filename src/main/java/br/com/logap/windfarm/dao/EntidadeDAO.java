package br.com.logap.windfarm.dao;

import java.util.List;

import br.com.logap.windfarm.model.Entidade;

public interface EntidadeDAO<T extends Entidade> {

	T buscarPorId(Integer id);
    T salvar(T entidade);
    void remover(T entidade);
    List<T> todos();
    T buscarPorNome(String nome);
    List<T> pesquisar(String termoPesquisa);
}
