package com.prolagos.sispcbackend.bots.calculosEnergia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalculosEnergia {
	

    public ArrayList<String> Datas = new ArrayList<>();
    public ArrayList<Double> Volume = new ArrayList<>();
    
    
    
    
	private void pesquisaDatas(String volume, String data, String url2, String username, String password) throws  ClassNotFoundException, SQLException {
		Datas.clear();
        String url = "select * from appweb_ind_exeindicadores where fk_indicador_id = "+volume+" and dataindicador <= '"+ ontem()+"' datareferencia = '"+data+"';" ;
        ResultSet rs;
        String dataindicador = null;
        Double realizado = null;
        PreparedStatement ps = getConnMySql(url2, username, password).prepareStatement(url);
        rs = ps.executeQuery();
        while(rs.next()){
            realizado = rs.getDouble("realizado");
            Datas.add(rs.getString("dataindicador"));
        }
        ps.close();
        rs.close();
    }
	
	public String ontem(){
        Calendar dataAtual = Calendar.getInstance(); 
        dataAtual.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(dataAtual.getTime());
    }
	
    public Connection getConnMySql(String url, String username, String password) throws ClassNotFoundException, SQLException {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    return DriverManager.getConnection(url+"?useTimezone=true&serverTimezone=UTC",username, password);
    }
}
