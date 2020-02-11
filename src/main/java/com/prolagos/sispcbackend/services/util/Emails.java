package com.prolagos.sispcbackend.services.util;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Emails {
	public String Nome;
    String servidor = "correio.level3br.com";
    int porta = 587;
    String usuario = "sispc.prl@prolagos.com.br";
    String senha = "Prolago5";
    String remetente = "sispc.prl@prolagos.com.br";
    String [] destinatario;

    public ArrayList<String> Emails = new ArrayList<>();
    
    
	public Emails(String tipo) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		

        String assunto = null;
        String txtHtml=
                "<html>"
                        + "Olá, Sou o SisPC,<br><br>"
                        + "Você acabou de agendar um veículo,<br>"
                        + "Aguarde que entraremos em contato!.<br><br>"
                        + Assinatura(email)
                + "</html>";
        
		if(tipo.equals("Agendamento")) {
			assunto = "Seu carro foi agendado com sucesso";
			Emails.add("vitor.heser@prolagos.com.br");
			
		}
        String[] convertido = (String[]) Emails.toArray(new String[Emails.size()]);
        destinatario = convertido;
		
        email.setDebug(false);
        email.setHostName(servidor);
        email.setSmtpPort(porta);
        email.setStartTLSEnabled(true);
        email.setAuthentication(usuario, senha);
        email.setFrom(remetente, "Planejamento - Relatório de Faturas Enel");
        email.setSubject(assunto);
        email.addTo(destinatario);
        email.setHtmlMsg(txtHtml);
        email.send();
	}
	
	
	public String Assinatura(HtmlEmail email) throws EmailException {
		String cid1 = email.embed(new File("\\\\Fsprl01\\prolagos\\RPA\\UTIL\\Prolagos.png"));
		String Assinatura =  "<em>Atenciosamente,</em></strong></p>"
        
        
		+"<br>"
		+ "<img src=\"cid:" + cid1 + "\"  width=\"127\" height=\"30\">" +
		                
		                
		"<div class=WordSection1>\n" +
		"\n" +
		"<p class=MsoNormal><b><span style='font-size:10.0pt;font-family:\"Trebuchet MS\",sans-serif;\n" +
		"color:#253F93'>Setor Planejamento<o:p></o:p></span></b></p>\n" +
		"\n" +
		"<p class=MsoNormal><span style='font-size:9.0pt;font-family:\"Trebuchet MS\",sans-serif;\n" +
		"color:#00AAA3'><o:p>&nbsp;</o:p></span></p>\n" +
		"\n" +
		"<p class=MsoNormal><span style='font-size:8.0pt;font-family:\"Trebuchet MS\",sans-serif;\n" +
		"color:#253F93'>+55 22 2621-5030<o:p></o:p></span></p>\n" +
		"\n" +
		"<p class=MsoNormal><span style='font-size:8.0pt;font-family:\"Trebuchet MS\",sans-serif;\n" +
		"color:#253F93'>+55 22 999491665<o:p></o:p></span></p>\n" +
		"\n" +
		"<p class=MsoNormal><span style='font-size:8.0pt;font-family:\"Trebuchet MS\",sans-serif;\n" +
		"color:#253F93'>Rodv. Amaral Peixoto, Km 107, quadra 20, lote 09<span\n" +
		"style='mso-spacerun:yes'>                            </span><o:p></o:p></span></p>\n" +
		"\n" +
		"<p class=MsoNormal><span style='font-size:8.0pt;font-family:\"Trebuchet MS\",sans-serif;\n" +
		"color:#253F93'>CEP 28.940-000 – Balneário – São Pedro da Aldeia <o:p></o:p></span></p>\n" +
		"\n" +
		"<p class=MsoNormal><span class=MsoHyperlink><span style='font-size:8.0pt;\n" +
		"font-family:\"Trebuchet MS\",sans-serif'><a href=\"http://www.prolagos.com.br/\">http://www.prolagos.com.br</a></span></span><span\n" +
		"class=MsoHyperlink><span style='mso-bidi-font-family:\"Times New Roman\"'><o:p></o:p></span></span></p>\n" +
		"\n" +
		"<p class=MsoNormal><o:p>&nbsp;</o:p></p>\n" +
		"\n" +
		"</div>\n"; 
		return Assinatura;
	}
}
