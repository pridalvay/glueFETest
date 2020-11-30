package com.example.ageverifier.tests;

import android.os.Bundle;

import androidx.annotation.NonNull;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberAndroidJUnitRunner;
import cucumber.runtime.android.CucumberAndroidJUnitArguments;

@CucumberOptions(glue = {"com.example.ageverifier.tests"}, features = {"features"}, tags = {"@ageverifier"})
public class AgeVerifierCucumberIntrument extends CucumberAndroidJUnitRunner {

    private CucumberAndroidJUnitArguments cucumberJUnitRunnerCore;

    @Override
    public void onCreate(final Bundle bundle) {
        cucumberJUnitRunnerCore = new CucumberAndroidJUnitArguments(bundle);
        super.onCreate(cucumberJUnitRunnerCore.processArgs());
    }

    @NonNull
    @Override
    public CucumberAndroidJUnitArguments getArguments() {
        return cucumberJUnitRunnerCore;
    }

}
