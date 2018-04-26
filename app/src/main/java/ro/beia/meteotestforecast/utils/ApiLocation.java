package ro.beia.meteotestforecast.utils;

public class ApiLocation {
    private String city, api_url;


    public ApiLocation(String city, String api_url) {
        this.city = city;
        this.api_url = api_url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }
}
