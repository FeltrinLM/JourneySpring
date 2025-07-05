package org.example.journeyspring.DAO;

import org.example.journeyspring.model.Estampa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstampaDAO {

    public List<Estampa> listar() {
        List<Estampa> lista = new ArrayList<>();
        String sql = "SELECT e.*, c.nome AS nome_colecao " +
                "FROM Estampa e " +
                "JOIN Colecao c ON e.id_colecao = c.id_colecao";

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estampa e = new Estampa();
                e.setId_estampa(rs.getInt("estampa_id"));
                e.setNome(rs.getString("nome"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setId_colecao(rs.getInt("id_colecao"));
                e.setNomeColecao(rs.getString("nome_colecao")); // <-- novo
                lista.add(e);
            }

        } catch (Exception e) {
            System.err.println("Erro ao listar estampas: " + e.getMessage());
        }

        return lista;
    }


    public Estampa buscarPorId(int id) {
        String sql = "SELECT * FROM Estampa WHERE estampa_id = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Estampa e = new Estampa();
                e.setId_estampa(rs.getInt("estampa_id"));
                e.setId_colecao(rs.getInt("id_colecao"));
                e.setNome(rs.getString("nome"));
                e.setQuantidade(rs.getInt("quantidade"));
                return e;
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar estampas: " + e.getMessage());
        }

        return null;
    }

    public boolean inserir(Estampa estampa) {
        String sql = "INSERT INTO Estampa (nome, quantidade, id_colecao) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estampa.getNome());
            stmt.setInt(2, estampa.getQuantidade());
            stmt.setInt(3, estampa.getId_colecao());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Erro ao inserir estampa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean atualizar(Estampa e) {
        String sql = "UPDATE ESTAMPA SET nome = ?, quantidade = ?, id_colecao = ? WHERE estampa_id = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setInt(2, e.getQuantidade());
            stmt.setInt(3, e.getId_colecao());
            stmt.setInt(4, e.getId_estampa());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("Nenhum registro foi atualizado - ID possivelmente não existe");
            }
            return rowsAffected > 0;

        } catch (IllegalArgumentException ex) {
            System.err.println("Formato de data inválido: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.err.println("Erro ao atualizar estampa: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }



    public boolean excluir(int id) {
        String sql = "DELETE FROM ESTAMPA WHERE estampa_id = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Erro ao excluir estampa: " + e.getMessage());
            return false;
        }
    }

    public Estampa buscarPorNomeEColecao(String nome, int idColecao) {
        String sql = "SELECT * FROM Estampa WHERE LOWER(nome) = LOWER(?) AND id_colecao = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, idColecao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estampa e = new Estampa();
                e.setId_estampa(rs.getInt("estampa_id"));
                e.setNome(rs.getString("nome"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setId_colecao(rs.getInt("id_colecao"));
                return e;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar estampa por nome e coleção: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarQuantidade(int idEstampa, int novaQuantidade) {
        String sql = "UPDATE Estampa SET quantidade = ? WHERE estampa_id = ?";

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, idEstampa);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar quantidade da estampa: " + e.getMessage());
            return false;
        }
    }

}
