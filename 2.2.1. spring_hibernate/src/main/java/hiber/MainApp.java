package hiber;
import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.addUser(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.addUser(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.addUser(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.addUser(new User("User4", "Lastname4", "user4@mail.ru"));

        userService.addUser(new User("Name","LastName","email").
                setCar(new Car("BMW",2014)));
        userService.addUser(new User("Name2","LastName2","email2").
                setCar(new Car("Mercedes",2018)));
        userService.addUser(new User("Name3","LastName3","email3").
                setCar(new Car("Жигули",1995)));


        List<User> users = userService.getUserList();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car Model = " + user.getCar().getModel());
                System.out.println("Car Series = " + user.getCar().getSeries());
            } else {
                System.out.println("Car = No car assigned");
            }
            System.out.println();
        }

        userService.findUserCars("Mercedes",2014);
        userService.findUserCars("BMW",2018);
        userService.findUserCars("Жигули",1995);
        context.close();
    }
}
