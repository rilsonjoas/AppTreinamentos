package negocio;

public class Fachada {

    private static Fachada instanciaUnica;

    public static Fachada getInstanciaUnica() {
        if (instanciaUnica == null) {
            instanciaUnica = new Fachada();
        }
        return instanciaUnica;
    }

    private ControladorTreino controleTreino;


    public void adicionarExercicio(Exercicio exercicio) {
        controleTreino.adicionarExercicio(exercicio);
    }

    public void removerExercicio(Exercicio exercicio) {
        controleTreino.removerExercicio(exercicio);
    }
}
