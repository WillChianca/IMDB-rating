import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App 
{   
    public static void main(String[] args) throws Exception 
    {
        // Get the data of the json file, using the url
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requisition = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse <String> answer = client.send(requisition, BodyHandlers.ofString());
        String json = answer.body();

        // Extract some of the movies data, such as name, raking, image
        var jsonparser = new parser();
        List<Map<String, String>> movieslist = jsonparser.parse(json); 

        // Print the data and manipulate it
        for (Map<String,String> movie : movieslist)
        {
            System.out.println("\u001b[38;2;208;255;00mTitulo -> \u001b[m\u001b[4m" + movie.get("fullTitle") + "\u001b[m");
            System.out.println("\u001b[38;2;208;255;00m IMDB Rating -> \u001b[m\u001b[1m" + movie.get("imDbRating") + "\u001b[m");

            String str_rate = movie.get("imDbRating");
            Float rating = Float.valueOf(str_rate);
            for (int i = 0; i < rating - 1; i++)
            {
                System.out.printf(" ⭐ ");
            }
            System.out.println();

            System.out.println("\u001b[1mImagem -> \u001b[m" + movie.get("image"));
            System.out.println();
        }

        
    }
}