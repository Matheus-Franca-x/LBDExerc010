package com.fatec.LBDSpring010.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.fatec.LBDSpring010.model.Produto;
import com.fatec.LBDSpring010.persistence.GenericDao;
import com.fatec.LBDSpring010.persistence.ProdutoDao;

@Controller
public class ProdutoController 
{
	
//	@Autowired
//	GenericDao gDao;
//	
//	@Autowired
//	ProdutoDao pDao;
	
	public String cadastrar(Produto p) throws SQLException, ClassNotFoundException 
	{
		GenericDao gDao = new GenericDao();
		ProdutoDao pDao = new ProdutoDao(gDao);
		String saida = pDao.crud("I", p);
		return saida;
		
	}

	public String alterar(Produto p) throws SQLException, ClassNotFoundException 
	{
		GenericDao gDao = new GenericDao();
		ProdutoDao pDao = new ProdutoDao(gDao);
		String saida = pDao.crud("U", p);
		return saida;
	}

	public String excluir(Produto p) throws SQLException, ClassNotFoundException 
	{
		GenericDao gDao = new GenericDao();
		ProdutoDao pDao = new ProdutoDao(gDao);
		String saida = pDao.crud("D", p);
		return saida;
	}

	public Produto buscar(Produto p) throws SQLException, ClassNotFoundException 
	{
		GenericDao gDao = new GenericDao();
		ProdutoDao pDao = new ProdutoDao(gDao);
		p = pDao.consultar(p);
		return p;
	}

	public List<Produto> listar() throws SQLException, ClassNotFoundException 
	{
		GenericDao gDao = new GenericDao();
		ProdutoDao pDao = new ProdutoDao(gDao);
		List<Produto> produtos = pDao.listar();
		
		return produtos;
	}
	
	public int qtdEstoque(int qtdEstoque) throws SQLException, ClassNotFoundException 
	{
		GenericDao gDao = new GenericDao();
		ProdutoDao pDao = new ProdutoDao(gDao);
		return pDao.qtdEstoque(qtdEstoque);
	}
	
	public List<Produto> qtdProduto(int qtdProduto) throws SQLException, ClassNotFoundException 
	{
		GenericDao gDao = new GenericDao();
		ProdutoDao pDao = new ProdutoDao(gDao);
		List<Produto> produtos = pDao.listarQtdProduto(qtdProduto);
		
		return produtos;
	}
	
	public boolean validar(String v)
	{
		if(v.strip().equals(""))
			return true;
		
		return false;
	}
	
	
	
}
