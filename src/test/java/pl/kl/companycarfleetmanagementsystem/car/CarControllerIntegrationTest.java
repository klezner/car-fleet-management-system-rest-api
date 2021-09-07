package pl.kl.companycarfleetmanagementsystem.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
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
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(carRepository.findAll()).singleElement().isInstanceOf(Car.class);
        assertThat(carRepository.findAll()).hasSize(1);
        assertThat(carRepository.findAll()).allSatisfy(car -> {
            assertThat(car.getId()).isEqualTo(1L);
            assertThat(car.getBrand()).isEqualTo(CarTestHelper.provideCar1().getBrand());
            assertThat(car.getModel()).isEqualTo(CarTestHelper.provideCar1().getModel());
            assertThat(car.getRegistrationNumber()).isEqualTo(CarTestHelper.provideCar1().getRegistrationNumber());
            assertThat(car.getProductionYear()).isEqualTo(CarTestHelper.provideCar1().getProductionYear());
            assertThat(car.getVinNumber()).isEqualTo(CarTestHelper.provideCar1().getVinNumber());
        });
    }
}
