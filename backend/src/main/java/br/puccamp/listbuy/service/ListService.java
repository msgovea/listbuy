package br.puccamp.listbuy.service;

import br.puccamp.listbuy.dao.ListDAO;
import br.puccamp.listbuy.entities.Listas;

import java.util.List;

public class ListService {

    ListDAO listDAO = new ListDAO();

    public List<Listas> listarListasPorUsuario(Long idUsuario) {
        List<Listas> listas = listDAO.listarListasPorUsuario(idUsuario);
        if (listas == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return listas;
    }

    public void deletarLista(Long idLista) {
        listDAO.deleteList(idLista);
    }

    public Listas atualizarLista(Listas dados) {
        Listas lista = listDAO.atualizarLista(dados);
        if (lista == null) {
            throw new RuntimeException("Erro ao atualizar informações da lista!");
        }
        return lista;
    }

    public Listas inserirLista(Listas dados) {
        Listas lista = listDAO.inserirLista(dados);
        if (lista == null) {
            throw new RuntimeException("Erro ao inserir Lista");
        }
        if (dados.getTipo_lista().compareTo("P") == 0) {
            listDAO.inserirListaPessoal(lista);
        } else {
            listDAO.inserirListaEvento(lista);
        }
        return lista;
    }
}
