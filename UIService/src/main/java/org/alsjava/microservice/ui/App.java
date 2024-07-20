package org.alsjava.microservice.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.alsjava.microservice.ui.view.DevicesView;
import org.alsjava.microservice.ui.view.UsersView;
import org.springframework.boot.info.BuildProperties;

@Route("")
public class App extends AppLayout {

    private final H1 viewTitle;

    public App(BuildProperties buildProperties) {
        H1 appTitle = new H1(buildProperties.getName());
        appTitle.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)");
        Tabs views = getPrimaryNavigation();
        DrawerToggle toggle = new DrawerToggle();
        viewTitle = new H1();
        viewTitle.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        Div panel = new Div();
        HorizontalLayout wrapper = new HorizontalLayout(toggle, viewTitle);
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setSpacing(false);
        VerticalLayout viewHeader = new VerticalLayout(wrapper, panel);
        viewHeader.setPadding(false);
        viewHeader.setSpacing(false);
        addToDrawer(appTitle, views);
        addToNavbar(viewHeader);
        setPrimarySection(Section.DRAWER);
        addAttachListener(event -> {
            if (getContent() != null) {
                changeTitle();
            }
        });
    }

    private Tabs getPrimaryNavigation() {
        Tabs tabs = new Tabs();
        tabs.add(
                createTab(VaadinIcon.SERVER, "Devices", DevicesView.class),
                createTab(VaadinIcon.USERS, "Users", UsersView.class)
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setSelectedIndex(0);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> view) {
        RouterLink link = new RouterLink();
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("padding", "var(--lumo-space-xs)");
        link.add(icon);
        link.add(new Span(viewName));
        link.setRoute(view);
        link.setTabIndex(-1);
        return new Tab(link);
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        changeTitle();
    }

    private void changeTitle() {
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
