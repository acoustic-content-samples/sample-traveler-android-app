/*
 * Copyright 2020 Acoustic, L.P.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Apache License, Version 2.0
 *  www.apache.org
 *  Home page of The Apache Software Foundation
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.acoustic.contenthub.sample.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents Timer that uses {@link TimerTask} and provides possibility to pause and resume it.
 */
public class AutoAdvanceTimer {
    private static int AUTO_ADVANCE_TIME_MILLS = 1000;
    private static int DEFAULT_PERIOD = 10 * AUTO_ADVANCE_TIME_MILLS; // default period for 10 seconds.

    private boolean isPaused = false;
    private boolean isStarted = false;
    private long period = DEFAULT_PERIOD;

    private Timer timer;
    private TimerTask task;
    private AutoAdvanceTimerListener listener;

    public AutoAdvanceTimer(AutoAdvanceTimerListener listener) {
        this.listener = listener;
        timer = new Timer();
    }

    /**
     * Starts new timer.
     *
     * @param newPeriod time in seconds for new timer.
     */
    public void startTimer(long newPeriod) {
        if (newPeriod > 0) {
            period = newPeriod * AUTO_ADVANCE_TIME_MILLS;
        }
        clearTimer();

        task = createTimerTask();
        timer.scheduleAtFixedRate(task, period, period);
        isStarted = true;
    }

    /**
     * Cancels current timer, clears all values.
     */
    public void cancelTimer() {
        clearTimer();
        isStarted = false;
        isPaused = false;
    }

    /**
     * Pauses current timer if it was already started and not paused yet.
     */
    public void pauseTimer() {
        if (isStarted && !isPaused) {
            isPaused = true;
            clearTimer();
        }
    }

    /**
     * Resumes current timer if it was already started and paused.
     */
    public void resumeTimer() {
        if (isStarted && isPaused) {
            isPaused = false;

            task = createTimerTask();
            timer.scheduleAtFixedRate(task, period, period);
        }
    }

    private void clearTimer() {
        if (task != null) {
            task.cancel();
        }
        timer.purge();
    }

    private TimerTask createTimerTask() {
        return new TimerTask() {
            public void run() {
                listener.onUpdate();
            }
        };
    }
}
