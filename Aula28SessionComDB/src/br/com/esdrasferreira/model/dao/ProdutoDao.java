package br.com.esdrasferreira.model.dao;

import java.sql.*;
import java.util.*;

import br.com.edrasferreira.model.entity.Produto;
import br.com.esdrasferreira.factory.jdbc.FabricaConexao;

public class ProdutoDao {
	
	private Connection conexao;
	
	public ProdutoDao() throws Exception {
		this.conexao = FabricaConexao.getConexao();
	}

	public List<Produto> todos(int id) throws Exception{
		
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		Produto produto = null;
		
		try {
			
			conexao = this.conexao;
			st = conexao.prepareStatement("SELECT * FROM servlet.produtos where servlet.produtos.id_produto = '"+id+"'");
			rs = st.executeQuery();
			
			List<Produto> produtos = new ArrayList<Produto>();
			
			while(rs.next()) {
				
				produto = new Produto(rs.getInt(1), rs.getString(2));
				
				produtos.add(produto);
				
			}
			
			return produtos;
				
			
		}catch(SQLException sqle) {
			
			throw new Exception("Erro ao visualizar os dados "+ sqle);
			
		}finally {
			FabricaConexao.fecharStmtRs(st, rs);
		}
		
	}
	

	//devemos chamar o fechamento da conexao apenas quando não for usar mais
	public void fecharConexao() throws Exception {
		FabricaConexao.fecharConexao(conexao);
	}

}
