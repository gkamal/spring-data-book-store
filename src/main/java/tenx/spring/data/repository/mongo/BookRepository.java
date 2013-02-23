package tenx.spring.data.repository.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import tenx.spring.data.domain.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
	
	Book findOneByTitle(String title);
	
	Page<Book> findByTitleLike(String title, Pageable pageable);
	
	Page<Book> findByTitleLikeOrAuthorsLike(String title, String author,Pageable pageable);
	
	Page<Book> findByAuthors(String author, Pageable pageable);
	
	Page<Book> findByPriceLessThan(double price, Pageable pageable);

}
