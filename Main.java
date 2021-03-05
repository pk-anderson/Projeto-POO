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

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o artista: ");
        String artista = sc.nextLine();

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
          System.out.println(musica);
        } 
      
      float distancia = 100000f;
      float tempo = (float) 0;
      float velocidade = 10;

      for(Musica musica : musicas){
          Calculador calculador = new Calculador(musica,distancia);
          System.out.println(calculador.numeroMusicas(10));
          tempo = tempo + musica.getTempoEmSegundos();
      }
      float mediaTempoMusicas = (float) tempo/10;  
      float deslocamento = (float) distancia/velocidade;
      float mediaMusicas = (float) deslocamento/mediaTempoMusicas;
      int resultado = (int) mediaMusicas;
      
      System.out.println ("O usuario podera ouvir uma media de "+resultado+" musicas do artista "+ busca.getName()+ " em seu trajeto.");
    }
}