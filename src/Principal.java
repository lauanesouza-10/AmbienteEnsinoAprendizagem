/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lauanesouza
 */


import java.time.LocalDateTime;

public class Principal {

    public static void main(String[] args) {

        System.out.println("=== AMBIENTE DE ENSINO-APRENDIZAGEM ===");

        
        Professor professor = new Professor(
                "Carlos", "carlos@escola.com");

        Professor outroProfessor = new Professor(
                "Roberto", "roberto@escola.com");

        Aluno aluno = new Aluno(
                "Ana", "ana@escola.com");

        Aluno alunoSemMatricula = new Aluno(
                "Pedro", "pedro@escola.com");

        
        Disciplina disciplina = new Disciplina(
                "Programacao Orientada a Objetos", "POO101");

        Turma turma = new Turma(
                "TURMA01", disciplina, professor);

    
        Matricula matricula = new Matricula(aluno, turma);
        turma.adicionarMatricula(matricula);

        System.out.println("\n1. MATRICULA");
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Status: " + matricula.getStatus());

      
        Atividade atividade = new Atividade(
                "Exercicio de Java",
                "Criar um programa usando classes e objetos.",
                LocalDateTime.now().plusDays(2),
                10.0,
                turma
        );

        System.out.println("\n2. PUBLICACAO DA ATIVIDADE");
        turma.publicarAtividade(professor, atividade);

        System.out.println("Status: " + atividade.getStatus());
        System.out.println("Prazo: " + atividade.getPrazo());

     
        System.out.println("\n3. CONSULTA DE ATIVIDADES");

        for (Atividade a : turma.consultarAtividades(aluno)) {
            System.out.println("Atividade: " + a.getTitulo());
            System.out.println("Descricao: " + a.getDescricao());
        }

      
        System.out.println("\n4. ENTREGAS DO ALUNO");

        Entrega primeiraEntrega = new Entrega(
                aluno, atividade, "Primeira versao do exercicio");

        atividade.adicionarEntrega(primeiraEntrega);

        Entrega segundaEntrega = new Entrega(
                aluno, atividade, "Versao corrigida do exercicio");

        atividade.adicionarEntrega(segundaEntrega);

        System.out.println("Total de entregas: "
                + atividade.getEntregas().size());

        System.out.println("Ultima entrega: "
                + atividade.getUltimaEntrega(aluno).getConteudo());

        
        System.out.println("\n5. TESTE DE PROFESSOR NAO AUTORIZADO");

        try {
            atividade.avaliarEntrega(
                    outroProfessor,
                    aluno,
                    8.0,
                    "Bom trabalho"
            );

        } catch (Exception e) {
            System.out.println(
                    "Acesso bloqueado: " + e.getMessage());
        }

       
        System.out.println("\n6. TESTE DE ALUNO SEM MATRICULA");

        try {
            turma.consultarAtividades(alunoSemMatricula);

        } catch (Exception e) {
            System.out.println(
                    "Acesso bloqueado: " + e.getMessage());
        }

        try {
            Entrega entregaInvalida = new Entrega(
                    alunoSemMatricula,
                    atividade,
                    "Minha resposta"
            );

            atividade.adicionarEntrega(entregaInvalida);

        } catch (Exception e) {
            System.out.println(
                    "Entrega bloqueada: " + e.getMessage());
        }

      
        System.out.println("\n7. AVALIACAO DA ULTIMA ENTREGA");

        atividade.avaliarEntrega(
                professor,
                aluno,
                9.5,
                "Excelente trabalho! Codigo bem organizado."
        );

        
        System.out.println("\n8. RESULTADO DA AVALIACAO");

        Entrega ultimaEntrega = atividade.getUltimaEntrega(aluno);

        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Status: " + ultimaEntrega.getStatus());
        System.out.println("Nota: "
                + ultimaEntrega.getAvaliacao().getNota());
        System.out.println("Feedback: "
                + ultimaEntrega.getAvaliacao().getFeedback());

       
        System.out.println("\n9. HISTORICO DE ENTREGAS");

        for (Entrega entrega : atividade.getEntregas()) {
            System.out.println(
                    "Conteudo: " + entrega.getConteudo());

            System.out.println(
                    "Status: " + entrega.getStatus());
        }

        
        System.out.println("\n10. TESTE DE MATRICULA INATIVA");

        matricula.inativar();

        try {
            turma.consultarAtividades(aluno);

        } catch (Exception e) {
            System.out.println(
                    "Acesso bloqueado: " + e.getMessage());
        }

    
        System.out.println("\n11. TESTE DE PRAZO ENCERRADO");

        
        matricula.ativar();

        Atividade atividadePrazo = new Atividade(
                "Atividade com prazo curto",
                "Teste de bloqueio por prazo vencido",
                LocalDateTime.now().plusSeconds(1),
                10.0,
                turma
        );

        turma.publicarAtividade(professor, atividadePrazo);

        try {
          
            Thread.sleep(2000);

            Entrega entregaAtrasada = new Entrega(
                    aluno,
                    atividadePrazo,
                    "Tentativa de entrega fora do prazo"
            );

            atividadePrazo.adicionarEntrega(entregaAtrasada);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Teste interrompido.");

        } catch (IllegalStateException e) {
            System.out.println(
                    "Entrega bloqueada: " + e.getMessage());
        }

        System.out.println(
                "Status da atividade: "
                + atividadePrazo.getStatus());

        System.out.println("\n=== FIM DA DEMONSTRACAO ===");
    }
}
