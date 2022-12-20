package me.eladiofeijoo.laptops.controllers;

import me.eladiofeijoo.laptops.dtos.ErrorDto;
import me.eladiofeijoo.laptops.models.Laptop;
import me.eladiofeijoo.laptops.services.LaptopsSaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopsControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private LaptopsSaveService saveService;

    @LocalServerPort
    private int port;


    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
        populateDb();
    }


    private void populateDb(){
        saveService.execute(new Laptop(null, "ACER", 17, 8));
        saveService.execute(new Laptop(null, "ACER", 15, 4));
        saveService.execute(new Laptop(null, "HP", 16, 8));
        saveService.execute(new Laptop(null, "Lenovo", 14, 4));
        saveService.execute(new Laptop(null, "Compact", 11, 2));
    }


    @Test
    void add() {

        String json = """
                {
                    "manufacturer": "HP",
                    "inches": 14,
                    "ram": 4
                }
                """;

        HttpEntity<String> request = createHttpEntityLaptop(json);


        ResponseEntity<Laptop> response = testRestTemplate.exchange("/v1/laptops", HttpMethod.POST, request,
                Laptop.class);

        Laptop result = response.getBody();

        assert result != null;
        assertTrue(result.getId() > 0);
        assertEquals("HP", result.getManufacturer());
        assertEquals(14, result.getInches());
        assertEquals(4, result.getRam());
    }

    @Test
    void addWithErrorBadRequest() {
        String json = """
                {
                    "inches": 14,
                    "ram": 4
                }
                """;
        HttpEntity<String> request = createHttpEntityLaptop(json);

        ResponseEntity<ErrorDto> response = testRestTemplate.exchange("/v1/laptops", HttpMethod.POST, request,
                ErrorDto.class);

        ErrorDto result = response.getBody();

        assert result != null;
        assertNotNull(result.localDate());
        assertEquals(400, result.statusCode());
        assertEquals("Please, validate information sent", result.message());
        assertEquals("/v1/laptops", result.path());
    }

    private HttpEntity<String> createHttpEntityLaptop(String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(json,headers);
    }

    @Test
    void getAll() {
        ResponseEntity<Laptop[]> response  =
                testRestTemplate.getForEntity("/v1/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> list = Arrays.asList(Objects.requireNonNull(response.getBody()));
        assertTrue(list.size() >= 5);
    }

    @Test
    void getById(){

        ResponseEntity<Laptop> response  =
                testRestTemplate.getForEntity("/v1/laptops/1", Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        Laptop result = response.getBody();

        assert result != null;
        assertEquals(1, result.getId());
    }

    @Test
    void getByIdWithErrorNotFound(){
        ResponseEntity<ErrorDto> response  =
                testRestTemplate.getForEntity("/v1/laptops/1000", ErrorDto.class);

        assert response != null;
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getStatusCodeValue());

        ErrorDto result = response.getBody();

        assert result != null;
        assertNotNull(result.localDate());
        assertEquals(404, result.statusCode());
        assertEquals("Could not find Laptop with id: 1000", result.message());
        assertEquals("/v1/laptops/1000", result.path());

    }

    @Test
    void getByIdWithErrorBadRequest(){
        ResponseEntity<ErrorDto> response  =
                testRestTemplate.getForEntity("/v1/laptops/any", ErrorDto.class);

        assert response != null;
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void update() {

        String json = """
                {
                    "id": 1,
                    "manufacturer": "HP",
                    "inches": 14,
                    "ram": 4
                }
                """;

        HttpEntity<String> request = createHttpEntityLaptop(json);


        ResponseEntity<Laptop> response = testRestTemplate.exchange("/v1/laptops", HttpMethod.PUT, request,
                Laptop.class);

        Laptop result = response.getBody();

        assert result != null;
        assertEquals( 1,result.getId());
        assertEquals("HP", result.getManufacturer());
        assertEquals(14, result.getInches());
        assertEquals(4, result.getRam());
    }

    @Test
    void updateWithBadRequest() {

        String json = """
                {
                    "manufacturer": "HP",
                    "inches": 14,
                    "ram": 4
                }
                """;

        HttpEntity<String> request = createHttpEntityLaptop(json);


        ResponseEntity<ErrorDto> response = testRestTemplate.exchange("/v1/laptops", HttpMethod.PUT, request,
                ErrorDto.class);

        ErrorDto result = response.getBody();

        assert result != null;
        assertNotNull(result.localDate());
        assertEquals(400, result.statusCode());
        assertEquals("unsupported laptop", result.message());
        assertEquals("/v1/laptops", result.path());
    }

    @Test
    void updateWithNotFound() {

        String json = """
                {
                    "id": 10000,
                    "manufacturer": "HP",
                    "inches": 14,
                    "ram": 4
                }
                """;

        HttpEntity<String> request = createHttpEntityLaptop(json);


        ResponseEntity<ErrorDto> response = testRestTemplate.exchange("/v1/laptops", HttpMethod.PUT, request,
                ErrorDto.class);

        assert response != null;
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getStatusCodeValue());

        ErrorDto result = response.getBody();

        assert result != null;
        assertNotNull(result.localDate());
        assertEquals(404, result.statusCode());
        assertEquals("Could not find Laptop with id: 10000", result.message());
        assertEquals("/v1/laptops", result.path());
    }


}