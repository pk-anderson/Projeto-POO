public abstract class Calculador {
    
    protected double distancia;

    public Calculador(double distancia) {
        this.distancia = distancia;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getDistancia(UnidadeMedida um) {
        return um.getDistanciaConvertida(this.distancia);
    }

    public double getVelocidadeMs(double vel) {
      return vel/3.6;
    }

    public abstract int getCalculoMusicas(double vel);

}