package de.asos.models;

public class UserDataModel {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String date;
    private final String month;
    private final String year;
    private final String sex;

    public UserDataModel(String email, String password, String firstName, String lastName, String date, String month, String year, String sex) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.month = month;
        this.year = year;
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getSex() {
        return sex;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
