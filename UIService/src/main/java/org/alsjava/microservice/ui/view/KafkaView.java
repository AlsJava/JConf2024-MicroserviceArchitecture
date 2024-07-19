package org.alsjava.microservice.ui.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.alsjava.microservice.ui.App;

@PageTitle("Kafka")
@Route(value = "/kafka", layout = App.class)
@RouteAlias(value = "kafka", layout = App.class)
public class KafkaView extends Div {

    public KafkaView() {
        // TODO Hacer el view para ver los canales
    }
}
