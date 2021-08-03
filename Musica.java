public class Musica {
    
    private double tempo;
    private String nome;

    public Musica(){}

    public Musica(int tempo, String nome) {
        this.tempo = tempo;
        this.nome = nome;
    }
    
    public Musica (double tempoMinutos, String nome){
        this.tempo = tempoMinutos*60000;
        this.nome = nome;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
    
    public double getTempoEmSegundos() {
        return this.tempo/1000;
    }
    
    public double getTempoEmMinutos() {
        return this.tempo/60000;
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
