package org.alsjava.microservice.ui.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.alsjava.microservice.model.UserDTO;
import org.alsjava.microservice.ui.App;

@PageTitle("Users")
@Route(value = "/users", layout = App.class)
@RouteAlias(value = "users", layout = App.class)
public class UsersView extends Div {

    private final Grid<UserDTO> grid = new Grid<>();

    public UsersView() {
        add(grid);
        grid.addColumn(UserDTO::getId).setHeader("ID");
        grid.addColumn(UserDTO::getName).setHeader("Name");
        grid.addColumn(UserDTO::getDescription).setHeader("Description");
        grid.addColumn(UserDTO::getUserType).setHeader("Type");
        // TODO falta la data
        // TODO crear la forma de crear un usuario y con sus variantes
    }
}
