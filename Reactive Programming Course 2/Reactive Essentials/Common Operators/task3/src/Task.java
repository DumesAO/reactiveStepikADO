import reactor.core.publisher.Flux;

public class Task {

	public static Flux<Character> createSequence(Flux<String> stringFlux) {
		return stringFlux.flatMap(i -> Flux.fromArray(i.split("")))
				.map(i -> i.charAt(0));
	}
}