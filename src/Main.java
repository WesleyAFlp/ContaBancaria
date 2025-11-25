import java.util.Scanner;

public class Main {

    // Objeto Scanner estático para ler a entrada do usuário
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // A conta corrente que será utilizada durante a execução
        ContaCorrente conta = null;
        int opcao;

        do {
            // Exibe o menu principal
            exibirMenuPrincipal();

            // Tenta ler a opção do usuário
            try {
                // Se a conta ainda não foi criada, obriga a criação primeiro (opção 1)
                if (conta == null) {
                    System.out.print("Escolha uma opção (1 para Criar Conta): ");
                } else {
                    System.out.print("Escolha uma opção: ");
                }

                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha após o nextInt()

                // Processa a opção
                switch (opcao) {
                    case 1:
                        conta = criarConta();
                        break;
                    case 2:
                        if (conta != null) {
                            realizarDeposito(conta);
                        } else {
                            System.out.println("\n⚠️ ERRO: Primeiro você precisa criar uma conta (Opção 1).");
                        }
                        break;
                    case 3:
                        if (conta != null) {
                            realizarSaque(conta);
                        } else {
                            System.out.println("\n⚠️ ERRO: Primeiro você precisa criar uma conta (Opção 1).");
                        }
                        break;
                    case 4:
                        if (conta != null) {
                            consultarSaldo(conta);
                        } else {
                            System.out.println("\n⚠️ ERRO: Primeiro você precisa criar uma conta (Opção 1).");
                        }
                        break;
                    case 5:
                        if (conta != null) {
                            conta.imprimirHistorico();
                        } else {
                            System.out.println("\n⚠️ ERRO: Primeiro você precisa criar uma conta (Opção 1).");
                        }
                        break;
                    case 0:
                        System.out.println("\n👋 Obrigado por usar o sistema! Encerrando...");
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida. Tente novamente.");
                }
            } catch (java.util.InputMismatchException e) {
                // Captura se o usuário digitar algo que não seja um número inteiro para a opção
                System.out.println("\n❌ Entrada inválida. Por favor, digite um número correspondente à opção.");
                scanner.nextLine(); // Limpa o buffer de entrada para evitar loop infinito
                opcao = -1; // Garante que o loop continue
            } catch (Exception e) {
                // Captura outras exceções genéricas
                System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
                opcao = -1;
            }

            System.out.println("\n----------------------------------------------");

        } while (opcao != 0);

        scanner.close(); // Fecha o Scanner ao sair do programa
    }

    /**
     * Exibe as opções disponíveis para o usuário.
     */
    private static void exibirMenuPrincipal() {
        System.out.println("\n===== Menu Bancário =====");
        System.out.println("1. Criar Conta Corrente");
        System.out.println("2. Realizar Depósito");
        System.out.println("3. Realizar Saque");
        System.out.println("4. Consultar Saldo e Detalhes");
        System.out.println("5. Visualizar Histórico de Transações");
        System.out.println("0. Sair");
        System.out.println("=========================");
    }

    /**
     * Coleta os dados para criar a ContaCorrente.
     * @return Uma nova instância de ContaCorrente.
     */
    private static ContaCorrente criarConta() {
        System.out.println("\n--- Criação de Conta ---");
        System.out.print("Número da Conta: ");
        String numero = scanner.nextLine();

        System.out.print("Nome do Titular: ");
        String titular = scanner.nextLine();

        double saldoInicial = lerValor("Saldo Inicial");

        double limite = lerValor("Limite de Cheque Especial");

        ContaCorrente novaConta = new ContaCorrente(numero, titular, saldoInicial, limite);
        System.out.println("\n✅ Conta Corrente criada com sucesso para " + titular + "!");
        return novaConta;
    }

    /**
     * Realiza a operação de depósito na conta.
     * @param conta A ContaCorrente para realizar a operação.
     */
    private static void realizarDeposito(ContaCorrente conta) {
        System.out.println("\n--- Realizar Depósito ---");
        double valor = lerValor("Valor do Depósito");
        conta.depositar(valor);
    }

    /**
     * Realiza a operação de saque na conta, tratando a exceção.
     * @param conta A ContaCorrente para realizar a operação.
     */
    private static void realizarSaque(ContaCorrente conta) {
        System.out.println("\n--- Realizar Saque ---");
        double valor = lerValor("Valor do Saque");

        try {
            conta.sacar(valor);
        } catch (SaldoInsuficiente e) {
            System.out.println("\n🛑 ERRO no Saque: " + e.getMessage());
        }
    }

    /**
     * Exibe o saldo atual e os detalhes da conta.
     * @param conta A ContaCorrente para consultar.
     */
    private static void consultarSaldo(ContaCorrente conta) {
        System.out.println("\n--- Detalhes da Conta ---");
        System.out.println("Titular: " + conta.getTitular());
        System.out.println("Número: " + conta.getNumero());

        // Exibe o saldo sem chamar o getSaldo() para evitar a mensagem extra
        double saldoAtual = conta.saldo;
        double limiteAtual = conta.getLimiteChequeEspecial();
        double saldoTotal = saldoAtual + limiteAtual;

        System.out.println("Saldo Atual: R$" + String.format("%.2f", saldoAtual));
        System.out.println("Limite Cheque Especial: R$" + String.format("%.2f", limiteAtual));
        System.out.println("SALDO TOTAL DISPONÍVEL: R$" + String.format("%.2f", saldoTotal));
    }

    /**
     * Método auxiliar para ler valores double com tratamento de erro.
     * @param prompt O texto a ser exibido para o usuário.
     * @return O valor double lido.
     */
    private static double lerValor(String prompt) {
        double valor = 0.0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print(prompt + ": R$");
                valor = scanner.nextDouble();
                scanner.nextLine(); // Consome a quebra de linha

                if (valor < 0) {
                    System.out.println("⚠️ Valor não pode ser negativo. Tente novamente.");
                } else {
                    entradaValida = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Por favor, digite um número (ex: 100.50).");
                scanner.nextLine(); // Limpa o buffer de entrada
            }
        }
        return valor;
    }
}