package service;

import java.util.List;
import java.util.stream.Collectors;
import model.Car;
import model.Cars;
import movement.MovementHandler;
import view.OutputView;

public class RacingGameService {
    private final MovementHandler randomNumberHandler;
    private final OutputView outputView;

    public RacingGameService(MovementHandler randomNumberHandler, OutputView outputView) {
        this.randomNumberHandler = randomNumberHandler;
        this.outputView = outputView;
    }

    public void startRace(Cars cars, int rounds) {
        for (int i = 0; i < rounds; i++) {
            moveAllCars(cars);
            outputView.printCurrentStatus(cars);
        }
    }

    private void moveAllCars(Cars cars) {
        for (Car car : cars.getCars()) {
            if (randomNumberHandler.canMove()) {
                car.moveForward();
            }
        }
    }

    private int getMaxPosition(Cars cars) {

        return cars.getCars().stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    public List<String> getWinners(Cars cars) {
        int maxPosition = getMaxPosition(cars);

        return cars.getCars().stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
