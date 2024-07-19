package org.alsjava.microservice;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;

@Push
@PWA(name = "Microservice Example UI", shortName = "MSUI")
public class AppShell implements AppShellConfigurator {
}
