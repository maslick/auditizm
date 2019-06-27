package io.maslick.auditizm.conf;

import io.maslick.auditizm.data.Book;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class BookEntityListener {
	@PrePersist
	public void prePersist(Book target) {
	}

	@PreUpdate
	public void preUpdate(Book target) {
		System.out.println("PRE UPDATE: " + target);
	}

	@PreRemove
	public void preRemove(Book target) {}
}
