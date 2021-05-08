import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {

	static Queue ready = new LinkedList();
	static int number;

	public void dispatch() {
		if (ready.size() > 0) {
			Process p = (Process) ready.peek();
			// System.out.println(p);
			// System.out.println("before" + ready);
			if (p.getProcessState(p) == ProcessState.Terminated) {
				ready.remove(p);
				// running();
			}
		}
		// System.out.println("after" + ready);

	}

	public void running() {
		Process p;
		while (ready.size() > 0) {
			p = (Process) ready.peek();
			if (p.getProcessState(p).equals(ProcessState.Terminated)) {
				this.dispatch();
			}
			if (p.getProcessState(p).equals(ProcessState.Ready)) {
				p.setProcessState(p, ProcessState.Running);
				p.start();
			}

		}

	}

	public void loadQueue(Process p) {
		ready.add(p);
	}

}
