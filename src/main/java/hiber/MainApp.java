package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;

public class MainApp {
    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User2", "Lastname2", "user2@mail.ru");

        Car car1 = new Car("Lambo", 2000);
        Car car2 = new Car("Lada", 2110);
        Car car3 = new Car("BMW", 5);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
            System.out.println();
        }
        try {
            System.out.println(userService.findUserByCar(car3));
            System.out.println();
        } catch (NoResultException e) {
            System.out.println("User not found");
            System.out.println();
        }

        context.close();
    }
}
