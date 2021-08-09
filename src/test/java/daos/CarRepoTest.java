package daos;

import models.Car;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CarRepoTest {
    @Test
    public void testCreate(){
        //given
        Car car = new Car(15, "Mitsubishi",
                "Lancer", 1998, "Black","ERD453");
        String expected = car.toString();
        //when
        Connection connection=main.ConnectionFactory.getConnection("mysql");
        CarRepo carRepo = new CarRepo(connection);
        carRepo.create(car);
        Car car1 = carRepo.findById(15);
        String actual = car1.toString();
        //then
        Assert.assertEquals(expected, actual);
        carRepo.delete(15);
    }

    @Test
    public void testFindAll() {
        //given
        Car car1 = new Car(15, "Mitsubishi",
                "Lancer", 1998, "Black","ERD453");
        Car car2 = new Car(25, "Toyota",
                "HighLander", 2015, "White","YTH453");
        List<Car> list = new ArrayList<>();
        list.add(car1);
        list.add(car2);
        String expected = list.toString();
        //when
        Connection connection = main.ConnectionFactory.getConnection("mysql");
        CarRepo carRepo = new CarRepo(connection);
        carRepo.create(car1);
        carRepo.create(car2);
        String actual = carRepo.findAll().toString();
        //then
        Assert.assertEquals(expected, actual);
        carRepo.delete(15);
        carRepo.delete(25);
    }

    @Test
    public void testFindById() {
        //given
        Car car = new Car(15, "Mitsubishi",
                "Lancer", 1998, "Black","ERD453");
        String expected = car.toString();
        //when
        Connection connection = main.ConnectionFactory.getConnection("mysql");
        CarRepo carRepo = new CarRepo(connection);
        carRepo.create(car);
        Car car1 = carRepo.findById(15);
        String actual = car1.toString();
        //then
        Assert.assertEquals(expected, actual);
        carRepo.delete(15);

    }

    @Test
    public void testUpdate() {
        //given
        Car car = new Car(15, "Mitsubishi",
                "Lancer", 1998, "Black","ERD453");
        Car newCar = new Car("Tata", "Innova", 2010, "Metallic Silver", "ERGH654");
        String expected = newCar.toString();
        //when
        Connection connection = main.ConnectionFactory.getConnection("mysql");
        CarRepo carRepo = new CarRepo(connection);
        carRepo.create(car);
        carRepo.update(15, newCar);
        Car car1 = carRepo.findById(15);
        String actual = car1.toString();
        //then
        Assert.assertEquals(expected, actual);
        carRepo.delete(15);
    }

    @Test
    public void testDeleteByID() {
        //given
        Car car1 = new Car(15, "Mitsubishi",
                "Lancer", 1998, "Black","ERD453");
        Car car2 = new Car(25, "Toyota",
                "HighLander", 2015, "White","YTH453");
        int expected = 1;
        //when
        Connection connection = main.ConnectionFactory.getConnection("mysql");
        CarRepo carRepo = new CarRepo(connection);
        carRepo.create(car1);
        carRepo.create(car2);
        carRepo.delete(25);
        int actual = carRepo.findAll().size();
        //then
        Assert.assertEquals(expected, actual);
        carRepo.delete(15);
    }

    @Test
    public void testDeleteObject() {
        //given
        Car car1 = new Car(15, "Mitsubishi",
                "Lancer", 1998, "Black","ERD453");
        Car car2 = new Car(25, "Toyota",
                "HighLander", 2015, "White","YTH453");
        int expected = 1;
        //when
        Connection connection = main.ConnectionFactory.getConnection("mysql");
        CarRepo carRepo = new CarRepo(connection);
        carRepo.create(car1);
        carRepo.create(car2);
        carRepo.delete(car2);
        int actual = carRepo.findAll().size();
        //then
        Assert.assertEquals(expected, actual);
        carRepo.delete(15);
    }
}
