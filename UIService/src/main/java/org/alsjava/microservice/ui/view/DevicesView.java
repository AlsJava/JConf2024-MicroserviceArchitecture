package org.alsjava.microservice.ui.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.alsjava.microservice.client.DeviceClient;
import org.alsjava.microservice.client.UserClient;
import org.alsjava.microservice.model.DeviceDTO;
import org.alsjava.microservice.model.UserDTO;
import org.alsjava.microservice.ui.App;

import java.util.Objects;
import java.util.stream.Stream;

@PageTitle("Devices")
@Route(value = "/devices", layout = App.class)
@RouteAlias(value = "devices", layout = App.class)
public class DevicesView extends Div {

    private final DeviceClient deviceClient;
    private final UserClient userClient;

    private final Grid<DeviceDTO> grid = new Grid<>();
    private final ComboBox<UserDTO> cbUser = new ComboBox<>();

    public DevicesView(DeviceClient deviceClient, UserClient userClient) {
        this.deviceClient = deviceClient;
        this.userClient = userClient;
        add(new HorizontalLayout(cbUser));
        add(grid);
        cbUser.setMinWidth(300, Unit.PIXELS);
        cbUser.setItemLabelGenerator(userDTO -> userDTO.getName() + " -- " + userDTO.getId().toString());
        grid.addColumn(DeviceDTO::getId).setHeader("ID");
        grid.addColumn(DeviceDTO::getName).setHeader("Name");
        grid.addColumn(DeviceDTO::getDescription).setHeader("Description");
        grid.addColumn(DeviceDTO::getDeviceType).setHeader("Type");
        grid.setItems(query -> {
            UserDTO userDTO = cbUser.getValue();
            if (userDTO == null) {
                return Stream.empty();
            }
            return Objects.requireNonNull(this.deviceClient.list(userDTO.getId(), query.getPage(), query.getPageSize()).getBody()).getDevices().stream();
        }, query -> {
            UserDTO userDTO = cbUser.getValue();
            if (userDTO == null) {
                return 0;
            }
            Long count = this.deviceClient.count(userDTO.getId()).getBody();
            return count == null ? 0 : count.intValue();
        });
        cbUser.setItems(query -> Objects.requireNonNull(this.userClient.list(query.getPage(), query.getPageSize()).getBody()).getUsers().stream());
        cbUser.addValueChangeListener(comboBoxUserDTOComponentValueChangeEvent -> grid.getDataProvider().refreshAll());
    }
}
