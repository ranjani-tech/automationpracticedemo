package com.globaltechchallenge.automationpracticedemo.web;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/globaltechchallenge/automationpracticedemo/web")

@ConfigurationParameters({
        @ConfigurationParameter(
                key =        PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME,
                value="false"
        ),
        @ConfigurationParameter(
                key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME,
                value="true"
        ),
        @ConfigurationParameter(
                key = PLUGIN_PROPERTY_NAME,
                value ="pretty, html:cucumber-reports/cucumber-reports.html"
        )
})
public class RunCucumberTest {
}
