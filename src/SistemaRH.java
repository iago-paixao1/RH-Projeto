import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class SistemaRH {
    private static List<Funcionario> listaFuncionarios = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            String menu = """
                    === SISTEMA DE RH ===
                    1. Cadastrar Funcionário
                    2. Listar Todos os Funcionários
                    3. Pesquisar Funcionário por Código
                    4. Pesquisar Funcionário por Nome
                    5. Editar Dados do Funcionário
                    6. Remover Funcionário
                    7. Mostrar Funcionário com Maior Salário
                    8. Mostrar Funcionário com Menor Salário
                    9. Listar Funcionários por Cargo
                    10. Calcular Média Salarial
                    0. Sair
                    """;

            String entrada = JOptionPane.showInputDialog(menu + "\nEscolha uma opção:");
            if (entrada == null) break; // caso o usuário cancele
            opcao = Integer.parseInt(entrada);

            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> listarFuncionarios();
                case 3 -> pesquisarPorCodigo();
                case 4 -> pesquisarPorNome();
                case 5 -> editarFuncionario();
                case 6 -> removerFuncionario();
                case 7 -> funcionarioMaiorSalario();
                case 8 -> funcionarioMenorSalario();
                case 9 -> listarPorCargo();
                case 10 -> calcularMediaSalarial();
                case 0 -> JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (true);
    }

    private static void cadastrarFuncionario() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código:"));
        String nome = JOptionPane.showInputDialog("Digite o nome:");
        String cargo = JOptionPane.showInputDialog("Digite o cargo:");
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Digite o salário:"));

        listaFuncionarios.add(new Funcionario(codigo, nome, cargo, salario));
        JOptionPane.showMessageDialog(null, "✅ Funcionário cadastrado com sucesso!");
    }

    private static void listarFuncionarios() {
        if (listaFuncionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Nenhum funcionário cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder("=== Lista de Funcionários ===\n");
            for (Funcionario f : listaFuncionarios) {
                sb.append(f).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void pesquisarPorCodigo() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código:"));
        for (Funcionario f : listaFuncionarios) {
            if (f.getCodigo() == codigo) {
                JOptionPane.showMessageDialog(null, f.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "⚠ Funcionário não encontrado.");
    }

    private static void pesquisarPorNome() {
        String nome = JOptionPane.showInputDialog("Digite parte do nome:").toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (Funcionario f : listaFuncionarios) {
            if (f.getNome().toLowerCase().contains(nome)) {
                sb.append(f).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "⚠ Nenhum funcionário encontrado.");
    }

    private static void editarFuncionario() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do funcionário:"));
        for (Funcionario f : listaFuncionarios) {
            if (f.getCodigo() == codigo) {
                String nome = JOptionPane.showInputDialog("Novo nome (atual: " + f.getNome() + "):");
                if (nome != null && !nome.isEmpty()) f.setNome(nome);

                String cargo = JOptionPane.showInputDialog("Novo cargo (atual: " + f.getCargo() + "):");
                if (cargo != null && !cargo.isEmpty()) f.setCargo(cargo);

                String salarioStr = JOptionPane.showInputDialog("Novo salário (atual: " + f.getSalario() + "):");
                if (salarioStr != null && !salarioStr.isEmpty()) f.setSalario(Double.parseDouble(salarioStr));

                JOptionPane.showMessageDialog(null, "✅ Funcionário atualizado!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "⚠ Funcionário não encontrado.");
    }

    private static void removerFuncionario() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código:"));
        listaFuncionarios.removeIf(f -> f.getCodigo() == codigo);
        JOptionPane.showMessageDialog(null, "✅ Funcionário removido (se existia).");
    }

    private static void funcionarioMaiorSalario() {
        if (listaFuncionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Nenhum funcionário cadastrado.");
            return;
        }
        Funcionario maior = listaFuncionarios.get(0);
        for (Funcionario f : listaFuncionarios) {
            if (f.getSalario() > maior.getSalario()) maior = f;
        }
        JOptionPane.showMessageDialog(null, "💰 Funcionário com maior salário:\n" + maior);
    }

    private static void funcionarioMenorSalario() {
        if (listaFuncionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Nenhum funcionário cadastrado.");
            return;
        }
        Funcionario menor = listaFuncionarios.get(0);
        for (Funcionario f : listaFuncionarios) {
            if (f.getSalario() < menor.getSalario()) menor = f;
        }
        JOptionPane.showMessageDialog(null, "💰 Funcionário com menor salário:\n" + menor);
    }

    private static void listarPorCargo() {
        String cargo = JOptionPane.showInputDialog("Digite o cargo:").toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (Funcionario f : listaFuncionarios) {
            if (f.getCargo().toLowerCase().equals(cargo)) {
                sb.append(f).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "⚠ Nenhum funcionário encontrado.");
    }

    private static void calcularMediaSalarial() {
        if (listaFuncionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Nenhum funcionário cadastrado.");
            return;
        }
        double soma = 0;
        for (Funcionario f : listaFuncionarios) {
            soma += f.getSalario();
        }
        double media = soma / listaFuncionarios.size();
        JOptionPane.showMessageDialog(null, "📊 Média salarial: R$ " + media);
    }
}