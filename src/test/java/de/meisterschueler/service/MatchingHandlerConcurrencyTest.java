package de.meisterschueler.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

public class MatchingHandlerConcurrencyTest {
	static final int TOTAL_THREADS_TO_RUN = 1000;
    static final int maxTimeoutSeconds = 5;
    AtomicInteger passed;
    AtomicInteger failed;

    @Before
    public void setUp() throws Exception {
        passed = new AtomicInteger();
    }

    @Test
    public void testConcurrency() throws Exception {
        List<Runnable> parrallelTasksList = new ArrayList<Runnable>(TOTAL_THREADS_TO_RUN);
        for (int i = 0; i < TOTAL_THREADS_TO_RUN; i++) {
            parrallelTasksList.add(new Runnable() {
                public void run() {
                    passed.incrementAndGet();
                }
            });
        }

        assertConcurrent("serviceCall must be ThreadSafe", parrallelTasksList, maxTimeoutSeconds);
        assertThat("Expected 700 service calls to pass", passed.get(), is(700));
        assertThat("Expected 300 service calls to fail", failed.get(), is(300));
    }

    public static void assertConcurrent(final String message, final List<? extends Runnable> runnables,
            final int maxTimeoutSeconds) throws InterruptedException {
        final int numThreads = runnables.size();
        final List<Throwable> exceptions = Collections.synchronizedList(new ArrayList<Throwable>());
        final ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
        try {
            final CountDownLatch allExecutorThreadsReady = new CountDownLatch(numThreads);
            final CountDownLatch afterInitBlocker = new CountDownLatch(1);
            final CountDownLatch allDone = new CountDownLatch(numThreads);
            for (final Runnable submittedTestRunnable : runnables) {
                threadPool.submit(new Runnable() {
                    public void run() {
                        allExecutorThreadsReady.countDown();
                        try {
                            afterInitBlocker.await();
                            submittedTestRunnable.run();
                        } catch (final Throwable e) {
                            exceptions.add(e);
                        } finally {
                            allDone.countDown();
                        }
                    }
                });
            }
            // wait until all threads are ready
            assertTrue(
                    "Timeout initializing threads! Perform long lasting initializations before passing runnables to assertConcurrent",
                    allExecutorThreadsReady.await(10L * runnables.size(), TimeUnit.MILLISECONDS));
            // start all test runners
            afterInitBlocker.countDown();
            assertTrue(message + " timeout! More than" + maxTimeoutSeconds + "seconds",
                    allDone.await(maxTimeoutSeconds, TimeUnit.SECONDS));
        } finally {
            threadPool.shutdownNow();
        }
        assertTrue(message + "failed with exception(s)" + exceptions, exceptions.isEmpty());
    }
}
