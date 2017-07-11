package server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import server.model.Booking;
import server.model.Client;

/**
 * Created by ileossa on 09/07/2017.
 */
@Service
public class Generator {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    private Client client;
    private Booking booking;
    @Value("${path.default.generator.file}")
    private String pathFile;
    @Value("${path.default.generator.template}")
    private String pathTemplate;

    ListFormatsSupport formatOutput;

    public Generator() {  }

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(final Booking booking) {
        this.booking = booking;
    }

    public void pathSave(String path){
        this.pathFile = path;
    }

    public void loadTemplate(String pathTemplate){
        this.pathTemplate = pathTemplate;
    }

    public void formatOut(ListFormatsSupport formatsSupport){
        this.formatOutput = formatsSupport;
    }

    public void execute(){
    }

}


