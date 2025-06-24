package org.example.journeyspring.DAO;


import org.example.journeyspring.model.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PecaDAO {

    // Listar todas as peças
    public ArrayList<Peca> listar() {
        String sql = "SELECT * FROM Peca";
        ArrayList<Peca> pecas = new ArrayList<>();

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Peca p = new Peca();
                p.setPeca_id(rs.getInt("peca_id"));
                p.setTipo(rs.getString("tipo"));
                p.setTamanho(rs.getString("tamanho"));
                p.setCor(rs.getString("cor"));
                p.setQuantidade(rs.getInt("quantidade"));
                pecas.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar as peças: " + e.getMessage());
            return new ArrayList<>(); // ← evita o NullPointer
        }

        return pecas;
    }

    // Buscar uma peça pelo ID
    public Peca buscarPorId(int idPeca) {
        String sql = "SELECT * FROM Peca WHERE peca_id = ?";
        Peca peca = null;

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPeca);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    peca = new Peca();
                    peca.setPeca_id(rs.getInt("peca_id"));
                    peca.setTipo(rs.getString("tipo"));
                    peca.setTamanho(rs.getString("tamanho"));
                    peca.setCor(rs.getString("cor"));
                    peca.setQuantidade(rs.getInt("quantidade"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar peça: " + e.getMessage());
        }

        return peca;
    }

    // Inserir uma nova peça
    public boolean inserir(Peca peca) {
        String sql = "INSERT INTO Peca (tipo, tamanho, cor, quantidade) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, peca.getTipo());
            stmt.setString(2, peca.getTamanho());
            stmt.setString(3, peca.getCor());
            stmt.setInt(4, peca.getQuantidade());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir peça: " + e.getMessage());
            return false;
        }
    }

    // Atualizar uma peça
    public boolean atualizar(Peca p) {
        String sql = "UPDATE Peca SET tipo = ?, tamanho = ?, cor = ?, quantidade = ? WHERE peca_id = ?";

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getTamanho());
            stmt.setString(3, p.getCor());
            stmt.setInt(4, p.getQuantidade());
            stmt.setInt(5, p.getPeca_id());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar peça: " + e.getMessage());
            return false;
        }
    }

    // Excluir uma peça
    public boolean excluir(int idPeca) {
        String sql = "DELETE FROM Peca WHERE peca_id = ?";

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPeca);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma peça com id " + idPeca + " encontrada.");
                return false;
            }

            System.out.println("Peça id: " + idPeca + " excluída com sucesso.");
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir peça: " + e.getMessage());
            return false;
        }
    }
    // Verifica se já existe uma peça com mesmo tipo, tamanho e cor
    public Peca buscarPorAtributos(String tipo, String tamanho, String cor) {
        String sql = "SELECT * FROM Peca WHERE tipo = ? AND tamanho = ? AND cor = ?";
        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            stmt.setString(2, tamanho);
            stmt.setString(3, cor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Peca p = new Peca();
                    p.setPeca_id(rs.getInt("peca_id"));
                    p.setTipo(rs.getString("tipo"));
                    p.setTamanho(rs.getString("tamanho"));
                    p.setCor(rs.getString("cor"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    return p;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar peça por atributos: " + e.getMessage());
        }

        return null;
    }

    // Atualiza apenas a quantidade de uma peça existente
    public boolean atualizarQuantidade(int pecaId, int novaQuantidade) {
        String sql = "UPDATE Peca SET quantidade = ? WHERE peca_id = ?";

        try (Connection con = ConexaoBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, pecaId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar quantidade da peça: " + e.getMessage());
            return false;
        }
    }

}
