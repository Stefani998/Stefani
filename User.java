public class User {

    private int id;
    private String name;
    private String userName;
    private String email;
    private String website;

    // Só é necessário a latitude para a questão
    private String latitude;

    public User(int id, String name, String userName, String email, String website, String latitude) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.website = website;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    /**
     * Retorna se o usuario está no hemisfério sul
     *
     * @return true se sim, senão false
     */
    public boolean isFromSouthHemisphere() {
        double lat = Double.parseDouble(latitude);
        if (lat < 0) {
            return true;
        }
        return false;
    }
}
