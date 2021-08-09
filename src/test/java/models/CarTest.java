package models;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

    @Test
    public void testConstructor(){
        //given
        Integer expectedId = 15;
        String expectedMake= "Mitsubishi";
        String expectedModel = "Lancer";
        Integer expectedYear = 1998;
        String expectedColor = "Black";
        String expectedVin="ERD453";
        //when
        Car car = new Car(expectedId, expectedMake, expectedModel, expectedYear, expectedColor,expectedVin);
        Integer actualID = car.getId();
        String actualMake = car.getMake();
        String actualModel = car.getModel();
        Integer actualYear = car.getYear();
        String actualColor = car.getColor();
        String actualVin=car.getVin();
        //then
        Assert.assertEquals(expectedId, actualID);
        Assert.assertEquals(expectedMake, actualMake);
        Assert.assertEquals(expectedModel, actualModel);
        Assert.assertEquals(expectedYear, actualYear);
        Assert.assertEquals(expectedColor, actualColor);
        Assert.assertEquals(expectedVin,actualVin);
    }


    @Test
    public void testGetId() {
        //given
        Integer expectedValue = 30;
        //when
        Car car = new Car(expectedValue, "Tata", "Sumo", 1997, "Green","IKJ984");
        Integer actualValue = car.getId();
        //then
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSetId() {
        //given
        Integer expected = 28;
        //when
        Car car = new Car();
        car.setId(expected);
        Integer actual = car.getId();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetMake() {
        //given
        String expected = "Tata";
        //when
        Car car = new Car(11, expected,"Docomo", 2002, "Red", "JUK596");
        String actual = car.getMake();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetMake() {
        //given
        String expected = "Ford";
        //when
        Car car = new Car();
        car.setMake(expected);
        String actual = car.getMake();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetModel() {
        //given
        String expected = "Camry";
        //when
        Car car = new Car(11, "Toyota", expected, 2013,"Burgundy Red", "KDY738");
        String actual = car.getModel();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetModel() {
        //given
        String expected = "Camrry";
        //when
        Car car = new Car();
        car.setModel(expected);
        String actual = car.getModel();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetYear() {
        //given
        Integer expected = 2019;
        //when
        Car car = new Car(19, "Volkwagon","Beetle", expected,"yellow", "JFT679");
        Integer actual = car.getYear();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetYear() {
        //given
        Integer expected = 2020;
        //when
        Car car = new Car();
        car.setYear(expected);
        Integer actual = car.getYear();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetColor() {
        //given
        String expected = "Grey";
        //when
        Car car = new Car(75, "Mitsubishi",
                "Lancer", 1998, "Grey","ERD453");
        String actual = car.getColor();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetColor() {
        //given
        String expected = "Blue";
        //when
        Car car = new Car();
        car.setColor(expected);
        String actual = car.getColor();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        //given
        String expected = "Car Details: \n" +
                "CarId:-15 CarMake:-Mitsubishi CarModel:-Lancer CarYear:-1998 CarColor:-Black CarVin:-ERD453";
        //when
        Car car = new Car(15, "Mitsubishi",
                "Lancer", 1998, "Black","ERD453");
        String actual = car.toString();
        //then
        Assert.assertEquals(expected, actual);
    }
}
