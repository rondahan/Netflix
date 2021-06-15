import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String name, u_name, password, formatedCreated, formatedMembershipEnd;
    private LocalDate created, membershipEnd;
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private HashMap<TvShow, ArrayList<String>> shows_watched;

    public User() {}
    public User(String name, String u_name, String password, int monthsOfMembership) {
        this.name = name;
        this.u_name = u_name;
        this.password = password;
        this.created = LocalDate.now();
        this.formatedCreated = created.format(formater);
        this.membershipEnd = created.plusMonths(monthsOfMembership);
        this.formatedMembershipEnd = membershipEnd.format(formater);
        this.shows_watched = new HashMap<TvShow, ArrayList<String>>();
    }


    public User(User user) {

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getU_name() {
        return u_name;
    }
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
    public String getPassword() {
        return password;
    }
    public String getFormatedCreated() {
        return formatedCreated;
    }
    public String getFormatedMembershipEnd() {
        return formatedMembershipEnd;
    }
    public HashMap<TvShow, ArrayList<String>> getShows_watched() {
        return shows_watched;
    }

    public void display_watched_shows() {
        for (TvShow key : shows_watched.keySet()) {
            System.out.println(key.getName());
            System.out.println("Last episode: " + shows_watched.get(key).get(shows_watched.get(key).size()-1));
            //ניגשים עבור כל סדרה, לפרק האחרון ברשימת פרקים שלה
        }
    }

    public void view_member_details() {
        int totalShows = shows_watched.size(), showsFinished = 0, totalEpisodes = 0;
        //כמה סדרות סיים: רצים על הסדרות שלו, ומשווים את הפרק האחרון שהוא צפה, לפרק האחרון בסדרה
        for (TvShow key : shows_watched.keySet()) {//עבור כל סדרה שצפה בה, בודקים את הפרק האחרון
            ArrayList<String> a = shows_watched.get(key);//רשימת הפרקים שצפה בסדרה הנוכחית
            totalEpisodes += a.size(); //סוכם את כמות הפרקים שנצפו בכל סדרה
            String lastEpisode = a.get(a.size()-1);//הפרק האחרון בסדרה
            if (key.getEpisodes().get(key.getEpisodes().size()-1).getName().equals(lastEpisode)) {//אם הם שווים, זה אומר שהמשתמש סיים את הסדרה
                showsFinished ++;
            }
        }

        System.out.println(toString());//להדפיס את הפרטים
        System.out.println("Total shows watched " + totalShows);
        System.out.println("Total episodes watched " + totalEpisodes);
        System.out.println("Total shows finished " + showsFinished);
    }

    public void add_to_watched(TvShow show, String episode) {
        if (!shows_watched.containsKey(show)) {
            shows_watched.put(show, new ArrayList<String>());
        }
        shows_watched.get(show).add(episode);
    }

    @Override
    public String toString() {
        return "Hello, this is user info:\n" +
                String.format("Name: %s\n", name) +
                String.format("User name: %s\n", u_name) +
                String.format("Joined at: %s\n", formatedCreated) +
                String.format("Ending in: %s\n", formatedMembershipEnd) ;
    }

}
