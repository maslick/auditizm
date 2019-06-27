package io.maslick.auditizm;

import io.maslick.auditizm.data.Book;
import io.maslick.auditizm.repo.AuditizmRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class AuditizmApplicationTests {

	@Autowired private AuditizmRepo auditizm;

	@Before
	public void before() {
		auditizm.deleteAll();
		auditizm.save(new Book(null, "Auditizm in Action"));
		auditizm.save(new Book(null, "Auditizm in Action ver 2"));
		auditizm.save(new Book(null, "Auditizm in Action ver 3"));
	}

	@Test
	public void testVersions() {
		printAllBooks();

		Book book = auditizm.findAll().get(0);
		Long id = book.getId();
		Assert.assertEquals(0, book.getVersionId().longValue());

		editBook(id);
		printAllBooks();
		Assert.assertEquals("Version should get incremented", book.getVersionId() + 1, auditizm.findById(id).orElse(null).getVersionId().longValue());

		editBook(id);
		printAllBooks();
		Assert.assertEquals("Version should get incremented", book.getVersionId() + 2, auditizm.findById(id).orElse(null).getVersionId().longValue());
	}

	@Test
	public void testUpdateDate() {
		printAllBooks();

		Book book = auditizm.findAll().get(0);
		Long id = book.getId();
		Assert.assertEquals(book.getCreatedDate(), book.getLastModifiedDate());

		editBook(id);
		printAllBooks();

		Book modifiedBook = auditizm.findById(id).get();
		Date modifiedDate = modifiedBook.getLastModifiedDate();
		Assert.assertNotEquals("Original book creation time and modified one should differ", book.getLastModifiedDate(), modifiedDate);
	}

	private void printAllBooks() {
		auditizm.findAll().forEach(System.out::println);
		System.out.println("\n\n");
	}

	private void editBook(long bookId) {
		Book book = auditizm.findById(bookId).orElse(null);
		book.setName(book.getName() + " vol. " + new Random().nextInt(10));
		auditizm.save(book);
	}
}
