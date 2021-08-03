import com.wrapper.spotify.SpotifyApi;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws ParseException, SpotifyWebApiException, IOException {
        SpotifyApi spotifyApi = new SpotifyApi.Builder().
                setClientId("4f652c9cc6284a04aebcefbb5788c7fc").
                setClientSecret("33127c66d2564a56a845194efbfe46b9").
                build();

        ClientCredentials clientCredentials = spotifyApi.clientCredentials().build().execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());

        Scanner sc1 = new Scanner(System.in);

      //Teste de métodos getTempo

      while (true) {
            System.out.println("Tempo da musica em minutos: ");
            float tempoMusica = (float) sc1.nextFloat();
            Musica musica1 = new Musica(tempoMusica, "nome");

            System.out.println("Escolha a opção de tempo da música: ");
            System.out.println("1 - milissegundos");
            System.out.println("2 - segundos");
            System.out.println("3 - minutos");
            int opcao = sc1.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println(musica1.getTempo());
                    break;
                case 2:
                    System.out.println(musica1.getTempoEmSegundos());
                    break;
                case 3:
                    System.out.println(musica1.getTempoEmMinutos());
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
            
            System.out.println("Deseja continuar? ");
            System.out.println("1 - Sim ");
            System.out.println("2 - Não ");
            int continuarScanner = sc1.nextInt();
            if (continuarScanner==1) {
                continue;
            } else {
                break;
            }
          }

        // Coletar o artista cujos dados são necessários
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Digite o artista: ");
        String artista = sc2.nextLine();
        System.out.println("--------------------------------------------");

        SearchArtistsRequest getArtistRequest = spotifyApi.searchArtists(artista).build();
        Artist busca = getArtistRequest.execute().getItems()[0];

        GetArtistsTopTracksRequest requisicaoTopTracks = spotifyApi.getArtistsTopTracks(busca.getId(), CountryCode.BR).build();

        System.out.println("As musicas mais ouvidas são: ");
        List<Musica> musicas = new LinkedList<>();
        Track[] tracks = requisicaoTopTracks.execute();
        for (Track track : tracks) {
            Musica musica = new Musica(track.getDurationMs(), track.getName());
            musicas.add(musica);
        }
        for (Musica musica : musicas) {
          System.out.println(musica.getNome());
        }

        System.out.println("--------------------------------------------"); 
      
      Scanner sc3 = new Scanner(System.in);
      System.out.println("Qual o ponto de origem?");
      String origem = sc3.nextLine();
      System.out.println("Qual o ponto de destino?");
      String destino = sc3.nextLine();

      RouteCalculator calculadorDistancia = new RouteCalculator(origem,destino); 
      
      System.out.println("Qual a velocidade em km/h?");
      double velocidade = sc3.nextDouble();

      for(Musica musica : musicas){
          CalculadorMusica calculador = new CalculadorMusica(calculadorDistancia.getDistance(), musica);
          System.out.println("O seu trajeto equivale a "+calculador.getCalculoMusicas(velocidade)+" vezes a música "+ musica.getNome());
      }
        
      CalculadorMedia media = new CalculadorMedia(calculadorDistancia.getDistance(), musicas);
      
      System.out.println("--------------------------------------------");
      System.out.println ("Você poderá ouvir uma media de "+media.getCalculoMusicas(velocidade)+" musicas do artista "+ busca.getName()+ " em seu trajeto. Boa viagem!");
    }
}