import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {
	semaphoreValue value;
	Queue<Process> blockedq = new LinkedList();

	public Semaphore() {
		value = semaphoreValue.one;
	}

	public void semWait(Process p) {
		if (this.value == semaphoreValue.one) {
			this.value = semaphoreValue.zero;
		} else {
			blockedq.add(p);
			p.setProcessState(p, ProcessState.Waiting);
			// Scheduler.ready.remove(p);
			p.suspend();
		}
	}

	public void semPost() {

		if (blockedq.isEmpty() || blockedq.size() == 0)
			this.value = semaphoreValue.one;
		else {
			/* remove a process P from s.queue */
			Process s = blockedq.remove();
			// Scheduler.ready.add(s);
			/* place process P on ready list */
			s.setProcessState(s, ProcessState.Ready);
			s.resume();

		}
	}

}
