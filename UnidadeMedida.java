public enum UnidadeMedida implements CalculoUnidadeMedida {
        KILOMETROS {
            public double getDistanciaConvertida(double distancia) {
                return distancia;
            }
        },
        METROS {
            public double getDistanciaConvertida(double distancia) {
                return distancia*1000;
            }
        },
        CENTIMETROS {
            public double getDistanciaConvertida(double distancia) {
                return distancia*100000;
            }
        },
        MILIMETROS {
            public double getDistanciaConvertida(double distancia) {
                return distancia*1000000;
            }
        };
    }