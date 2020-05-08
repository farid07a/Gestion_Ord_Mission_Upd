/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author horizon
 */
public class Settings {
    

   private Properties settings;
	private File propertiesFile;
   
    public Settings() throws FileNotFoundException, IOException{
//    Properties settings = new Properties();
//settings.put("title","This is simple example");
//settings.put("color","Color.BLACK");
//
//FileOutputStream out = new FileOutputStream("mySettings.properties");
//settings.store(out , "This is My Settings");

//
//    String usrDir=System.getProperty("title");
    	String userDir = System.getProperty("user.home");
    	File propertiesDir = new File ( userDir, ".myApp" );
		if ( ! propertiesDir.exists()) propertiesDir.mkdir();
		
                propertiesFile = new File( propertiesDir , "here.test");
                Properties defaultSettings = new Properties();
                defaultSettings.put("stat","lock");
                
                settings = new Properties( defaultSettings );
                
                if ( propertiesFile.exists() )  {
                    
                    JOptionPane.showMessageDialog(null, "The File is Exists");
			try {
				FileInputStream in = new FileInputStream ( propertiesFile );
				settings.load( in );
			}
			catch (IOException ex ) {
                            System.out.println("Controller.Settings.<init>()");
			}
		}
                
                


    }
    
    public void ApplyDefaultSettings(){
    
    
    }
    
    public static void main(String[] args) {
       try {
           Settings settings1 = new Settings();
       } catch (IOException ex) {
           Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
