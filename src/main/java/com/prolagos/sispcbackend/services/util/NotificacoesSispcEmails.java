package com.prolagos.sispcbackend.services.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class NotificacoesSispcEmails {
	public String Nome;
    String servidor = "correio.level3br.com";
    int porta = 587;
    String usuario = "sispc.prl@prolagos.com.br";
    String senha = "Prolago5";
    String remetente = "sispc.prl@prolagos.com.br";
    String [] destinatario;
    
    
    public Connection getnewConnection() throws ClassNotFoundException, SQLException{
        Connection connection=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://SISPCPRL01:3306/sispc?autoReconnect=true&useSSL=false","vitor.heser", "Heser15585907735");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public ArrayList<String> Emails() throws ClassNotFoundException, SQLException{
    	ArrayList<String> Emails= new ArrayList<>();
    	String inserir="SELECT * FROM sispc.cad_sispc_usuarios;";

        Connection connection= getnewConnection();
        PreparedStatement ps = connection.prepareStatement(inserir);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            String email = rs.getString("email");
            Emails.add(email);
        }
        
        connection.close();
		return Emails;
    }
    
    
    public NotificacoesSispcEmails(String Assunto, String Texto) throws EmailException, UnsupportedEncodingException, ClassNotFoundException, SQLException {
    	
    	
    	ArrayList Emails = Emails();
    	Texto = Texto.replaceAll("\n", "<br>");
    	HtmlEmail email = new HtmlEmail();
        email.setDebug(true);
		
        String assunto = Assunto;
        String[] convertido = (String[]) Emails.toArray(new String[Emails.size()]);
		
        String txtHtml = "<html>";
        txtHtml =Texto;
		txtHtml = txtHtml +convertido+ Assinatura(email) + "</html>";
		
		
		
		
        email.setDebug(false);
        email.setHostName(servidor);
        email.setSmtpPort(porta);
        email.setStartTLSEnabled(true);
        email.setAuthentication(usuario, senha);
        email.setFrom(remetente, "SisPC - Informativo");
        email.setSubject(assunto);
        email.addTo("vitor.heser@prolagos.com.br");
        email.setHtmlMsg(txtHtml);
        email.send();
    }

	
	public String Assinatura(HtmlEmail email) throws EmailException {
		String cid1 = email.embed(new File("C:\\Users\\Public\\Documents\\Prolagos.png"));
		
//		String cid1 = email.embed(new File("C:\\Users\\Public\\Documents\\Prolagos.png"));
		String Assinatura =  "<em>Atenciosamente,</em></strong></p>"
		+ "<img src=\"cid:" + cid1 + "\"  width=\"127\">";
		return Assinatura;
	}
	
	public String dataFormatada(String data) {
		String datafinal= data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0, 4) +" "+data.substring(11); 
		return datafinal;
	}

}
