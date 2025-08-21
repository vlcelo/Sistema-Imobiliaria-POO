package br.com.imobiliaria.model;

public class Imovel {
    private int id;
    private String endereco;
    private String cidade;
    private String tipo;
    private int quartos;
    private int banheiros;
    private double areaM2;
    private double valorAluguel;
    private String status;

    public Imovel(int id, String endereco, String cidade, String tipo, int quartos, int banheiros,
                  double areaM2, double valorAluguel, String status) {
        this.id = id;
        this.endereco = endereco;
        this.cidade = cidade;
        this.tipo = tipo;
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.areaM2 = areaM2;
        this.valorAluguel = valorAluguel;
        this.status = status;
    }

    public int getId() { return id; }
    public String getEndereco() { return endereco; }
    public String getCidade() { return cidade; }
    public String getTipo() { return tipo; }
    public int getQuartos() { return quartos; }
    public int getBanheiros() { return banheiros; }
    public double getAreaM2() { return areaM2; }
    public double getValorAluguel() { return valorAluguel; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("Imóvel #%d | %s - %s | %s | %d quartos, %d banheiros | %.2f m² | R$ %.2f | %s",
                id, endereco, cidade, tipo, quartos, banheiros, areaM2, valorAluguel, status);
    }
}
