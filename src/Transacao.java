import java.time.LocalDateTime;
public class Transacao {
    private LocalDateTime data;
    private String tipo;
    private double valor;

    public Transacao(String tipo, double valor){
        this.data = LocalDateTime.now();
        this.tipo = tipo; this.valor = valor;
    }

    public LocalDateTime getData(){
        return data;
    }

    public String getTipo() {
        return tipo;
    }
    public double getValor(){
        return valor;
    }
    public String toString(){
        return (data + " - " + tipo+" - R$ "+ valor);
    }
