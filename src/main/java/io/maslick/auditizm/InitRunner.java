package io.maslick.auditizm;

import io.maslick.auditizm.data.Book;
import io.maslick.auditizm.repo.AuditizmRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class InitRunner implements ApplicationRunner {

	private final AuditizmRepo auditizm;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Starting...");
		auditizm.save(new Book(null, "Auditizm in Action"));
		auditizm.save(new Book(null, "Auditizm in Action ver 2"));
		auditizm.save(new Book(null, "Auditizm in Action ver 3"));

		auditizm.findAll().forEach(System.out::println);

		Thread.sleep(2000);
		System.out.println("");

		Book book2 = auditizm.findById(2L).get();
		book2.setName("Hello world in Action");
		auditizm.save(book2);

		book2 = auditizm.findById(2L).get();
		book2.setName("Goodbye world vol. 1");
		auditizm.save(book2);

		auditizm.findAll().forEach(System.out::println);
	}
}
