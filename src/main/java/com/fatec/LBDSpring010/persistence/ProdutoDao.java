package com.fatec.LBDSpring010.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fatec.LBDSpring010.model.Produto;

@Repository
public class ProdutoDao implements ICrud<Produto>, ICrudProcedure<Produto>
{
	private GenericDao gDao;

	public ProdutoDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public Produto consultar(Produto p) throws SQLException, ClassNotFoundException
	{
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, valor, qtd_estoque FROM produto WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getCodigo());
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setValor(rs.getFloat("valor"));
			p.setQtdEstoque(rs.getInt("qtd_estoque"));
		}
		rs.close();
		ps.close();
		c.close();
		
		return p;
	}

	@Override
	public List<Produto> listar() throws SQLException, ClassNotFoundException
	{
		List<Produto> produtos = new ArrayList<Produto>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, valor, qtd_estoque FROM produto";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Produto p = new Produto();
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setValor(rs.getFloat("valor"));
			p.setQtdEstoque(rs.getInt("qtd_estoque"));
			
			produtos.add(p);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return produtos;
	}

	@Override
	public String crud(String acao, Produto p) throws SQLException, ClassNotFoundException
	{
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_crud_produto (?, ?, ?, ?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, acao);
		cs.setInt(2, p.getCodigo());
		cs.setString(3, p.getNome());
		cs.setFloat(4, p.getValor());
		cs.setInt(5, p.getQtdEstoque());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(6);
		
		cs.close();
		c.close();
		return saida;
	}

	public int qtdEstoque(int qtdEstoque) throws SQLException, ClassNotFoundException
	{
		Connection c = gDao.getConnection();
		String sql = "{? = CALL fn_qtd_estoque(?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setInt(2, qtdEstoque);
		cs.execute();
		
		int qtd = cs.getInt(1);
		
		cs.close();
		c.close();
		
		return qtd;
	}

	public List<Produto> listarQtdProduto(int qtdProduto) throws SQLException, ClassNotFoundException
	{
		List<Produto> produtos = new ArrayList<Produto>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, qtd_estoque FROM fn_qtd_produto(?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, qtdProduto);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Produto p = new Produto();
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setQtdEstoque(rs.getInt("qtd_estoque"));
			
			produtos.add(p);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return produtos;
	}

}
