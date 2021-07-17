package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	@GetMapping("/products")
	public List<Product> list()
	{
		return service.listAll();
	}
	@GetMapping("products/{id}")
	public ResponseEntity<Product>  get(@PathVariable Integer id)
	{
		try {
			Product p = service.get(id);
			return new ResponseEntity<Product>(p,HttpStatus.OK);
		}
		catch(NoSuchElementException e){
				return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PostMapping("/products")
	public void add(@RequestBody Product p)
	{
		service.save(p);
	}
	@PutMapping("/products/{id}")
	public ResponseEntity<?> update(@RequestBody Product p,@PathVariable Integer id)
	{
		try {
			Product existing = service.get(id);
			service.save(existing);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id)
	{
		service.delete(id);
	}
	
}
