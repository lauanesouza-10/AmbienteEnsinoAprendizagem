/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lauanesouza
 */

public class Matricula {

    private Aluno aluno;
    private Turma turma;
    private StatusMatricula status;

    public Matricula(Aluno aluno, Turma turma) {

        if (aluno == null) {
            throw new IllegalArgumentException(
                "O aluno e obrigatorio.");
        }

        if (turma == null) {
            throw new IllegalArgumentException(
                "A turma e obrigatoria.");
        }

        this.aluno = aluno;
        this.turma = turma;
        this.status = StatusMatricula.ATIVA;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public boolean estaAtiva() {
        return status == StatusMatricula.ATIVA;
    }

    public void inativar() {
        this.status = StatusMatricula.INATIVA;
    }

    public void ativar() {
        this.status = StatusMatricula.ATIVA;
    }
}
