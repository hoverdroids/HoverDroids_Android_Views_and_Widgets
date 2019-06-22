/*
 * Copyright (C) 2019. Christopher Sprague
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hoverdroids.dialogs;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Util class to manage showing dialogs.
 *
 * @author Chris Sprague
 */
public class DialogUtils {

    public static String showDialogFragment(FragmentManager fragmentManager, DialogFragment dialogFragment,
                                            String fragmentTag, boolean onlyIfNotDuplicate) {

        // If only showing non duplicates dialogs, make sure the fragment isn't already in the manager
        boolean doesFragmentExist = fragmentManager.findFragmentByTag(fragmentTag) != null;
        if (!(onlyIfNotDuplicate && doesFragmentExist)) {
            dialogFragment.show(fragmentManager, fragmentTag);
        }

        return fragmentTag;
    }

    public static String showDialogFragment(FragmentManager fragmentManager, DialogFragment dialogFragment,
                                            boolean onlyIfNotDuplicate) {
        return showDialogFragment(fragmentManager, dialogFragment, generateFragmentTag(dialogFragment), onlyIfNotDuplicate);
    }

    public static String showDialogFragment(FragmentManager fragmentManager, DialogFragment dialogFragment,
                                            String fragmentTag) {
        return showDialogFragment(fragmentManager, dialogFragment, fragmentTag, true);
    }

    public static String showDialogFragment(FragmentManager fragmentManager, DialogFragment dialogFragment) {
        return showDialogFragment(fragmentManager, dialogFragment, generateFragmentTag(dialogFragment));
    }

    private static String generateFragmentTag(Fragment fragment) {
        return fragment.getClass().getName();
    }
}