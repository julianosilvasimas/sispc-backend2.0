package com.prolagos.sispcbackend.domain.enums;

public enum Classificacao_Indicadores {
	
	ATENDIMENTO(1, "Atendimento"), 
    SERVCOMERCIAL(2, "Servi\u00e7os Comercial"), 
    CORPORATIVO(3, "Corporativo"), 
    ENERGIA(4, "Energia"), 
    VOLUMES(5, "Volumes"), 
    PRODUTOSQU\u00cdMICOS(6, "Produtos Qu\u00edmicos"), 
    FATURAMENTO(7, "Comerciais"), 
    INDICADOROPERACIONAL(8, "Indicador Operacional"), 
    COBRANCA(9, "Cobran\u00e7a"), 
    ASSERTIVIDADE(10, "Assertividade"), 
    PRODUTIVIDADE(11, "Produtivade"), 
    PRAZOS(12, "Prazos");
    
    private int cod;
    private String descricao;
    
    public static Classificacao_Indicadores toEnum(final Integer cod) {
        if (cod == null) {
            return null;
        }
        for (final Classificacao_Indicadores x : values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inv\u00e1lido: " + cod);
    }
    
    private Classificacao_Indicadores(final int cod, final String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }
    
    public int getCod() {
        return this.cod;
    }
    
    public String getDescricao() {
        return this.descricao;
    }

}
