import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Quadras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Array de strings do tipo quadras
        String[] quadras = {
            "Quadra Poliesportiva 1 (Vôlei / Futsal)",
            "Quadra Poliesportiva 2 (Vôlei / Futsal)",
            "Quadra de Areia 1 (Vôlei / Futvolei)",
            "Quadra de Areia 2 (Vôlei / Futvolei)"
        };

        // Preço das quadras
        double[] precos = {90.0, 90.0, 50.0, 50.0};


        // Liasta de horarios disponiveis no formato 24H
        String[] horariosDisponiveis = {
            "08:00", "09:00", "10:00", "11:00", "12:00", 
            "13:00", "14:00", "15:00", "16:00", "17:00",
            "18:00", "19:00", "20:00", "21:00", "22:00",
            "23:00"
        };

        //Lista dinâmica onde será armazenado todos os agendamentos
        List<Agendamento> agendamentos = new ArrayList<>();

        //Mensagem de saudação
        System.out.println("Bem-vindos ao Sistema de Aluguel de Quadras!");

        //Loop que se repetira até ser escolhido a opção 5
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Ver quadras disponíveis");
            System.out.println("2. Ver horários disponíveis");
            System.out.println("3. Alugar quadra");
            System.out.println("4. Ver agendamentos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            //Captura a opçao escolhida
            int opcao = scanner.nextInt();
            scanner.nextLine();

            //Estrutura de decisão para cada opção do menu
            switch (opcao) {
                case 1:
                    
                    //Mostra todas as quadras e seus respectivos preços
                    System.out.println("\nQuadras disponíveis:");
                    for (int i = 0; i < quadras.length; i++) {
                        System.out.println((i + 1) + ". " + quadras[i] + " - R$" + precos[i] + "/Hora");
                    }
                    break;

                case 2:

                    //Exibe os horários disponiveis para cada quadra
                    System.out.println("\nHorários disponíveis:");
                    for (int i = 0; i < quadras.length; i++) {
                        System.out.println("\n" + quadras[i] + ":");
                        for (int h = 0; h < horariosDisponiveis.length; h++) {
                            boolean ocupado = false;

                                //Verifica se esse horário está ocupado para essa quadra
                            for (Agendamento ag : agendamentos) {
                                int inicio = getHorarioIndex(ag.horario, horariosDisponiveis);
                                if (ag.quadra == i && h >= inicio && h < inicio + ag.duracao && h < horariosDisponiveis.length) {
                                    ocupado = true;
                                    break;
                                }
                            }

                            //Mostra se o horário esta disponivel ou não
                            if (ocupado) {
                                System.out.print("[OCUPADO]  ");
                            } else {
                                System.out.print(horariosDisponiveis[h] + "  ");
                            }
                        }
                        System.out.println();
                    }
                    break;

                case 3:

                    //Processo de aluguel da quadra
                    System.out.println("\nAlugar Quadra:");
                    for (int i = 0; i < quadras.length; i++) {
                        System.out.println((i + 1) + ". " + quadras[i]);
                    }

                    System.out.print("Selecione o número da quadra: ");
                    int numQuadra = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (numQuadra < 0 || numQuadra >= quadras.length) {
                        System.out.println("Quadra inválida!");
                        break;
                    }

                    //Exibe os horários disponiveis para a quadra escolhida
                    System.out.println("Horários disponíveis para " + quadras[numQuadra] + ":");
                    for (int h = 0; h < horariosDisponiveis.length; h++) {
                        boolean ocupado = false;
                        for (Agendamento ag : agendamentos) {
                            int inicio = getHorarioIndex(ag.horario, horariosDisponiveis);
                            if (ag.quadra == numQuadra && h >= inicio && h < inicio + ag.duracao) {
                                ocupado = true;
                                break;
                            }
                        }
                        if (!ocupado) {
                            System.out.print(horariosDisponiveis[h] + "  ");
                        } else {
                            System.out.print("[OCUPADO]  ");
                        }
                    }
                    System.out.println();

                    //Recebe o horário inicial desejado
                    System.out.print("Digite o horário desejado (ex: 14:00): ");
                    String horario = scanner.nextLine();

                    //Converte o horario para índice
                    int inicioHorario = getHorarioIndex(horario, horariosDisponiveis);
                    if (inicioHorario == -1) {
                        System.out.println("Horário inválido!");
                        break;
                    }

                    //Captura quantas horas o cliente quer alugar
                    System.out.print("Quantas horas deseja alugar?: ");
                    int duracao = scanner.nextInt();
                    scanner.nextLine();

                    //verifica se a duração é maior que uma hora para poder ja ocupar a hora seguinte
                    if (inicioHorario + duracao > horariosDisponiveis.length) {
                        System.out.println("Horário excede limite da agenda!");
                        break;
                    }

                    // verifica se o horario escolhido já esta ocupado para não dar conflito
                    boolean conflito = false;
                    for (Agendamento ag : agendamentos) {
                        if (ag.quadra == numQuadra) {
                            int agInicio = getHorarioIndex(ag.horario, horariosDisponiveis);

                            //Checa se os intervalos não se sobrepõem
                            if (!(inicioHorario + duracao <= agInicio || inicioHorario >= agInicio + ag.duracao)) {
                                conflito = true;
                                break;
                            }
                        }
                    }

                    if (conflito) {
                        System.out.println("Já existe um agendamento nesse intervalo.");
                        break;
                    }

                    System.out.print("Digite seu nome: ");
                    String nome = scanner.nextLine();

                    //Calcula o valor total com base no preço da quadra e a duração
                    double total = precos[numQuadra] * duracao;

                    Agendamento novoAg = new Agendamento(numQuadra, nome, horario, duracao, total);
                    agendamentos.add(novoAg);

                    // Saida final para o cliente
                    System.out.println("Agendamento realizado com sucesso!");
                    System.out.println("Quadra: " + quadras[numQuadra]);
                    System.out.println("Horário: " + horario);
                    System.out.println("Duração: " + duracao + " hora(s)");
                    System.out.println("Total: R$" + total);
                    break;

                case 4:
                    //Lista todos os agendamentos registrados
                    System.out.println("\nAgendamentos Atuais:");
                    if (agendamentos.isEmpty()) {
                        System.out.println("Nenhum agendamento realizado.");
                    } else {
                        for (Agendamento ag : agendamentos) {
                            System.out.println("\nQuadra: " + quadras[ag.quadra]);
                            System.out.println("Cliente: " + ag.nome);
                            System.out.println("Horário: " + ag.horario);
                            System.out.println("Duração: " + ag.duracao + " hora(s)");
                            System.out.println("Total: R$" + ag.total);
                        }
                    }
                    break;

                case 5:
                    
                    //Encerra o programa
                    System.out.println("Obrigado por utilizar nosso sistema!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Retorna o índice do horário no array
    static int getHorarioIndex(String horario, String[] horarios) {
        for (int i = 0; i < horarios.length; i++) {
            if (horarios[i].equals(horario)) {
                return i;
            }
        }
        return -1;
    }

    static class Agendamento {
        int quadra;
        String nome;
        String horario;
        int duracao;
        double total;

        public Agendamento(int quadra, String nome, String horario, int duracao, double total) {
            this.quadra = quadra;
            this.nome = nome;
            this.horario = horario;
            this.duracao = duracao;
            this.total = total;
        }
    }
}
