package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.Departamento;
import com.example.demo.util.PaginacaoUtil;

public interface DepartamentoDao {

	void save(Departamento departamento);

	void update(Departamento departamento);

	void delete(Long id);

	Departamento findById(Long id);

	List<Departamento> findAll();

	PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao);
}
