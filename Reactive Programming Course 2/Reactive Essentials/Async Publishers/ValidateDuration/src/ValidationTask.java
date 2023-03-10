import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ValidationTask {

	public static Mono<Void> validate(Duration duration) {
		if (duration.isNegative() || duration.isZero()) {
			return Mono.error(new IllegalArgumentException("wrong duration"));
		} else {
			return Mono.empty();
		}
	}
}