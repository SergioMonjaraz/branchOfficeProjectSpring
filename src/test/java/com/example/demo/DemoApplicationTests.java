package com.example.demo;

import com.example.demo.models.BranchOffice;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {


	int randomServerPort = 8080;

	@Autowired
	private TestRestTemplate restTemplate;

	final String baseUrl = "http://localhost:"+randomServerPort+"/demo_war/api/branchoffice";

	@Test
	public void testGetBranchOffices(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.GET,entity, String.class);

		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

	}

	@Test
	public void testCreateSuccess(){
		BranchOffice office = new BranchOffice();
		office.setLongitude(123.0115);
		office.setLatitude(-78.5054);
		office.setAddress("Direccion nueva");
		office.setName("Sucursal 3");

		ResponseEntity<BranchOffice> response = restTemplate.postForEntity(baseUrl+"/create", office, BranchOffice.class);

		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertNotNull(response);
	}


	@Test
	public void testCreateMissing(){
		BranchOffice office = new BranchOffice();
		office.setLongitude(123.0115);
		office.setLatitude(-78.5054);
		office.setAddress("Direccion nueva");
		office.setName("Sucursal 2");

		ResponseEntity<BranchOffice> response = restTemplate.postForEntity(baseUrl+"/create", office, BranchOffice.class);

		Assert.assertEquals(409, response.getStatusCodeValue());
	}

	@Test
	public void testGetOfficeByPoint(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> result = restTemplate.exchange(baseUrl+"/get-by-point?latitude=120.00&longitude=85.95", HttpMethod.GET,entity, String.class);

		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

}
