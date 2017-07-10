package server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import server.model.Booking;
import server.model.Client;

/**
 * Created by ileossa on 09/07/2017.
 */
@Service
public class GeneratorBuilder implements IGeneratorBuilder{
    private Generator generator;

    @Autowired
    public GeneratorBuilder(Generator generator) {
        this.generator = generator;
    }

    @Override
    public Generator build() {
        return generator;
    }

    @Override
    public IGeneratorBuilder setClient(final Client client) {
        generator.setClient(client);
        return this;
    }

    @Override
    public IGeneratorBuilder setBooking(final Booking booking) {
        generator.setBooking(booking);
        return this;
    }

    @Override
    public IGeneratorBuilder pathSave(String path) {
        generator.pathSave(path);
        return this;
    }

    @Override
    public IGeneratorBuilder loadTemplate(String pathTemplate) {
        generator.loadTemplate(pathTemplate);
        return this;
    }

    @Override
    public IGeneratorBuilder formatOut(ListFormatsSupport formatsSupport) {
        generator.formatOut(formatsSupport);
        return this;
    }

    @Override
    public IGeneratorBuilder execute() {
        generator.execute();
        return this;
    }


}
