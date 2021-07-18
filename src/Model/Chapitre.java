/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author farid
 */
public class Chapitre {
    
    private int Id_Chp;
    private int NumChp;
    private String Descp_Chp;
    private double Amount_Chp;
    
    
    public Chapitre(){
    
    }
    
    public void InsertChapitre(){
    
    String Query="insert into Chapitre (Num_Chapitre,Desc_Chapitre,amount)VALUES (?,?,?)";
    
    }
}
