package thread;

public class ConditionApplication {

    private static Runnable getThreadA(final ConditionService service) {
        return () -> {
            for (int i = 0; i < 10; i++) {
                service.excuteA();
            }
        };
    }

    private static Runnable getThreadB(final ConditionService service) {
        return () -> {
            for (int i = 0; i < 10; i++) {
                service.excuteB();
            }
        };
    }

    private static Runnable getThreadC(final ConditionService service) {
        return () -> {
            for (int i = 0; i < 10; i++) {
                service.excuteC();
            }
        };
    }

    public static void main(String[] args) {
        ConditionService service = new ConditionService();
        Runnable A = getThreadA(service);
        Runnable B = getThreadB(service);
        Runnable C = getThreadC(service);

        new Thread(A, "A").start();
        new Thread(B, "B").start();
        new Thread(C, "C").start();
    }

}
