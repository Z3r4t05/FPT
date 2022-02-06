
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class EastAsiaCountries extends Country implements Comparable<EastAsiaCountries> {

    private String countryTerrain;

    public EastAsiaCountries(String countryCode,
            String countryName, float totalArea, String countryTerrain) {
        super(countryCode, countryName, totalArea);
        this.countryTerrain = countryTerrain;
    }

    public EastAsiaCountries() {

    }

    public String getCountryTerrain() {
        return countryTerrain;
    }

    public void setCountryTerrain(String countryTerrain) {
        this.countryTerrain = countryTerrain;
    }

    @Override
    public void display() {
        System.out.println(this.getCountryCode() + "\n" + this.getCountryName());
    }

    @Override
    public int compareTo(EastAsiaCountries t) {
        return this.getCountryName().compareTo(t.getCountryName());
    }

}
