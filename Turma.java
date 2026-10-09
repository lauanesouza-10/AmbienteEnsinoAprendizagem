/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lauanesouza
 */


import java.util.ArrayList;
import java.util.List;

public class Turma {

    private String codigo;
    private Disciplina disciplina;
    private Professor professorResponsavel;
    private List<Matricula> matriculas;
    private List<Atividade> atividades;

    public Turma(String codigo, Disciplina disciplina,
                 Professor professorResponsavel) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O codigo da turma e obrigatorio.");
        }

        if (disciplina == null || professorResponsavel == null) {
            throw new IllegalArgumentException(
                "Disciplina e professor sao obrigatorios.");
        }

        this.codigo = codigo;
        this.disciplina = disciplina;
        this.professorResponsavel = professorResponsavel;
        this.matriculas = new ArrayList<>();
        this.atividades = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }

    public List<Matricula> getMatriculas() {
        return new ArrayList<>(matriculas);
    }

    public List<Atividade> getAtividades() {
        return new ArrayList<>(atividades);
    }

    public void adicionarMatricula(Matricula matricula) {

        if (matricula == null || matricula.getTurma() != this) {
            throw new IllegalArgumentException(
                "A matricula deve pertencer a esta turma.");
        }

        for (Matricula m : matriculas) {
            if (m.getAluno() == matricula.getAluno()) {
                throw new IllegalArgumentException(
                    "O aluno ja possui matricula nesta turma.");
            }
        }

        matriculas.add(matricula);
    }

    public boolean possuiMatriculaAtiva(Aluno aluno) {

        for (Matricula matricula : matriculas) {
            if (matricula.getAluno() == aluno
                    && matricula.estaAtiva()) {
                return true;
            }
        }

        return false;
    }

    public List<Atividade> consultarAtividades(Aluno aluno) {

        if (!possuiMatriculaAtiva(aluno)) {
            throw new IllegalStateException(
                "Aluno sem matricula ativa nesta turma.");
        }

        return new ArrayList<>(atividades);
    }

    public void publicarAtividade(Professor professor,
                                  Atividade atividade) {

        if (professor == null ||
                professor != professorResponsavel) {
            throw new IllegalArgumentException(
                "Somente o professor responsavel pode publicar atividades.");
        }

        if (atividade == null || atividade.getTurma() != this) {
            throw new IllegalArgumentException(
                "A atividade deve pertencer a esta turma.");
        }

        if (!atividade.estaAberta()) {
            throw new IllegalStateException(
                "Nao e possivel publicar uma atividade encerrada.");
        }

        if (atividades.contains(atividade)) {
            throw new IllegalArgumentException(
                "Esta atividade ja foi publicada.");
        }

        atividades.add(atividade);

        System.out.println(
            "Atividade publicada com sucesso: "
            + atividade.getTitulo());
    }
}
