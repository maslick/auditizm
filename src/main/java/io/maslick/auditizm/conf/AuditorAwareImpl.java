package io.maslick.auditizm.conf;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("helloworld-" + generateRandomInt());
	}

	private int generateRandomInt() {
		return new Random().nextInt(128) & Integer.MAX_VALUE;
	}
}

