
package ch.bzz.projekt.service;

        import ch.bzz.projekt.data.DataHandler;
        import ch.bzz.projekt.model.Tier;
        import ch.bzz.projekt.model.Zoo;
        import ch.bzz.projekt.model.Publisher;

        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.math.BigDecimal;
        import java.util.Map;
        import java.util.UUID;


        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.math.BigDecimal;
        import java.util.Map;
        import java.util.UUID;

/**
 * short description
 * <p>
 * Projektarbeit
 *
 * @author TODO
 * @version 1.0
 * @since 12.03.20
 */
public class TierService {


    /**
     * produces a map of all books
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listBooks(
    ) {
        Map<String, Book> bookMap = new Bookshelf().getBookMap();
        Response response = Response
                .status(200)
                .entity(bookMap)
                .build();
        return response;

    }

    /**
     * reads a single book identified by the bookId
     *
     * @param bookUUID the bookUUID in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)

    public Response readBook(
            @QueryParam("uuid") String bookUUID
    ) {
        Book book = null;
        int httpStatus;

        try {
            UUID.fromString(bookUUID);
            book = new Bookshelf().getBookByUUID(bookUUID);
            if (book != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(book)
                .build();
        return response;
    }

    /**
     * creates a new book
     * @param title the book title
     * @param author the author
     * @param publisherUUID the unique key of the publisher
     * @param price the price
     * @param isbn the isbn-13 number
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBook(
            @FormParam("title") String title,
            @FormParam("author") String author,
            @FormParam("publisherUUID") String publisherUUID,
            @FormParam("price") BigDecimal price,
            @FormParam("isbn") String isbn
    ) {
        int httpStatus = 200;
        Book book = new Book();
        Bookshelf bookshelf = new Bookshelf();
        book.setBookUUID(UUID.randomUUID().toString());
        setValues(
                book,
                title,
                author,
                publisherUUID,
                price,
                isbn
        );

        bookshelf.getBookMap().put(book.getBookUUID(), book);
        DataHandler.writeBooks(bookshelf.getBookMap());

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * updates an existing book
     * @param title the book title
     * @param author the author
     * @param publisherUUID the unique key of the publisher
     * @param price the price
     * @param isbn the isbn-13 number
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @FormParam("bookUUID") String bookUUID,
            @FormParam("title") String title,
            @FormParam("author") String author,
            @FormParam("publisherUUID") String publisherUUID,
            @FormParam("price")BigDecimal price,
            @FormParam("isbn") String isbn
    ) {
        int httpStatus = 200;
        Book book;
        try {
            UUID.fromString(bookUUID);
            Bookshelf bookshelf = new Bookshelf();
            book = bookshelf.getBookByUUID(bookUUID);
            if (book != null) {
                httpStatus = 200;
                setValues(
                        book,
                        title,
                        author,
                        publisherUUID,
                        price,
                        isbn
                );
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
    public Response deleteBook(
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

    /**
     * sets the attribute values of the book object
     * @param book  the book object
     * @param title the book title
     * @param author the author
     * @param publisherUUID the unique key of the publisher
     * @param price the price
     * @param isbn the isbn-13 number
     */
    private void setValues(
            Book book,
            String title,
            String author,
            String publisherUUID,
            BigDecimal price,
            String isbn) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setIsbn(isbn);

        Publisher publisher = DataHandler.getPublisherMap().get(publisherUUID);
        book.setPublisher(publisher);
    }
}
