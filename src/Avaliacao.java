/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lauanesouza
 */

public class Avaliacao {

    private Professor professor;
    private double nota;
    private String feedback;

    public Avaliacao(Professor professor, double nota,
                     String feedback, double valorMaximo) {

        if (professor == null) {
            throw new IllegalArgumentException(
                "O professor e obrigatorio.");
        }

        if (Double.isNaN(nota) || Double.isInfinite(nota)
                || nota < 0 || nota > valorMaximo) {
            throw new IllegalArgumentException(
                "A nota deve estar entre 0 e " + valorMaximo);
        }

        if (feedback == null || feedback.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O feedback e obrigatorio.");
        }

        this.professor = professor;
        this.nota = nota;
        this.feedback = feedback;
    }

    public Professor getProfessor() {
        return professor;
    }

    public double getNota() {
        return nota;
    }

    public String getFeedback() {
        return feedback;
    }
}

