package tenx.spring.data.repository.mongodb;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tenx.spring.data.domain.Book;
import tenx.spring.data.repository.mongo.BookRepository;

@ContextConfiguration("classpath:META-INF/spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRespositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Before
	public void setup(){
		bookRepository.deleteAll();
		Book book = bookRepository.save(newBook("Core Java", 
				350, 
				new String []{"Cay S. Horstmann","Gary Cornell"}));
		book.addReview("Great book for learning java");
		bookRepository.save(book);
	}


	private Book newBook(String title, double price,  String [] authors) {
		Book book = new Book();
		book.setTitle(title);
		book.setPrice(price);
		for(String author:authors) {
			book.addAuthor(author);
		}
		return book;
	}
	

	@Test
	public void findByTitle() {
		Page<Book> results = bookRepository.findByTitleLike("Core",new PageRequest(0,10));
		assertEquals(1,results.getNumberOfElements());
	}
	
	@Test
	public void findByTitleOrAuthor() {
		Page<Book> results = bookRepository.findByTitleLikeOrAuthorsLike("Cores","Cay",new PageRequest(0,10));
		assertEquals(1,results.getNumberOfElements());
	}

	
	@Test
	public void findSub500Books() {
		Page<Book> results = bookRepository.findByPriceLessThan(500,new PageRequest(0,10));
		assertEquals(1,results.getNumberOfElements());
		results = bookRepository.findByPriceLessThan(50,new PageRequest(0,10));
		assertEquals(0,results.getNumberOfElements());
	}

}
