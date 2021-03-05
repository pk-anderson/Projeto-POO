public class Calculador {
    private Musica musica;
    private float distancia;

    public Calculador(Musica musica, float distancia) {
        this.musica = musica;
        this.distancia = distancia;
    }
    
    public int numeroMusicas(float velocidade) {
        float tempoDeslocamento = this.distancia/velocidade;
        float numeroMusicas = tempoDeslocamento/musica.getTempoEmSegundos();
        return (int) numeroMusicas;
    }
}
