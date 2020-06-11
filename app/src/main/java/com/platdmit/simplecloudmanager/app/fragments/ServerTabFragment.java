package com.platdmit.simplecloudmanager.app.fragments;

public interface ServerTabFragment<F>{
    CharSequence getTitle();
    F getInstance();
}
