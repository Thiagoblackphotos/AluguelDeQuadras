import java.util.Scanner;

public class Quadras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] quadras = {
            "Quadra Poliesportiva 1 (Vôlei / Futsal)",
            "Quadra Poliesportiva 2 (Vôlei / Futsal)",
            "Quadra de Areia 1 (Vôlei / Futvolei)",
            "Quadra de Areia 2 (Vôlei / Futvolei)"


            
        };

        // preços por hora
        double[] precos = {90.0, 90.0, 50.0, 50.0};

        String[] agendamentos = new String[4];
        System.out.println("Bem-vindos ao Sistema de Aluguel de Quadras!");

        while (true){
            System.out.println(" Menu");
            System.out.println("1. Ver quadras disponiveis");
            System.out.println("2. Alugar quadra");
            System.out.println("3. Ver agendamentos");
            System.out.println("4. Sair");
            System.out.println("Escolha uma opção");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Quadras disponiveis:");
                    for (int i = 0; i < quadras.length; i++) {
                        System.out.println((i+1) + ". " + quadras[i] + " - R$" + precos[i] + "Hora");
                    }
                    break;

                    case 2: 
                    System.out.println("Alugar Quadras:");
                    System.out.println("Quadras Disponiveis");
                    for (int i = 0; i < quadras.length; i++) {
                        System.out.println((i+1) + ". " + quadras[i]);
                    }

                    System.out.println("Escolha o numero da quadra: ");
                    int numQuadra = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (numQuadra < 0 || numQuadra >= quadras.length) {
                        System.out.println("Quadra ivalida");
                        break;

                        
                    }

                    System.out.println("Digite seu nome: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o horario: ");
                    String horario = scanner.nextLine();

                    System.out.print("Quantas horas deseja alugar?: ");
                    int horas = scanner.nextInt();
                    scanner.nextLine();
                    double total = precos[numQuadra] * horas;

                    agendamentos[numQuadra] = " Cliente: " + nome + " | Horario: " + horario + " | Duração: " + horas + "h" + " Total: R$" + total;
                    
                System.out.println(" Agendamento realizado com sucesso!");
                System.out.println("Quadra: " + quadras[numQuadra]);
                System.out.println(agendamentos[numQuadra]);
                break;
                    
            case 3:
                System.out.println("Agendamentos Atuais:");
                    for (int i = 0; i < agendamentos.length; i++) {
                        System.out.print("Quadra " + (i+1) + ": ");
                        if (agendamentos[i] != null) {
                            System.out.println(agendamentos[i]);
                        } else {
                            System.out.println("disponivel");
                        }
                    }
                    break;

                    case 4:
                        System.out.println("Obrigado por utilizar nosso sistema!");
                        scanner.close();
                        return;



                default:
                    System.out.println("opçao invalida tente novamente.");

            }

        }
    }
    
}
