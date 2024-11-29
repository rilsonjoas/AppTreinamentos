import java.util.*;

public class Treino {
    private UUID id;
    private String nome;
    private String tipoDeTreino;
    private int duracao;
    private int nivelDeDificuldade;
    private Usuario usuario;
    private ArrayList<Exercicio> exercicios;
    private double caloriasQueimadas;
    private double progresso;
    private boolean concluido;

    // Construtor default
    public Treino() {
        this.id = UUID.randomUUID();
        this.exercicios = new ArrayList<>(); //Cria uma lista de exercícios associadas a este treino
        this.concluido = false;
        this.caloriasQueimadas = 0.0;
        this.progresso = 0.0;
    }

    // Construtor com parâmetros
    public Treino(String nome, String tipoDeTreino, int duracao, int nivelDeDificuldade, Usuario usuario) {
        this.nome = nome;
        this.tipoDeTreino = tipoDeTreino;
        this.duracao = duracao;
        this.nivelDeDificuldade = nivelDeDificuldade;
        this.usuario = usuario;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDeTreino() {
        return tipoDeTreino;
    }

    public void setTipoDeTreino(String tipoDeTreino) {
        this.tipoDeTreino = tipoDeTreino;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getNivelDeDificuldade() {
        return nivelDeDificuldade;
    }

    public void setNivelDeDificuldade(int nivelDeDificuldade) {
        this.nivelDeDificuldade = nivelDeDificuldade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }

    public double getCaloriasQueimadas() {
        return caloriasQueimadas;
    }

    public double getProgresso() {
        return progresso;
    }

    public boolean isConcluido() {
        return concluido;
    }

    // Métodos que precisaremos para a classe Treino
    public void adicionarExercicio(Exercicio exercicio) {
        if (exercicio != null && !exercicios.contains(exercicio)) {
            exercicios.add(exercicio);
            calcularCaloriasQueimadas();
            atualizarProgresso();
            //Assim que um exercício é concluído, adicionamos ao progresso e atualizamos as calorias queimadas.
        }
    }

    public void removerExercicio(Exercicio exercicio) {
        if (exercicios.remove(exercicio)) {
            calcularCaloriasQueimadas();
            atualizarProgresso();
            //Assim que um exercício é removido, atualizamos o progresso e as calorias queimadas.
        }
    }

    // Calcular as calorias queimadas com base nos exercícios
    public double calcularCaloriasQueimadas() {
        caloriasQueimadas = 0;
        for (Exercicio exercicio : exercicios) {
            caloriasQueimadas += exercicio.calcularCaloriasQueimadas();
        }
        return caloriasQueimadas;
    }

    // Atualiza a taxa de progresso geral do treino
    public void atualizarProgresso() {
        if (exercicios.isEmpty()) {
            progresso = 0.0;
            concluido = false;
            return;
        } //Se não foi concluído nenhum exercício, retorna um progresso 0.

        //Conta a quantidade de exercícios realizados:
        long exerciciosConcluidos = exercicios.stream()
                .filter(Exercicio::isConcluido)
                .count();

        //Baseado na quantidade de exercícios concluídos, atribui um progresso.
        progresso = (exerciciosConcluidos / (double) exercicios.size()) * 100.0;
        concluido = progresso == 100.0;
    }

    @Override
    public String toString() {
        return "Treino{" +
                "nome='" + nome + '\'' +
                ", tipoDeTreino='" + tipoDeTreino + '\'' +
                ", duracao=" + duracao +
                ", nivelDeDificuldade=" + nivelDeDificuldade +
                ", progresso=" + progresso +
                ", concluido=" + concluido +
                '}';
    }
}