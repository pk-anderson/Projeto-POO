public class CalculadorMusica extends Calculador {

    private Musica musica;

    public CalculadorMusica(double distancia, Musica musica) {
        super(distancia);
        this.musica = musica;
    }
    
    public int getCalculoMusicas(double vel) {
        double velocidade = super.getVelocidadeMs(vel);
        double tempoDeslocamento = super.getDistancia(UnidadeMedida.METROS)/velocidade;
        double numeroMusicas = tempoDeslocamento / musica.getTempoEmSegundos();
        return (int) numeroMusicas;
    }

}