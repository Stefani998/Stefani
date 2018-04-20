import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Stefani {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private static final String SAMANTHA = "Samantha";

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();

        try {
            userList = getUsersFromUrl(URL);
        } catch (IOException e) {
            System.out.println("Erro ao tentar obter a lista de usuários");
        }

        // Verifica se foi possível obter algum usuário
        if (!userList.isEmpty()) {

            // Fazendo de forma com que em um unico for obtenha todas as informações.
            int southCount = 0;
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                System.out.println("1) Usuario: " + user.getName() + " -> website: " + user.getWebsite());
                if (user.getUserName().equals(SAMANTHA)) {
                    System.out.println("2) Email do usuario com username Samantha: " + user.getEmail());
                }
                if (user.isFromSouthHemisphere()) {
                    southCount++;
                }
            }
            System.out.println("3) Usuarios do hemisferio sul: " + southCount);
        }
    }

    /**
     * Obtem lista de objetos User (em JSON) a partir de uma URL
     *
     * @param url URL a ser acessada
     * @return lista de usuários
     * @throws IOException
     * @throws JSONException
     */
    private static List<User> getUsersFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);

            List<User> users = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                // Le cada objeto do array do JSON e transforma em um objeto User, adicionando-o a lista local
                JSONObject userObject = json.getJSONObject(i);
                int id = userObject.getInt("id");
                String name = userObject.getString("name");
                String userName = userObject.getString("username");
                String email = userObject.getString("email");
                String website = userObject.getString("website");

                String latitude = userObject.getJSONObject("address").getJSONObject("geo").getString("lat");

                User user = new User(id, name, userName, email, website, latitude);
                users.add(user);
            }

            return users;
        } finally {
            is.close();
        }
    }

    /**
     * Transforma objeto Reader em String
     *
     * @param rd
     * @return JSON em String
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
