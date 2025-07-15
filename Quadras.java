import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


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

        String[] horariosDisponiveis = {
            "08:00", "09:00", "10:00", "11:00", "12:00", 
            "13:00", "14:00", "15:00", "16:00", "17:00",
            "18:00", "19:00", "20:00", "21:00", "22:00",
            "23:00",
        };
        List <Agendamento> agendamentos = new ArrayList<>();
        

        System.out.println("Bem-vindos ao Sistema de Aluguel de Quadras!");

        while (true){
            System.out.println(" Menu");
            System.out.println("1. Ver quadras disponiveis");
            System.out.println("2. Ver horarios disponiveis");
            System.out.println("3. Alugar quadra");
            System.out.println("4. Ver agendamentos");
            System.out.println("5. Sair");
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
                        System.out.println("Horarios disponiveis");
                        for (int i = 0; i < quadras.length; i++) {
                            System.out.println("\n" + quadras[i] + ":");
                            for (String horario : horariosDisponiveis) {
                                boolean ocupado = false;
                                for (Agendamento ag : agendamentos) {
                                    if (ag.quadra == i && ag.horario.equals(horario)) {
                                        ocupado = true;
                                        break;
                                    }
                                }
                                if (ocupado) {
                                    System.out.println("[OCUPADO]");
                                } else {
                                    System.out.print(horario + "  ");
                                }
                            }
                            System.out.println();

                        }
                        break;






                        
                        
        
                    case 3: 
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

                    System.out.println("Horarios disponiveis para " + quadras[numQuadra] + ":");
                    for (String horario : horariosDisponiveis) {
                        boolean ocupado = false;
                        for (Agendamento ag : agendamentos) {
                            if (ag.quadra == numQuadra && ag.horario.equals(horario)) {
                                ocupado = true;
                                break;

                            }
                        }
                        if (!ocupado) {
                            System.out.print(horario + "  ");
                        }
                    }
                    System.out.println();

                    System.out.print("Digite o horario desejado (formato de 24H)");
                    String horario = scanner.nextLine();

                    boolean horarioValido = false;
                    for (String h : horariosDisponiveis) {
                        if (h.equals(horario)){
                            horarioValido = true;
                            break;
                        }
                    }

                    if (!horarioValido){
                        System.out.println("Horario invalido! Use o formato 24h (ex: 14:00)");
                        break;
                    }

                    for (Agendamento ag : agendamentos){
                        if (ag.quadra == numQuadra && ag.horario.equals(horario)) {
                            System.out.println("Este horario já está ocupado!");
                            break;

                        }
                    }


                    System.out.println("Digite seu nome: ");
                    String nome = scanner.nextLine();

                    

                    System.out.print("Quantas horas deseja alugar?: ");
                    int horas = scanner.nextInt();
                    scanner.nextLine();
                    double total = precos[numQuadra] * horas;

                    Agendamento novoAgendamento = new Agendamento(numQuadra, nome, horario, horas, total);
                    agendamentos.add(novoAgendamento);


                    
                System.out.println(" Agendamento realizado com sucesso!");
                System.out.println("Quadra: " + quadras[numQuadra]);
                System.out.println("Horario: " + horario);
                System.out.println("Duração: " + horas + " hora(s)");
                System.out.println("Total: R$" + total);
                break;
                    
            case 4:
                System.out.println("Agendamentos Atuais:");
                if (agendamentos.isEmpty()) {
                    System.out.println("Nenhum agendamanto realizado.");


                } else {
                    for (Agendamento ag : agendamentos){
                        System.out.println("Quadra: " + quadras[ag.quadra]);
                        System.out.println("Cliente: " + ag.nome);
                        System.out.println("Horário: " + ag.horario);
                        System.out.println("Duração: " + ag.horas + " hora(s)");
                        System.out.println("Total: R$" + ag.total);
                    }
                }
                    break;

                    case 5:
                        System.out.println("Obrigado por utilizar nosso sistema!");
                        scanner.close();
                        return;



                default:
                    System.out.println("opçao invalida tente novamente.");

            }

        }
    }
    
    static class  Agendamento {
        int quadra;
        String nome;
        String horario;
        int horas;
        double total;
        
        public Agendamento(int quadra, String nome, String horario, int horas, double total) {
            this.quadra = quadra;
            this.nome = nome;
            this.horario= horario;
            this.horas= horas;
            this.total = total;

        }
    
        
    }
}
