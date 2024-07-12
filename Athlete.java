
import java.util.Date;


class Athlete {
    private String number;
    private String surname;
    private String name;
    private String id;
    private String nationality;
    private Date birthdate;
    private String gender;
    private String category;
    private double weight;
    private double height;
    private int finishedRaces;
    private double economicBudget;
    private int rank;
    private PhysicalCondition physicalCondition;

    public Athlete(String number, String surname, String name, String id, String nationality, Date birthdate,
                   String gender, String category, double weight, double height, int finishedRaces,
                   double economicBudget, int rank, PhysicalCondition physicalCondition) {
        this.number = number;
        this.surname = surname;
        this.name = name;
        this.id = id;
        this.nationality = nationality;
        this.birthdate = birthdate;
        this.gender = gender;
        this.category = category;
        this.weight = weight;
        this.height = height;
        this.finishedRaces = finishedRaces;
        this.economicBudget = economicBudget;
        this.rank = rank;
        this.physicalCondition = physicalCondition;
    }
    
    public String getNumber() {
        return number;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getBirthDate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getCategory() {
        return category;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getFinishedRaces() {
        return finishedRaces;
    }

    public double getEconomicBudget() {
        return economicBudget;
    }

    public int getRank() {
        return rank;
    }

    public PhysicalCondition getPhysicalCondition() {
        return physicalCondition;
    }
}

