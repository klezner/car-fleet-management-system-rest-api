package pl.kl.carfleetmanagementsystem.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CarServiceTest {

    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarService carService;
    @Captor
    ArgumentCaptor<Car> carArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> updatedCarIdArgumentCaptor;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createCar_whenAllValuesAreCorrect_thenReturnNewCarFromRepository() {
        // when
        carService.createCar(CreateCarRequestTestHelper.provideCreateCar1Request());
        // then
        verify(carRepository).save(carArgumentCaptor.capture());
        final Car car = carArgumentCaptor.getValue();
        verify(carRepository, times(1)).save(any(Car.class));
        assertThat(car.getId()).isEqualTo(null);
        assertThat(car.getBrand()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getBrand());
        assertThat(car.getModel()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getModel());
        assertThat(car.getRegistrationNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getRegistrationNumber());
        assertThat(car.getProductionYear()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getProductionYear());
        assertThat(car.getVinNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getVinNumber());
    }

    @Test
    void createCar_whenAllValuesAreCorrect_thenReturnNewCar() {
        // given
        when(carRepository.save(any(Car.class))).thenReturn(CarTestHelper.provideCar1());
        // when
        final Car car = carService.createCar(CreateCarRequestTestHelper.provideCreateCar1Request());
        // then
        verify(carRepository, times(1)).save(any(Car.class));
        assertThat(car.getId()).isEqualTo(CarTestHelper.provideCar1().getId());
        assertThat(car.getBrand()).isEqualTo(CarTestHelper.provideCar1().getBrand());
        assertThat(car.getModel()).isEqualTo(CarTestHelper.provideCar1().getModel());
        assertThat(car.getRegistrationNumber()).isEqualTo(CarTestHelper.provideCar1().getRegistrationNumber());
        assertThat(car.getProductionYear()).isEqualTo(CarTestHelper.provideCar1().getProductionYear());
        assertThat(car.getVinNumber()).isEqualTo(CarTestHelper.provideCar1().getVinNumber());
    }

    @Test
    void fetchAllCars_whenThereAreCarsInDb_thenReturnAllCars() {
        // given
        when(carRepository.findAll()).thenReturn(CarTestHelper.provideCarList());
        // when
        final List<Car> cars = carService.fetchAllCars();
        // then
        verify(carRepository, times(1)).findAll();
        assertThat(cars.size()).isEqualTo(2);
        assertThat(cars.get(0)).isInstanceOf(Car.class);
        assertThat(cars.get(0).getId()).isEqualTo(CarTestHelper.provideCarList().get(0).getId());
        assertThat(cars.get(0).getBrand()).isEqualTo(CarTestHelper.provideCarList().get(0).getBrand());
        assertThat(cars.get(0).getModel()).isEqualTo(CarTestHelper.provideCarList().get(0).getModel());
        assertThat(cars.get(0).getRegistrationNumber()).isEqualTo(CarTestHelper.provideCarList().get(0).getRegistrationNumber());
        assertThat(cars.get(0).getProductionYear()).isEqualTo(CarTestHelper.provideCarList().get(0).getProductionYear());
        assertThat(cars.get(0).getVinNumber()).isEqualTo(CarTestHelper.provideCarList().get(0).getVinNumber());
        assertThat(cars.get(1)).isInstanceOf(Car.class);
        assertThat(cars.get(1).getId()).isEqualTo(CarTestHelper.provideCarList().get(1).getId());
        assertThat(cars.get(1).getBrand()).isEqualTo(CarTestHelper.provideCarList().get(1).getBrand());
        assertThat(cars.get(1).getModel()).isEqualTo(CarTestHelper.provideCarList().get(1).getModel());
        assertThat(cars.get(1).getRegistrationNumber()).isEqualTo(CarTestHelper.provideCarList().get(1).getRegistrationNumber());
        assertThat(cars.get(1).getProductionYear()).isEqualTo(CarTestHelper.provideCarList().get(1).getProductionYear());
        assertThat(cars.get(1).getVinNumber()).isEqualTo(CarTestHelper.provideCarList().get(1).getVinNumber());
    }

    @Test
    void fetchAllCars_whenDbIsEmpty_thenReturnAllCars() {
        // given
        when(carRepository.findAll()).thenReturn(new ArrayList<>());
        // when
        final List<Car> cars = carService.fetchAllCars();
        // then
        verify(carRepository, times(1)).findAll();
        assertThat(cars).isEmpty();
    }

    @Test
    void editCar_whenAllValuesAreCorrect_thenReturnUpdatedCarFromService() {
        // when
        carService.editCar(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L));
        // then
        verify(carRepository).findById(updatedCarIdArgumentCaptor.capture());
        final Long carId = updatedCarIdArgumentCaptor.getValue();
        verify(carRepository, times(1)).findById(anyLong());
        assertThat(carId).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L).getId());
    }

    // TODO: Test not implemented
    @Test
    void editCar_whenAllValuesAreCorrect_thenReturnUpdatedCar() {
        // given

        // when

        // then
    }
}
