package io.maslick.auditizm.repo;

import io.maslick.auditizm.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditizmRepo extends JpaRepository<Book, Long> {}
