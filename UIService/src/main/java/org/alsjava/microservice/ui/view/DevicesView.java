package org.alsjava.microservice.ui.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.alsjava.microservice.client.DeviceClient;
import org.alsjava.microservice.model.DeviceDTO;
import org.alsjava.microservice.model.UserDTO;
import org.alsjava.microservice.ui.App;

@PageTitle("Devices")
@Route(value = "/devices", layout = App.class)
@RouteAlias(value = "devices", layout = App.class)
public class DevicesView extends Div {

    private final DeviceClient deviceClient;

    private final Grid<DeviceDTO> grid = new Grid<>();
    private final ComboBox<UserDTO> cbUser = new ComboBox<>();

    public DevicesView(DeviceClient deviceClient) {
        this.deviceClient = deviceClient;
        add(grid);
        grid.addColumn(DeviceDTO::getId).setHeader("ID");
        grid.addColumn(DeviceDTO::getName).setHeader("Name");
        grid.addColumn(DeviceDTO::getDescription).setHeader("Description");
        grid.addColumn(DeviceDTO::getDeviceType).setHeader("Type");
//        grid.setItems(query -> deviceClient.list(query.getPage(), query.getPageSize()).stream());
    }
}
