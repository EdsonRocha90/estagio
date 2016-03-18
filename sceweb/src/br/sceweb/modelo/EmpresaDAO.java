package br.sceweb.modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import br.sceweb.servicos.FabricaDeConexoes;

public class EmpresaDAO {
	/*
	 * inclui uma empresa no db
	 * entrada objeto do tipo empresa
	 * saida - o numero de linhas afetadas com a operacao
	 */
	Logger logger = Logger.getLogger(EmpresaDAO.class);
	public int adiciona(Empresa empresa) {
		PreparedStatement ps;
		int codigoRetorno = 0;
		
		//logger.info("chamou o método adiciona");
		
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("insert into empresa (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone) values (?,?,?,?,?)");
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return codigoRetorno;
	}
	
	/*
	 * exclui uma empresa no db
	 * entrada string contendo o cnpj
	 * saida - o numero de linhas afetadas com a operacao
	 */
	public int exclui(String cnpj){
		PreparedStatement ps;
		int codigoRetorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("delete from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			codigoRetorno = ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
		return codigoRetorno;		
	}
}
