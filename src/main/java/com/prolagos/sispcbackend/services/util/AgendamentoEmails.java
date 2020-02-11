package com.prolagos.sispcbackend.services.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;
import com.prolagos.sispcbackend.services.UsuariosService;

public class AgendamentoEmails {
	public String Nome;
    String servidor = "correio.level3br.com";
    int porta = 587;
    String usuario = "sispc.prl@prolagos.com.br";
    String senha = "Prolago5";
    String remetente = "sispc.prl@prolagos.com.br";
    String [] destinatario;

    public ArrayList<String> Emails = new ArrayList<>();
	private UsuariosService service;
    
    //AGENDAMENTO
	public AgendamentoEmails(Appweb_Transporte_Agendamentos obj, String tipo) throws EmailException, UnsupportedEncodingException {
        System.setProperty("file.encoding","ISO-8859-1");
        System.setProperty("encoding", "ISO-8859-1");
        System.setProperty("sun.jnu.encoding", "ISO-8859-1");
		HtmlEmail email = new HtmlEmail();
		
        String assunto = null;
        String txtHtml= null;
        
        if(tipo.equals("Novo Agendamento")) {
        	txtHtml=TextoHtmlNovoAgendamento(obj);
    		assunto = "Solicitação de Agendamento";
        }else if(obj.getAprovacao()==1) {
        	txtHtml=TextoHtmlAgendamentoAprovado(obj);
    		assunto = "Agendamento Aprovado";
        }else if(obj.getAprovacao()==0) {
        	txtHtml=TextoHtmlAgendamentoReprovado(obj);
    		assunto = "Agendamento Reprovado";
        }
        
		Emails.add(obj.getEmailsolicitante());
        String[] convertido = (String[]) Emails.toArray(new String[Emails.size()]);
        destinatario = convertido;
		
        
        txtHtml = txtHtml + Assinatura(email) + "</html>";
        email.setDebug(false);
        email.setHostName(servidor);
        email.setSmtpPort(porta);
        email.setStartTLSEnabled(true);
        email.setAuthentication(usuario, senha);
        email.setFrom(remetente, "SisPC - Agendamento de Veículos");
        email.setSubject(assunto);
        email.addTo(destinatario);
        email.setHtmlMsg(txtHtml);
        email.send();
	}
	
	
	public String Assinatura(HtmlEmail email) throws EmailException {
		String cid1 = email.embed(new File("\\\\Fsprl01\\prolagos\\RPA\\UTIL\\Prolagos.png"));
		String Assinatura =  "<em>Atenciosamente,</em></strong></p>"
		+ "<img src=\"cid:" + cid1 + "\"  width=\"127\">";
		return Assinatura;
	}
	
	public String dataFormatada(String data) {
		String datafinal= data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0, 4) +" "+data.substring(11); 
		return datafinal;
	}
	
	public String TextoHtmlNovoAgendamento(Appweb_Transporte_Agendamentos obj) throws EmailException{
		String texto=
                "<html>"
                        + "Olá, <br><br>"
                        + "Você acabou de agendar um veículo, segue abaixo os dados do seu agendamento: <br><br>"
                        + "<b>Solicitante: </b>"+obj.getSolicitante()+",<br>"
                        + "<b>Condutor: </b>"+obj.getCondutor()+",<br>"
                        + "<b>Destino: </b>"+obj.getDestino()+",<br>"
                        + "<b>Veículo Solicitado: </b>"+obj.getTipoVeiculoSolicitado()+",<br>"
                        + "<b>Horário: </b>"+dataFormatada(obj.getAgendadode())+" - "+dataFormatada(obj.getAgendadoate())+",<br>"
                        + "<b>Justificativa: </b>"+obj.getJustificativasolicitacao()+",<br><br>"
                        + "Aguarde que entraremos em contato em caso de aprovação ou reprovação da solicitação!.<br><br>";
                        
		return texto;
	}
	public String TextoHtmlAgendamentoAprovado(Appweb_Transporte_Agendamentos obj) throws EmailException{
		String texto=
                "<html>"
                        + "Olá, <br><br>"
                        + "Seu veículo foi aprovado pelo setor de transporte: <br><br>"
                        + "<b>SOLICITAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"
                        + "<b>Nº da Solicitação: "+obj.getAgendamentoId()+"</b>,<br>"
                        + "<b>Solicitante: </b>"+obj.getSolicitante()+",<br>"
                        + "<b>Condutor: </b>"+obj.getCondutor()+",<br>"
                        + "<b>Destino: </b>"+obj.getDestino()+",<br>"
                        + "<b>Veículo Solicitado: </b>"+obj.getTipoVeiculoSolicitado()+",<br>"
                        + "<b>Horário: </b>"+dataFormatada(obj.getAgendadode())+" - "+dataFormatada(obj.getAgendadoate())+",<br>"
                        + "<b>Justificativa: </b>"+obj.getJustificativasolicitacao()+",<br><br>"

                        + "<b>APROVAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"
                        + "<b>Status: </b>APROVADO<br>"
                        + "<b>Placa: </b>"+obj.getPlaca()+",<br>"
                        + "<b>Veiculo Disponibilizado: </b>"+obj.getTipoVeiculoDisponibilizado()+",<br>"
                        + "<b>Aprovador: </b>"+obj.getAprovador()+"<br><br>"
                        
						+"<b>INSTRUÇÕES</b> <br>"
				        + "------------------------------------------------------------<br>"
        				+ "1. Retire as chaves junto ao setor de transporte.<br>"
        				+ "2. Velocidade máxima permitida é de 60km/h.<br>"
        				+ "3. O prazo máximo de permanência do veículo é de 4 Horas.<br>"	
        				+ "4. Ligue os faróis.<br>"
        				+ "5. Sempre use o cinto de segurança.<br><br>"
		;
				                        
		return texto;
	}
	public String TextoHtmlAgendamentoReprovado(Appweb_Transporte_Agendamentos obj) throws EmailException{
		String texto=
                "<html>"
                        + "Olá, <br><br>"
                        + "Seu veículo foi reprovado pelo setor de transporte: <br><br>"
                        + "<b>SOLICITAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"
                        + "<b>Nº da Solicitação: "+obj.getAgendamentoId()+"</b>,<br>"
                        + "<b>Solicitante: </b>"+obj.getSolicitante()+",<br>"
                        + "<b>Condutor: </b>"+obj.getCondutor()+",<br>"
                        + "<b>Destino: </b>"+obj.getDestino()+",<br>"
                        + "<b>Veículo Solicitado: </b>"+obj.getTipoVeiculoSolicitado()+",<br>"
                        + "<b>Horário: </b>"+dataFormatada(obj.getAgendadode())+" - "+dataFormatada(obj.getAgendadoate())+",<br>"
                        + "<b>Justificativa: </b>"+obj.getJustificativasolicitacao()+",<br><br>"

                        + "<b>APROVAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"
                        + "<b>Status: </b>REPROVADO<br>"
                        + "<b>Aprovador: </b>"+obj.getAprovador()+"<br>"
                        + "<b>Justificativa: </b>"+obj.getJustificativa()+"<br>"
                        
						+"<b>INSTRUÇÕES</b> <br>"
				        + "------------------------------------------------------------<br>"
        				+ "1. Retire as chaves junto ao setor de transporte.<br>"
        				+ "2. Velocidade máxima permitida é de 60km/h.<br>"
        				+ "3. O prazo máximo de permanência do veículo é de 4 Horas.<br>"	
        				+ "4. Ligue os faróis.<br>"
        				+ "5. Sempre use o cinto de segurança.<br><br>"
		;
				                        
		return texto;
	}
}
