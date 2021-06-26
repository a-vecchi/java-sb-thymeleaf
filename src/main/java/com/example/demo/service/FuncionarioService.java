package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.Funcionario;
import com.example.demo.util.PaginacaoUtil;

public interface FuncionarioService {

	void salvar(Funcionario funcionario);

	void editar(Funcionario funcionario);

	void excluir(Long id);

	Funcionario buscarPorId(Long id);

	PaginacaoUtil<Funcionario> buscarPorPagina(int pagina, String direcao);

	List<Funcionario> buscarTodos();

	List<Funcionario> buscarPorNome(String nome);

	List<Funcionario> buscarPorCargo(Long id);

	List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida);
}
