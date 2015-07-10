package pt.aor.projeto7.ex10;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 *
 * @author Edgar
 */
public class MyMonitor extends AbstractQueuedSynchronizer {

    public MyMonitor() {
    }

    // Reports whether in locked state
    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

    // Acquires the lock if state is zero
    @Override
    public boolean tryAcquire(int acquires) {
        assert acquires == 1; // Otherwise unused
        if (hasQueuedPredecessors()) {
            return false;
        } else if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;

    }

    // Releases the lock by setting state to zero
    @Override
    protected boolean tryRelease(int releases) {
        assert releases == 1; // Otherwise unused
        if (getState() == 0) {
            throw new IllegalMonitorStateException();
        }
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public boolean isLocked() {
        return isHeldExclusively();
    }

    public void enter(){
     acquire(1);
    }
    public void leave(){
    release(1);
    }
}
