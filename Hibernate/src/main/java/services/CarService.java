package services;

import dao.CarDao;
import models.Auto;
import models.User;

import java.util.List;

public class CarService {

    private CarDao carDao = new CarDao();

    public CarService() {
    }

    public Auto findAuto(int id) {
        return carDao.findById(id);
    }

    public void saveCar(Auto auto) {
        carDao.save(auto);
    }

    public void deleteCar(Auto auto) {
        carDao.delete(auto);
    }

    public void updateCar(Auto auto) {
        carDao.update(auto);
    }

    public List<Auto> findAllCars() {
        return carDao.findAll();
    }




}
