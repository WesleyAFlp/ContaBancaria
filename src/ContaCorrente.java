import java.util.ArrayList;

public class ContaCorrente extends Conta {
    // 2. Adiciona o atributo limiteChequeEspecial (double)
    private double limiteChequeEspecial;

    // 3. Cria o construtor. Chama o construtor da superclasse e inicializa o limite.
    public ContaCorrente(String numero, String titular, double saldoInicial, double limiteChequeEspecial) {

        // Chama o construtor da superclasse (Conta)
        super(numero, titular, saldoInicial);
        this.limiteChequeEspecial = limiteChequeEspecial;

        // Inicializa o histórico de transações, que estava faltando na classe Conta
        this.historicoTransacoes = new ArrayList<>();
    }

    // Método getter para o limite (opcional, mas útil)
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    // 4. Sobrescreve o método sacar(double valor) (Polimorfismo).
    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
            return;
        }

        // o Calcule o saldo total disponível (saldo + limiteChequeEspecial).
        double saldoTotalDisponivel = this.saldo + this.limiteChequeEspecial;

        // o Verifique se o valor do saque excede o saldo disponível.
        if (valor > saldoTotalDisponivel) {
            // o Se exceder: Lance a SaldoInsuficienteException.
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente. Saldo disponível (Saldo + Limite): R$" + String.format("%.2f", saldoTotalDisponivel) +
                            ". Valor do saque solicitado: R$" + String.format("%.2f", valor)
            );
        } else {
            // o Se não exceder: Subtraia o valor do saque do saldo e registre a transação.
            this.saldo -= valor;

            // Registra a transação
            Transacao novaTransacao = new Transacao("SAQUE", valor);
            this.historicoTransacoes.add(novaTransacao);

            System.out.println("Saque de R$" + String.format("%.2f", valor) + " realizado com sucesso.");
            System.out.println("Novo Saldo: R$" + String.format("%.2f", this.saldo));
        }
    }

    // Sobrescreve o método depositar para também registrar a transação
    @Override
    public void depositar(double valor){
        if (valor > 0){
            this.saldo += valor;

            // Registra a transação
            Transacao novaTransacao = new Transacao("DEPOSITO", valor);
            this.historicoTransacoes.add(novaTransacao);

            System.out.println("Depósito de R$" + String.format("%.2f", valor) + " realizado com sucesso.");
            System.out.println("Novo Saldo: R$" + String.format("%.2f", this.saldo));
        } else {
            System.out.println("Não é possível depositar um valor negativo ou igual a 0!");
        }
    }

    // Método para imprimir o histórico (Adicional)
    public void imprimirHistorico() {
        System.out.println("\n--- Histórico de Transações ---");
        if (historicoTransacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
            return;
        }
        for (Transacao t : historicoTransacoes) {
            System.out.println(t);
        }
        System.out.println("---------------------------------");
    }
}