/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lauanesouza
 */

import java.time.LocalDateTime;

public class Entrega {

    private Aluno aluno;
    private Atividade atividade;
    private String conteudo;
    private LocalDateTime dataEntrega;
    private StatusEntrega status;
    private Avaliacao avaliacao;

    public Entrega(Aluno aluno, Atividade atividade, String conteudo) {

        if (aluno == null) {
            throw new IllegalArgumentException(
                "O aluno e obrigatorio.");
        }

        if (atividade == null) {
            throw new IllegalArgumentException(
                "A atividade e obrigatoria.");
        }

        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O conteudo da entrega e obrigatorio.");
        }

        this.aluno = aluno;
        this.atividade = atividade;
        this.conteudo = conteudo;
        this.dataEntrega = LocalDateTime.now();
        this.status = StatusEntrega.ENTREGUE;
        this.avaliacao = null;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void registrarAvaliacao(Avaliacao avaliacao) {

        if (this.status == StatusEntrega.AVALIADA) {
            throw new IllegalStateException(
                "Esta entrega ja foi avaliada.");
        }

        if (avaliacao == null) {
            throw new IllegalArgumentException(
                "A avaliacao nao pode ser nula.");
        }

        this.avaliacao = avaliacao;
        this.status = StatusEntrega.AVALIADA;
    }
}





