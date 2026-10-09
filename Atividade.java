/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lauanesouza
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atividade {

    private String titulo;
    private String descricao;
    private LocalDateTime prazo;
    private double valorMaximo;
    private StatusAtividade status;
    private Turma turma;
    private List<Entrega> entregas;

    public Atividade(String titulo, String descricao,
                     LocalDateTime prazo, double valorMaximo,
                     Turma turma) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O titulo da atividade e obrigatorio.");
        }

        if (prazo == null || !prazo.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                "O prazo deve ser uma data futura.");
        }

        if (valorMaximo <= 0) {
            throw new IllegalArgumentException(
                "O valor maximo deve ser maior que zero.");
        }

        if (turma == null) {
            throw new IllegalArgumentException(
                "A turma e obrigatoria.");
        }

        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.valorMaximo = valorMaximo;
        this.turma = turma;
        this.status = StatusAtividade.ABERTA;
        this.entregas = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public StatusAtividade getStatus() {
        atualizarStatus();
        return status;
    }

    public Turma getTurma() {
        return turma;
    }

    public List<Entrega> getEntregas() {
        return new ArrayList<>(entregas);
    }

    public void atualizarStatus() {
        if (!LocalDateTime.now().isBefore(prazo)) {
            status = StatusAtividade.ENCERRADA;
        }
    }

    public boolean estaAberta() {
        atualizarStatus();
        return status == StatusAtividade.ABERTA;
    }

    public void adicionarEntrega(Entrega entrega) {

        if (entrega == null || entrega.getAtividade() != this) {
            throw new IllegalArgumentException(
                "Entrega invalida para esta atividade.");
        }

        if (!turma.getAtividades().contains(this)) {
            throw new IllegalStateException(
                "Esta atividade ainda nao foi publicada.");
        }

        if (!turma.possuiMatriculaAtiva(entrega.getAluno())) {
            throw new IllegalStateException(
                "Aluno sem matricula ativa nesta turma.");
        }

        if (!estaAberta()) {
            throw new IllegalStateException(
                "O prazo terminou. Novas entregas estao bloqueadas.");
        }

        if (entrega.getStatus() != StatusEntrega.ENTREGUE) {
            throw new IllegalStateException(
                "A entrega deve estar no estado ENTREGUE.");
        }

        if (entregas.contains(entrega)) {
            throw new IllegalArgumentException(
                "Esta entrega ja foi registrada.");
        }

        entregas.add(entrega);

        System.out.println(
            "Entrega registrada com sucesso para o aluno: "
            + entrega.getAluno().getNome());
    }

    public Entrega getUltimaEntrega(Aluno aluno) {

        Entrega ultimaEntrega = null;

        for (Entrega entrega : entregas) {
            if (entrega.getAluno() == aluno) {
                ultimaEntrega = entrega;
            }
        }

        return ultimaEntrega;
    }

    public void avaliarEntrega(Professor professor, Aluno aluno,
                               double nota, String feedback) {

        if (professor == null ||
                professor != turma.getProfessorResponsavel()) {
            throw new IllegalArgumentException(
                "Somente o professor responsavel pode avaliar.");
        }

        if (!turma.getAtividades().contains(this)) {
            throw new IllegalStateException(
                "A atividade ainda nao foi publicada.");
        }

        Entrega ultimaEntrega = getUltimaEntrega(aluno);

        if (ultimaEntrega == null) {
            throw new IllegalStateException(
                "O aluno ainda nao realizou nenhuma entrega.");
        }

        if (ultimaEntrega.getStatus() == StatusEntrega.AVALIADA) {
            throw new IllegalStateException(
                "A ultima entrega ja foi avaliada.");
        }

        Avaliacao avaliacao = new Avaliacao(
                professor, nota, feedback, valorMaximo);

        ultimaEntrega.registrarAvaliacao(avaliacao);

        System.out.println(
                "Avaliacao registrada para: " + aluno.getNome());
        System.out.println("Nota: " + nota);
        System.out.println("Feedback: " + feedback);
    }
}
