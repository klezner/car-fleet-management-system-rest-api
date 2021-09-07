package pl.kl.companycarfleetmanagementsystem.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarService carService;
    @Captor
    ArgumentCaptor<Car> carArgumentCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createCar_whenAllValuesAreCorrect_thenReturnNewCar() {
        // when
        carService.createCar(CreateCarRequestTestHelper.provideCreateCarRequest());
        // then
        verify(carRepository).save(carArgumentCaptor.capture());
        final Car car = carArgumentCaptor.getValue();
        verify(carRepository, times(1)).save(any(Car.class));
        assertThat(car.getBrand()).isEqualTo(CreateCarRequestTestHelper.provideCreateCarRequest().getBrand());
        assertThat(car.getModel()).isEqualTo(CreateCarRequestTestHelper.provideCreateCarRequest().getModel());
        assertThat(car.getRegistrationNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCarRequest().getRegistrationNumber());
        assertThat(car.getProductionYear()).isEqualTo(CreateCarRequestTestHelper.provideCreateCarRequest().getProductionYear());
        assertThat(car.getVinNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCarRequest().getVinNumber());
    }

    @Test
    void fetchAllCars_thenReturnAllCars() {
        // given
        when(carRepository.findAll()).thenReturn(CarTestHelper.provideCarList());
        // when
        final List<Car> cars = carService.fetchAllCars();
        // then
        verify(carRepository).findAll();
        verify(carRepository, times(1)).findAll();
        assertThat(cars.size()).isEqualTo(2);
        assertThat(cars.get(0)).isInstanceOf(Car.class);
        assertThat(cars.get(0).getBrand()).isEqualTo(CarTestHelper.provideCarList().get(0).getBrand());
        assertThat(cars.get(0).getModel()).isEqualTo(CarTestHelper.provideCarList().get(0).getModel());
        assertThat(cars.get(0).getRegistrationNumber()).isEqualTo(CarTestHelper.provideCarList().get(0).getRegistrationNumber());
        assertThat(cars.get(0).getProductionYear()).isEqualTo(CarTestHelper.provideCarList().get(0).getProductionYear());
        assertThat(cars.get(0).getVinNumber()).isEqualTo(CarTestHelper.provideCarList().get(0).getVinNumber());
        assertThat(cars.get(1)).isInstanceOf(Car.class);
        assertThat(cars.get(1).getBrand()).isEqualTo(CarTestHelper.provideCarList().get(1).getBrand());
        assertThat(cars.get(1).getModel()).isEqualTo(CarTestHelper.provideCarList().get(1).getModel());
        assertThat(cars.get(1).getRegistrationNumber()).isEqualTo(CarTestHelper.provideCarList().get(1).getRegistrationNumber());
        assertThat(cars.get(1).getProductionYear()).isEqualTo(CarTestHelper.provideCarList().get(1).getProductionYear());
        assertThat(cars.get(1).getVinNumber()).isEqualTo(CarTestHelper.provideCarList().get(1).getVinNumber());
    }
}
