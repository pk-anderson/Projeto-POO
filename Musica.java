public class Musica {
    
    private float tempo;
    private String nome;

    public Musica(int tempo, String nome) {
        this.tempo = tempo;
        this.nome = nome;
    }
    
    public Musica (float tempoMinutos, String nome){
        this.tempo = tempoMinutos*60000;
        this.nome = nome;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    
    public float getTempoEmSegundos() {
        return this.tempo/1000;
    }
    
    public float getTempoEmMinutos() {
        return (float) this.tempo/60000;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
      this.nome = nome;
    }

    public String toString() {
      return this.nome+" - "+this.tempo;
    }
}
