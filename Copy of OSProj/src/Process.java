//import java.util.concurrent.Semaphore;

public class Process extends Thread {

	volatile public int processID;
	volatile ProcessState status = ProcessState.New;

	public Process(int m) {
		processID = m;
	}

	@Override
	public void run() {
		switch (processID) {
		case 1:
			process1();
			break;
		case 2:
			process2();
			break;
		case 3:
			process3();
			break;
		case 4:
			process4();
			break;
		case 5:
			process5();
			break;
		}

	}

	private void process1() {
		// setProcessState(this, ProcessState.Running);
		OperatingSystem.print.semWait(this);
		OperatingSystem.printText("Enter File Name: ");
		OperatingSystem.print.semPost();
		OperatingSystem.print.semWait(this);
		OperatingSystem.read.semWait(this);
		OperatingSystem.take.semWait(this);
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		OperatingSystem.print.semPost();
		OperatingSystem.read.semPost();
		OperatingSystem.take.semPost();
		setProcessState(this, ProcessState.Terminated);
	}

	private void process2() {
		// setProcessState(this, ProcessState.Running);
		OperatingSystem.print.semWait(this);
		OperatingSystem.printText("Enter File Name: ");
		OperatingSystem.print.semPost();
		OperatingSystem.take.semWait(this);
		String filename = OperatingSystem.TakeInput();
		OperatingSystem.take.semPost();
		OperatingSystem.print.semWait(this);
		OperatingSystem.printText("Enter Data: ");
		OperatingSystem.print.semPost();
		OperatingSystem.take.semWait(this);
		String data = OperatingSystem.TakeInput();
		OperatingSystem.take.semPost();
		OperatingSystem.write.semWait(this);
		OperatingSystem.writefile(filename, data);
		OperatingSystem.write.semPost();
		setProcessState(this, ProcessState.Terminated);

	}

	private void process3() {
		// setProcessState(this, ProcessState.Running);
		int x = 0;
		OperatingSystem.print.semWait(this);
		while (x < 301) {
			OperatingSystem.printText(x + "\n");
			x++;
		}
		OperatingSystem.print.semPost();
		setProcessState(this, ProcessState.Terminated);

	}

	private void process4() {
		// setProcessState(this, ProcessState.Running);
		int x = 500;
		OperatingSystem.print.semWait(this);
		while (x < 1001) {
			OperatingSystem.printText(x + "\n");
			x++;
		}
		OperatingSystem.print.semPost();
		setProcessState(this, ProcessState.Terminated);

	}

	private void process5() {
		// setProcessState(this, ProcessState.Running);
		OperatingSystem.print.semWait(this);
		OperatingSystem.printText("Enter LowerBound: ");
		OperatingSystem.print.semPost();
		OperatingSystem.take.semWait(this);
		String lower = OperatingSystem.TakeInput();
		OperatingSystem.take.semPost();
		OperatingSystem.print.semWait(this);
		OperatingSystem.printText("Enter UpperBound: ");
		OperatingSystem.print.semPost();
		OperatingSystem.take.semWait(this);
		String upper = OperatingSystem.TakeInput();
		OperatingSystem.take.semPost();
		int lowernbr = Integer.parseInt(lower);
		int uppernbr = Integer.parseInt(upper);
		String data = "";

		while (lowernbr <= uppernbr) {
			data += lowernbr++ + "\n";
		}
		OperatingSystem.write.semWait(this);
		OperatingSystem.writefile("P5.txt", data);
		OperatingSystem.write.semPost();
		setProcessState(this, ProcessState.Terminated);

	}

	public static void setProcessState(Process p, ProcessState s) {
		p.status = s;
		if (s == ProcessState.Terminated) {
			OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
			// Scheduler.ready.remove(OperatingSystem.ready.remove(p));
			// OperatingSystem.schedule.dispatch();
		}
	}

	public static ProcessState getProcessState(Process p) {
		return p.status;
	}
}
