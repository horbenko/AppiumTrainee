package data;

public enum User {

    VALID_USER(
            "Имя Фамилия",
            "my_test_mail@meta.ua",
            "mytestpassword"
    );

    public String name;
    public String email;
    public String password;

    User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}