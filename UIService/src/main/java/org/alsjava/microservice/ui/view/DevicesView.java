package org.alsjava.microservice.ui.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.alsjava.microservice.model.DeviceDTO;
import org.alsjava.microservice.ui.App;

@PageTitle("Devices")
@Route(value = "/devices", layout = App.class)
@RouteAlias(value = "devices", layout = App.class)
public class DevicesView extends Div {

    private final Grid<DeviceDTO> grid = new Grid<>();

    public DevicesView() {
        add(grid);
        grid.addColumn(DeviceDTO::getId).setHeader("ID");
        grid.addColumn(DeviceDTO::getName).setHeader("Name");
        grid.addColumn(DeviceDTO::getDescription).setHeader("Description");
        grid.addColumn(DeviceDTO::getDeviceType).setHeader("Type");
        // TODO falta la data
        // TODO adicionar la logica para crear dispositivos.
    }
}
