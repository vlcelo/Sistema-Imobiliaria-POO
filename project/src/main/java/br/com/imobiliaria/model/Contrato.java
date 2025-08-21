package br.com.imobiliaria.model;

public class Contrato {
    private int id;
    private int clienteId;
    private int imovelId;
    private double valorMensal;
    private String dataInicio;
    private String dataFim;
    private String status;

    public Contrato(int id, int clienteId, int imovelId, double valorMensal, String dataInicio, String dataFim, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.imovelId = imovelId;
        this.valorMensal = valorMensal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    public int getId() { return id; }
    public int getClienteId() { return clienteId; }
    public int getImovelId() { return imovelId; }
    public double getValorMensal() { return valorMensal; }
    public String getDataInicio() { return dataInicio; }
    public String getDataFim() { return dataFim; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("Contrato #%d | Cliente ID: %d | Imóvel ID: %d | R$ %.2f/mês | %s a %s | %s",
                id, clienteId, imovelId, valorMensal, dataInicio, dataFim, status);
    }
}
