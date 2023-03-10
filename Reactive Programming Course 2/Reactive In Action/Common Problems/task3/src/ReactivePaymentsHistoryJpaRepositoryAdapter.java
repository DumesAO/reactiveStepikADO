import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ReactivePaymentsHistoryJpaRepositoryAdapter
		implements PaymentsHistoryReactiveJpaRepository {

	final PaymentsHistoryJpaRepository repository;
	// use ExecutorService or Scheduler to offload blocking calls
	final Scheduler tasksScheduler = Schedulers.newBoundedElastic(ConnectionsPool.instance()
							.size(),
					Integer.MAX_VALUE,
					"DataBaseBlockingTasksScheduler");

	public ReactivePaymentsHistoryJpaRepositoryAdapter(PaymentsHistoryJpaRepository repository) {
		this.repository = repository;
	}

	public Flux<Payment> findAllByUserId(String userId) {
		// TODO: provide asynchronous wrapping around blocking JPARepository
		// HINT: Consider provide custom singleton thread-pool with limited amount of workers
		//       ThreadCount == ConnectionsPool.size()

		return Flux.defer(() -> Flux.fromIterable(repository.findAllByUserId(userId)))
				.subscribeOn(tasksScheduler);	}
}
