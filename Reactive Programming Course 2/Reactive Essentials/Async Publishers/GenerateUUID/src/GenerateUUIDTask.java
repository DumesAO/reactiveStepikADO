import reactor.core.publisher.Mono;

public class GenerateUUIDTask {

	static UUIDGenerator uuidGenerator;

	public static Mono generateRandomUUID() {
		return Mono.just(uuidGenerator);
	}
}