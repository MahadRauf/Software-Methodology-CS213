package project1;
/**
 * Representation of clinic locations as an enum. Enum values are counties that have constants city and zip that
 * together represent a clinic.
 * @author Mahad Rauf, Moeez Shahid
 */
public enum Location {
    SOMERSET("Bridgewater", 8807),
    MIDDLESEX("Piscataway", 8854),
    MERCER("Princeton",8542),
    MORRIS("Morristown",7960),
    UNION("Union",7083);
    public final String CITY; // The city in the county
    public final int ZIP; // zip code of the city

    /**
     * Constructor for java to initialize constants relating to each county. Namely, the city and zip.
     * @param CITY city that the clinic is in
     * @param ZIP zip code of clinic's city
     */
    Location(String CITY, int ZIP) {
        this.CITY = CITY;
        this.ZIP = ZIP;
    }

    /**
     * Overrides toString() with another appropriate for the purposes of this project
     * @return returns location as String in form "CITY ZIP, COUNTY"
     */
    @Override
    public String toString(){
        String zip = String.format("%05d", this.ZIP);
        String ret = this.CITY + " " + zip + ", " + this.name();
        return ret;
    }

}
