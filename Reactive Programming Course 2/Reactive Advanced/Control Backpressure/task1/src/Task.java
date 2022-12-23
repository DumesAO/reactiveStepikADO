import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Task {

	public static Flux<RefCounted> dropElementsOnBackpressure(Flux<RefCounted> upstream) {
		return upstream.onBackpressureDrop(RefCounted::release);
	}
}