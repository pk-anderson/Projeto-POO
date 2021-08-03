import java.util.List;

public class CalculadorMedia extends Calculador {
  
  private List<Musica> musicas;
  private double tempo;

  public CalculadorMedia(double distancia, List musicas){
    super(distancia);
    this.musicas = musicas;
    this.tempo = 0;
  }

  public double getMediaTempo() {
     for(Musica musica : musicas){
          this.tempo = this.tempo + musica.getTempoEmSegundos();
      }
     return this.tempo/10; 
  }

  public int getCalculoMusicas(double vel) {
    double velocidade = super.getVelocidadeMs(vel);
    double tempoDeslocamento = super.getDistancia(UnidadeMedida.METROS)/velocidade;
    double mediaMusicas = tempoDeslocamento/this.getMediaTempo();
    return (int) mediaMusicas;
  }

}