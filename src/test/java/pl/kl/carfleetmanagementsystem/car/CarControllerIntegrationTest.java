package pl.kl.carfleetmanagementsystem.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CarControllerIntegrationTest {

    @Autowired
    private CarController carController;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
        MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    void addCar_whenPostNewCarRequest_thenReturn201AndSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1Request();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(cars).singleElement().isInstanceOf(Car.class);
        assertThat(cars).hasSize(1);
        assertThat(cars).singleElement().satisfies(car -> {
            assertThat(car.getBrand()).isEqualTo(CarTestHelper.provideCar1().getBrand());
            assertThat(car.getModel()).isEqualTo(CarTestHelper.provideCar1().getModel());
            assertThat(car.getRegistrationNumber()).isEqualTo(CarTestHelper.provideCar1().getRegistrationNumber());
            assertThat(car.getProductionYear()).isEqualTo(CarTestHelper.provideCar1().getProductionYear());
            assertThat(car.getVinNumber()).isEqualTo(CarTestHelper.provideCar1().getVinNumber());
        });
    }

    @Test
    void addCar_whenPostNewCarRequestWithEmptyBrand_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithEmptyBrandRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithNullBrand_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithNullBrandRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithEmptyModel_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithEmptyModelRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithNullModel_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithNullModelRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithEmptyRegNumber_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithEmptyRegNumberRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithNullRegNumber_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithNullRegNumberRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithRegNumberShorterThanRequired_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithRegNumberShorterThanRequiredRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithRegNummberShorterThanRequired_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithRegNumberLongerThanRequiredRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithEmptyVinNumber_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithEmptyVinNumberRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithNullVinNumber_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithNullVinNumberRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithVinNumberShorterThanRequired_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithVinNumberShorterThanRequiredRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithVinNumberLongerThanRequired_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1WithVinNumberLongerThanRequiredRequest();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithNullProdYear_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1RequestWithNullProdYear();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithTooEarlyProdYear_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1RequestWithTooEarlyProdYear();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void addCar_whenPostNewCarRequestWithTooLateProdYear_thenReturn400AndDoesNotSaveNewCarInDb() throws Exception {
        // given
        final CreateCarRequest requestBody = CreateCarRequestTestHelper.provideCreateCar1RequestWithTooLateProdYear();
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(cars).isEmpty();
    }

    @Test
    void updateCar_whenCarIsSavedInDb_thenReturn200AndSaveUpdatedCarInDb() throws Exception {
        // given
        final CreateCarRequest createRequestBody = CreateCarRequestTestHelper.provideCreateCar1Request();
        final MockHttpServletRequestBuilder createRequest = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestBody));

        mockMvc.perform(createRequest);

        final Long createdCarId = carRepository.findAll().stream()
                .findFirst().orElse(new Car()).getId();

        final UpdateCarRequest updateRequestBody = UpdateCarRequestTestHelper.provideUpdateCar1Request(createdCarId);
        final MockHttpServletRequestBuilder updateRequest = MockMvcRequestBuilders
                .put("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequestBody));
        // when
        final MockHttpServletResponse response = mockMvc.perform(updateRequest)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(cars).singleElement().isInstanceOf(Car.class);
        assertThat(cars).hasSize(1);
        assertThat(cars).singleElement().satisfies(car -> {
            assertThat(car.getId()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(createdCarId).getId());
            assertThat(car.getBrand()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(createdCarId).getBrand());
            assertThat(car.getModel()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(createdCarId).getModel());
            assertThat(car.getRegistrationNumber()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(createdCarId).getRegistrationNumber());
            assertThat(car.getProductionYear()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(createdCarId).getProductionYear());
            assertThat(car.getVinNumber()).isEqualTo(UpdateCarRequestTestHelper.provideUpdateCar1Request(createdCarId).getVinNumber());
        });
    }

    @Test
    void getAllCars_whenCarsAreSavedInDb_thenReturn200AndGetAllCarsFromDb() throws Exception {
        // given
        final CreateCarRequest createRequestBody1 = CreateCarRequestTestHelper.provideCreateCar1Request();
        final MockHttpServletRequestBuilder createRequest1 = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestBody1));

        mockMvc.perform(createRequest1);

        final CreateCarRequest createRequestBody2 = CreateCarRequestTestHelper.provideCreateCar2Request();
        final MockHttpServletRequestBuilder createRequest2 = MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestBody2));

        mockMvc.perform(createRequest2);

        final MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders
                .get("/car");
        // when
        final MockHttpServletResponse response = mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(cars).first().isInstanceOf(Car.class);
        assertThat(cars).hasSize(2);
        assertThat(cars).element(0).satisfies(car -> {
            assertThat(car.getBrand()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getBrand());
            assertThat(car.getModel()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getModel());
            assertThat(car.getRegistrationNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getRegistrationNumber());
            assertThat(car.getProductionYear()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getProductionYear());
            assertThat(car.getVinNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar1Request().getVinNumber());
        });
        assertThat(cars).element(1).satisfies(car -> {
            assertThat(car.getBrand()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar2Request().getBrand());
            assertThat(car.getModel()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar2Request().getModel());
            assertThat(car.getRegistrationNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar2Request().getRegistrationNumber());
            assertThat(car.getProductionYear()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar2Request().getProductionYear());
            assertThat(car.getVinNumber()).isEqualTo(CreateCarRequestTestHelper.provideCreateCar2Request().getVinNumber());
        });
    }

    @Test
    void getAllCars_whenCarsAreNotSavedInDb_thenReturn404AndGetEmptyListFromDb() throws Exception {
        // given
        final MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders
                .get("/car");
        // when
        final MockHttpServletResponse response = mockMvc.perform(getRequest)
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
        // then
        final List<Car> cars = carRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(cars).isEmpty();
    }
}
