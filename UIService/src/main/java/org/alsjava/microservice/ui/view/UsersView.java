package org.alsjava.microservice.ui.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.alsjava.microservice.client.UserClient;
import org.alsjava.microservice.model.UserDTO;
import org.alsjava.microservice.ui.App;

import java.util.Objects;

@PageTitle("Users")
@Route(value = "/users", layout = App.class)
@RouteAlias(value = "users", layout = App.class)
public class UsersView extends Div {

    private final UserClient userClient;

    public UsersView(UserClient userClient) {
        this.userClient = userClient;
        Grid<UserDTO> grid = new Grid<>();
        add(grid);
        grid.addColumn(UserDTO::getId).setHeader("ID");
        grid.addColumn(UserDTO::getName).setHeader("Name");
        grid.addColumn(UserDTO::getDescription).setHeader("Description");
        grid.addColumn(UserDTO::getUserType).setHeader("Type");
        grid.setItems(query -> Objects.requireNonNull(this.userClient.list(query.getOffset(), query.getLimit()).getBody()).getUsers().stream());
    }
}
