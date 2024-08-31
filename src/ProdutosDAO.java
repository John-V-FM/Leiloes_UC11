/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
                
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES(?,?,?);";
        
        try{
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
      }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar"+ e.getMessage());
      }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos";
        
        try{
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet res = prep.executeQuery();
            
            while(res.next()){
                ProdutosDTO p = new ProdutosDTO();
                
                p.setId(res.getInt("id"));
                p.setNome(res.getString("nome"));
                p.setValor(res.getInt("valor"));
                p.setStatus(res.getString("status"));
                
                listagem.add(p);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar os registros do banco de dados.");
        }
        return listagem;
    }
    
    
    
        
}

