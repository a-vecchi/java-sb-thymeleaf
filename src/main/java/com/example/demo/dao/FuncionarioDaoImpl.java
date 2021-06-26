package com.example.demo.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Funcionario;
import com.example.demo.util.PaginacaoUtil;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	public List<Funcionario> findByNome(String nome) {

		return createQuery("select f from Funcionario f where f.nome like concat('%',?1,'%') ", nome);
	}

	public PaginacaoUtil<Funcionario> buscaPaginada(int pagina, String direcao) {
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10
		List<Funcionario> funcionarios = getEntityManager()
				.createQuery("select c from Funcionario c order by c.nome " + direcao, Funcionario.class)
				.setFirstResult(inicio).setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, funcionarios);
	}

	public long count() {
		return getEntityManager().createQuery("select count(*) from Funcionario", Long.class).getSingleResult();
	}

	@Override
	public List<Funcionario> findByCargoId(Long id) {

		return createQuery("select f from Funcionario f where f.cargo.id = ?1", id);
	}

	@Override
	public List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
		String jpql = new StringBuilder("select f from Funcionario f ")
				.append("where f.dataEntrada >= ?1 and f.dataSaida <= ?2 ").append("order by f.dataEntrada asc")
				.toString();
		return createQuery(jpql, entrada, saida);
	}

	@Override
	public List<Funcionario> findByDataEntrada(LocalDate entrada) {
		String jpql = new StringBuilder("select f from Funcionario f ").append("where f.dataEntrada = ?1 ")
				.append("order by f.dataEntrada asc").toString();
		return createQuery(jpql, entrada);
	}

	@Override
	public List<Funcionario> findByDataSaida(LocalDate saida) {
		String jpql = new StringBuilder("select f from Funcionario f ").append("where f.dataSaida = ?1 ")
				.append("order by f.dataEntrada asc").toString();
		return createQuery(jpql, saida);
	}

}
