package com.prolagos.sispcbackend.services.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.springframework.scheduling.annotation.Async;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class AgendamentoEmails extends Thread{

	public String Nome;
    String servidor = "correio.level3br.com";
    int porta = 587;
    String usuario = "sispc.prl@prolagos.com.br";
    String senha = "Prolago5";
    String remetente = "sispc.prl@prolagos.com.br";
    String [] destinatario;

    public ArrayList<String> Emails = new ArrayList<>();

    public AgendamentoEmails(
    		Integer getAprovacao, 
			String getEmailsolicitante, 
			Integer getAgendamentoId, 
			String getCondutor, 
			String getDestino, 
			String getSolicitante,
			String getTipoVeiculoSolicitado,
			String getAgendadode,
	 		String getAgendadoate,
	 		String getJustificativasolicitacao,
	 		String getAprovador,
	 		String getJustificativa,
	 		String getPlaca,
	 		String getTipoVeiculoDisponibilizado,
    		String tipo) throws EmailException, UnsupportedEncodingException {
    	HtmlEmail email = new HtmlEmail();
        email.setDebug(true);
		
        String assunto = null;
        String txtHtml= null;
		Emails.add(getEmailsolicitante);

        String[] convertido = (String[]) Emails.toArray(new String[Emails.size()]);
        destinatario = convertido;
		
        if(tipo.equals("Novo Agendamento")) {
        	txtHtml=TextoHtmlNovoAgendamento(getAgendamentoId, getSolicitante,
        			getCondutor, getDestino, getTipoVeiculoSolicitado, getAgendadode, getAgendadoate, getJustificativasolicitacao,
        			getAprovador, getJustificativa, getPlaca, getTipoVeiculoDisponibilizado);
    		assunto = "Solicitação de Agendamento Nº"+getAgendamentoId;
        }else if(getAprovacao==1) {
        	txtHtml=TextoHtmlAgendamentoAprovado(getAgendamentoId, getSolicitante,
        			getCondutor, getDestino, getTipoVeiculoSolicitado, getAgendadode, getAgendadoate, getJustificativasolicitacao,
        			getAprovador, getJustificativa, getPlaca, getTipoVeiculoDisponibilizado);
    		assunto = "Solicitação de Agendamento Nº "+getAgendamentoId+" Aprovado";
        }else if(getAprovacao==0) {
        	txtHtml=TextoHtmlAgendamentoReprovado(getAgendamentoId, getSolicitante,
        			getCondutor, getDestino, getTipoVeiculoSolicitado, getAgendadode, getAgendadoate, getJustificativasolicitacao,
        			getAprovador, getJustificativa, getPlaca, getTipoVeiculoDisponibilizado);
    		assunto = "Solicitação de Agendamento Nº "+getAgendamentoId+" Reprovado";
        }

        txtHtml = txtHtml + Assinatura(email) + "</html>";
        email.setDebug(false);
        email.setHostName(servidor);
        email.setSmtpPort(porta);
        email.setStartTLSEnabled(true);
        email.setAuthentication(usuario, senha);
        email.setFrom(remetente, "SisPC - Agendamento de Veículos - teste");
        email.setSubject(assunto);
        email.addTo(destinatario);
        email.setHtmlMsg(txtHtml);
        email.send();

    }

	
	public String Assinatura(HtmlEmail email) throws EmailException {
		
		String cid1 = email.embed(new File("C:\\Users\\Public\\Documents\\Prolagos.png"));

		String Assinatura =  "<em>Atenciosamente,</em></strong></p>"
		+ "<img src=\"cid:" + cid1 + "\"  width=\"127\">";
		return Assinatura;
	}
	
	public String dataFormatada(String data) {
		String datafinal= data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0, 4) +" "+data.substring(11); 
		return datafinal;
	}
	

	public String TextoHtmlNovoAgendamento(
			Integer getAgendamentoId, 
			String getSolicitante,
			String getCondutor, 
			String getDestino, 
			String getTipoVeiculoSolicitado, 
			String getAgendadode, 
			String getAgendadoate, 
			String getJustificativasolicitacao,
			String getAprovador, 
			String getJustificativa, 
			String getPlaca, 
			String getTipoVeiculoDisponibilizado) throws EmailException{
		String texto=
                "<html>"
                        + "Olá,<br><br>"
                        + "Você acabou de agendar um veículo, segue abaixo os dados do seu agendamento: <br><br>"
                        + "<b>Nº da Solicitação: "+getAgendamentoId+"</b>,<br>"
                        + "<b>Solicitante: </b>"+getSolicitante+",<br>"
                        + "<b>Condutor: </b>"+getCondutor+",<br>"
                        + "<b>Destino: </b>"+getDestino+",<br>"
                        + "<b>Veículo Solicitado: </b>"+getTipoVeiculoSolicitado+",<br>"
                        + "<b>Horário: </b>"+dataFormatada(getAgendadode)+" - "+dataFormatada(getAgendadoate)+",<br>"
                        + "<b>Justificativa: </b>"+getJustificativasolicitacao+",<br><br>"
                        + "Aguarde que entraremos em contato em caso de aprovação ou reprovação da solicitação!.<br><br>";
                        
		return texto;
	}

	public String TextoHtmlAgendamentoAprovado(
			Integer getAgendamentoId, 
			String getSolicitante,
			String getCondutor, 
			String getDestino, 
			String getTipoVeiculoSolicitado, 
			String getAgendadode, 
			String getAgendadoate, 
			String getJustificativasolicitacao,
			String getAprovador, 
			String getJustificativa, 
			String getPlaca, 
			String getTipoVeiculoDisponibilizado)  throws EmailException{
		String texto=
                "<html>"
                        + "Olá, <br><br>"
                        + "Seu veículo foi aprovado pelo setor de transporte: <br><br>"
                        + "<b>SOLICITAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"

                        + "<b>Nº da Solicitação: "+getAgendamentoId+"</b>,<br>"
                        + "<b>Solicitante: </b>"+getSolicitante+",<br>"
                        + "<b>Condutor: </b>"+getCondutor+",<br>"
                        + "<b>Destino: </b>"+getDestino+",<br>"
                        + "<b>Veículo Solicitado: </b>"+getTipoVeiculoSolicitado+",<br>"
                        + "<b>Horário: </b>"+dataFormatada(getAgendadode)+" - "+dataFormatada(getAgendadoate)+",<br>"
                        + "<b>Justificativa: </b>"+getJustificativasolicitacao+",<br><br>"

                        + "<b>APROVAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"
                        + "<b>Status: </b>APROVADO<br>"

                        + "<b>Placa: </b>"+getPlaca+",<br>"
                        + "<b>Veiculo Disponibilizado: </b>"+getTipoVeiculoDisponibilizado+",<br>"
                        + "<b>Aprovador: </b>"+getAprovador+"<br><br>"
                        
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
	public String TextoHtmlAgendamentoReprovado(
			Integer getAgendamentoId, 
			String getSolicitante,
			String getCondutor, 
			String getDestino, 
			String getTipoVeiculoSolicitado, 
			String getAgendadode, 
			String getAgendadoate, 
			String getJustificativasolicitacao,
			String getAprovador, 
			String getJustificativa, 
			String getPlaca, 
			String getTipoVeiculoDisponibilizado) throws EmailException{
		String texto=
                "<html>"
                        + "Olá, <br><br>"
                        + "Seu veículo foi reprovado pelo setor de transporte: <br><br>"
                        + "<b>SOLICITAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"
                        + "<b>Nº da Solicitação: "+getAgendamentoId+"</b>,<br>"
                        + "<b>Solicitante: </b>"+getSolicitante+",<br>"
                        + "<b>Condutor: </b>"+getCondutor+",<br>"
                        + "<b>Destino: </b>"+getDestino+",<br>"
                        + "<b>Veículo Solicitado: </b>"+getTipoVeiculoSolicitado+",<br>"
                        + "<b>Horário: </b>"+dataFormatada(getAgendadode)+" - "+dataFormatada(getAgendadoate)+",<br>"
                        + "<b>Justificativa: </b>"+getJustificativasolicitacao+",<br><br>"

                        + "<b>APROVAÇÃO</b> <br>"
                        + "------------------------------------------------------------<br>"
                        + "<b>Status: </b>REPROVADO<br>"
                        + "<b>Aprovador: </b>"+getAprovador+"<br>"
                        + "<b>Justificativa: </b>"+getJustificativa+"<br><br>"
                        
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
