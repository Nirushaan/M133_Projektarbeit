package ch.bzz.projekt.service;


import ch.bzz.projekt.model.Tier;
import ch.bzz.projekt.model.Zoowaerter;
import org.intellij.lang.annotations.Pattern;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * provides services for the bookshelf
 * <p>
 * M133: Bookshelf
 *
 * @author Marcel Suter
 */
@Path("book")
public class TierService {

    /**
     * produces a map of all books
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listTier(
    ) {
        Map<String, Tier> tierMap = new Zoowaerter().getTierMap();
        Response response = Response
                .status(200)
                .entity(tierMap)
                .build();
        return response;

    }


    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)

    public Response readTier(
            @Pattern(regexp = "[0-9a-fA-F]{8}-(0-9a-fA-F{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("tierID") String tierID
    ) {
        Tier tier = null;
        int httpStatus;

        try {
            UUID.fromString(tierID);
            tier = new Zoowaerter().getTierByTierID(tierID);
            if (tier != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(tier)
                .build();
        return response;
    }

    /**
     * creates a new book
     * @param book valid a Book-Object
     * @param publisherUUID the unique key of the publisher
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createTier(
            @Valid @BeanParam Tier tier

    ) {
        int httpStatus = 200;
        Zoowaerter zoowaerter = new Zoowaerter();
        tier.setTierID(UUID.randomUUID().toString());
        zoowaerter.getTierMap().put(tier.getTierID(), tier);
        DataHandler.writeBooks(zoowaerter.getTierMap());

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * updates an existing book
     * @param book valid a Book-Object
     * @param publisherUUID the unique key of the publisher
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateTier(
            @Pattern(regexp = "[0-9a-fA-F]{8}-(0-9a-fA-F{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("bookUUID") String bookUUID,
            @Valid @BeanParam Book book,
            @Pattern(regexp = "[0-9a-fA-F]{8}-(0-9a-fA-F{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("publisherUUID") String publisherUUID
    ) {
        int httpStatus = 200;
        try {
            UUID.fromString(bookUUID);
            Bookshelf bookshelf = new Bookshelf();
            book = bookshelf.getBookByUUID(bookUUID);
            if (book != null) {
                httpStatus = 200;
                DataHandler.writeBooks(bookshelf.getBookMap());
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * deletes an existing book identified by its uuid
     * @param bookUUID  the unique key for the book
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTier(
            @Pattern(regexp = "[0-9a-fA-F]{8}-(0-9a-fA-F{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String bookUUID
    ) {
        int httpStatus;
        try {
            UUID.fromString(bookUUID);
            Bookshelf bookshelf = new Bookshelf();
            Book book = bookshelf.getBookByUUID(bookUUID);
            if (book != null) {
                httpStatus = 200;
                bookshelf.getBookMap().remove(book);
                DataHandler.writeBooks(bookshelf.getBookMap());
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }


}
