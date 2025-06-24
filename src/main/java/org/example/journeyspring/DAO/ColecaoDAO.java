package org.example.journeyspring.DAO;


import org.example.journeyspring.model.Colecao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColecaoDAO {

    public List<Colecao> listar() {
        List<Colecao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Colecao";

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Colecao c = new Colecao();
                c.setId_colecao(rs.getInt("id_colecao"));
                c.setNome(rs.getString("nome"));
                c.setData_inicio(rs.getString("data_inicio"));
                c.setData_fim(rs.getString("data_fim"));
                lista.add(c);
            }

        } catch (Exception e) {
            System.err.println("Erro ao listar coleções: " + e.getMessage());
        }

        return lista;
    }

    public Colecao buscarPorId(int id) {
        String sql = "SELECT * FROM Colecao WHERE id_colecao = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Colecao c = new Colecao();
                c.setId_colecao(rs.getInt("id_colecao"));
                c.setNome(rs.getString("nome"));
                c.setData_inicio(rs.getString("data_inicio"));
                c.setData_fim(rs.getString("data_fim"));
                return c;
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar coleção: " + e.getMessage());
        }

        return null;
    }

    public boolean inserir(Colecao colecao) {
        String sql = "INSERT INTO Colecao (nome, data_inicio, data_fim) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, colecao.getNome());
            stmt.setDate(2, Date.valueOf(colecao.getData_inicio()));

            if (colecao.getData_fim() == null || colecao.getData_fim().isEmpty()) {
                stmt.setNull(3, Types.DATE);
            } else {
                stmt.setDate(3, Date.valueOf(colecao.getData_fim()));
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Erro ao inserir coleção: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean atualizar(Colecao c) {
        String sql = "UPDATE Colecao SET nome = ?, data_inicio = ?, data_fim = ? WHERE id_colecao = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());

            if (c.getData_inicio() == null || c.getData_inicio().isEmpty()) {
                throw new IllegalArgumentException("Data de início é obrigatória");
            }
            stmt.setDate(2, Date.valueOf(c.getData_inicio()));

            // Tratar data_fim (opcional)
            if (c.getData_fim() == null || c.getData_fim().isEmpty()) {
                stmt.setNull(3, Types.DATE);
            } else {
                stmt.setDate(3, Date.valueOf(c.getData_fim()));
            }

            stmt.setInt(4, c.getId_colecao());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("Nenhum registro foi atualizado - ID possivelmente não existe");
            }
            return rowsAffected > 0;

        } catch (IllegalArgumentException e) {
            System.err.println("Formato de data inválido: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar coleção: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean removerColecao(int id) {
        String sql = "DELETE FROM Colecao WHERE id_colecao = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Erro ao excluir coleção: " + e.getMessage());
            return false;
        }
    }

    public boolean nomeExiste(String nome) {
        String sql = "SELECT 1 FROM Colecao WHERE LOWER(nome) = LOWER(?)";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // retorna true se encontrou algo
        } catch (SQLException e) {
            System.err.println("Erro ao verificar nome da coleção: " + e.getMessage());
            return false;
        }
    }

}
