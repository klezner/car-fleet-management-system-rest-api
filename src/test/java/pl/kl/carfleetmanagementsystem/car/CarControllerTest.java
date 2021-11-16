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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CarControllerTest {

    @Mock
    CarMapper carMapper;
    @Mock
    CarService carService;
    @InjectMocks
    CarController carController;
    @Captor
    ArgumentCaptor<CreateCarRequest> createCarRequestArgumentCaptor;
    @Captor
    ArgumentCaptor<UpdateCarRequest> updateCarRequestArgumentCaptor;

    @BeforeEach
    void setUp() {

    }

    @Test
    void addCar_whenAllValuesAreCorrect_thenReturnNewCarFromService() {
        // when
        carController.addCar(CreateCarRequestTestHelper.provideCreateCar1Request());
        // then
        verify(carService).createCar(createCarRequestArgumentCaptor.capture());
        final CreateCarRequest request = createCarRequestArgumentCaptor.getValue();
        verify(carService, times(1)).createCar(any(CreateCarRequest.class));
        assertThat(request.getBrand()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getBrand());
        assertThat(request.getModel()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getModel());
        assertThat(request.getRegistrationNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getRegistrationNumber());
        assertThat(request.getProductionYear()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getProductionYear());
        assertThat(request.getVinNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getVinNumber());
    }

    @Test
    void addCar_whenAllValuesAreCorrect_thenReturnNewCar() {
        // given
        when(carService.createCar(any(CreateCarRequest.class))).thenReturn(CarTestHelper.provideCar1());
        // when
        final ResponseEntity<CarResponse> responseEntity = carController.addCar(CreateCarRequestTestHelper.provideCreateCar1Request());
        // then
        verify(carService, times(1)).createCar(any(CreateCarRequest.class));
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(null);
    }

    @Test
    void getAllCars_whenThereAreCarsInDb_thenReturnAllCars() {
        // given
        when(carService.fetchAllCars()).thenReturn(CarTestHelper.provideCarList());
        // when
        final List<Car> cars = carService.fetchAllCars();
        // then
        verify(carService, times(1)).fetchAllCars();
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
    void getAllCars_whenDbIsEmpty_thenReturnAllCars() {
        // given
        when(carService.fetchAllCars()).thenReturn(new ArrayList<>());
        // when
        final List<Car> cars = carService.fetchAllCars();
        // then
        verify(carService, times(1)).fetchAllCars();
        assertThat(cars).isEmpty();
    }

    @Test
    void updateCar_whenAllValuesAreCorrect_thenReturnUpdatedCarFromService() {
        // when
        carController.updateCar(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L));
        // then
        verify(carService).editCar(updateCarRequestArgumentCaptor.capture());
        final UpdateCarRequest request = updateCarRequestArgumentCaptor.getValue();
        verify(carService, times(1)).editCar(any(UpdateCarRequest.class));
        assertThat(request.getId()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L).getId());
        assertThat(request.getBrand()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L).getBrand());
        assertThat(request.getModel()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L).getModel());
        assertThat(request.getRegistrationNumber()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L).getRegistrationNumber());
        assertThat(request.getProductionYear()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L).getProductionYear());
        assertThat(request.getVinNumber()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(1L).getVinNumber());
    }

    @Test
    void updateCar_whenAllValuesAreCorrect_thenReturnUpdatedCar() {
        // given
        when(carService.editCar(any(UpdateCarRequest.class))).thenReturn(CarTestHelper.provideCar1());
        // when
        final ResponseEntity<CarResponse> responseEntity = carController.updateCar(UpdateCarRequestTestHelper.provideUpdateCar1Request(1111L));
        // then
        verify(carService, times(1)).editCar(any(UpdateCarRequest.class));
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(null);
    }
}
