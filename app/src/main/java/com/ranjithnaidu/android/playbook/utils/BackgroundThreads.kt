package com.ranjithnaidu.android.playbook.utils

import java.util.concurrent.*

/**
 * A ThreadPoolExecutor that exposes Exceptions to the real world
 */
class BackgroundThreads : ThreadPoolExecutor(1, 4, 1, TimeUnit.MINUTES, LinkedBlockingQueue<Runnable>(), NamedThreadFactory()) {

    override fun afterExecute(r: Runnable?, t: Throwable?) {
        super.afterExecute(r, t)
        var throwable: Throwable? = t
        if (t == null && r is Future<*>) {
            try {
                val future = r as Future<*>
                if (future.isDone) {
                    future.get()
                }
            } catch (ce: CancellationException) {
                throwable = ce
            } catch (ee: ExecutionException) {
                throwable = ee.cause
            } catch (ie: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
        if (throwable != null) {
            throw throwable
        }
    }

    /**
     * Add -background ot the name of the thread to help debugging
     */
    class NamedThreadFactory : ThreadFactory {
        override fun newThread(r: Runnable?): Thread {
            val thread = Executors.defaultThreadFactory().newThread(r)
            thread.name = thread.name + "-background"
            return thread
        }
    }
}