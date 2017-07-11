package server.service.impl;

import server.model.Booking;
import server.model.Client;

/**
 * Created by ileossa on 09/07/2017.
 */
public interface IGeneratorBuilder {

    Generator  build();

    IGeneratorBuilder setClient(final Client client);

    IGeneratorBuilder setBooking(final Booking booking);

    IGeneratorBuilder pathSave(String path);

    IGeneratorBuilder loadTemplate(String pathTemplate);

    IGeneratorBuilder formatOut(ListFormatsSupport listFormatsSupport);

    IGeneratorBuilder execute();
}
