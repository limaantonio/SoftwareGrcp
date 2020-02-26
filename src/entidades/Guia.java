/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author carlos
 */
public class Guia {
    
    private String numero;
    private String secretaria;
    private Double baseDeCalculo;
    private Double valServidor;
    private Double valPatronal;
    private Double valSuplementar;
    private String observacao;
    
    public Guia(){
        
    }

    public Guia(String numero, String secretaria, Double baseDeCalculo, Double valServidor, Double valPatronal, Double valSuplementar, String observacao) {
        this.numero = numero;
        this.secretaria = secretaria;
        this.baseDeCalculo = baseDeCalculo;
        this.valServidor = valServidor;
        this.valPatronal = valPatronal;
        this.valSuplementar = valSuplementar;
        this.observacao = observacao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public Double getBaseDeCalculo() {
        return baseDeCalculo;
    }

    public void setBaseDeCalculo(Double baseDeCalculo) {
        this.baseDeCalculo = baseDeCalculo;
    }

    public Double getValServidor() {
        return valServidor;
    }

    public void setValServidor(Double valServidor) {
        this.valServidor = valServidor;
    }

    public Double getValPatronal() {
        return valPatronal;
    }

    public void setValPatronal(Double valPatronal) {
        this.valPatronal = valPatronal;
    }

    public Double getValSuplementar() {
        return valSuplementar;
    }

    public void setValSuplementar(Double valSuplementar) {
        this.valSuplementar = valSuplementar;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    private void calculaValorServidor(double base){
        this.valServidor = base * 0.11;
       
    }
    
    


    @Override
    public String toString() {
        return "Guia{" + "numero=" + numero + ", secretaria=" + secretaria + ", baseDeCalculo=" + baseDeCalculo + ", valServidor=" + valServidor + ", valPatronal=" + valPatronal + ", valSuplementar=" + valSuplementar + ", observacao=" + observacao + '}';
    }
    
    
}
