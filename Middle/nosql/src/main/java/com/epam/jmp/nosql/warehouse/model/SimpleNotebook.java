package com.epam.jmp.nosql.warehouse.model;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by Nikolay_Vasilishin on 6/27/2017.
 */
public class SimpleNotebook implements Notebook {

    public static final int LIMIT = 100;
    private final MongoCollection<Document> collection;

    public SimpleNotebook(MongoCollection<Document> collection) {
        this.collection = collection;
    }


    @Override
    public long count() {
        return collection.count();
    }

    @Override
    public void saveNote(Note note) {
        Document document = new Document();
        document.putAll(note.getContents());
        collection.insertOne(document);
    }

    /**
     * Case sensitive search by title.
     *
     * @param title
     * @return list of matched notes
     */
    @Override
    public List<Note> findByTitle(String title) {
        //Didn't find how to get it working with Stream API.
        FindIterable<Document> documents = collection.find(eq(Note.TITLE_FIELD, title));
        return getNotesFrom(documents);
    }

    /**
     * Find occurence of string in title or text
     * @param anyText
     * @return
     */
    @Override
    public List<Note> find(String anyText) {
        String predicate = ".*" + anyText + ".*";
        FindIterable<Document> documents = collection.find(
                or(
                        regex(Note.TITLE_FIELD, predicate),
                        regex(Note.TEXT_FIELD, predicate)
                ));
        return getNotesFrom(documents);
    }

    @Override
    public boolean delete(Note note) {
        return !collection.findOneAndDelete(
                or(
                        eq(Note.TITLE_FIELD, note.title),
                        eq(Note.TEXT_FIELD, note.text))
        ).isEmpty();
    }


    public void drop() {
        collection.drop();
    }

    private List<Note> getNotesFrom(FindIterable<Document> documents) {
        List<Note> notes = new ArrayList<>();
        documents.limit(LIMIT).forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                String title = (String) document.get(Note.TITLE_FIELD);
                String text = (String) document.get(Note.TEXT_FIELD);
                LocalDateTime date = Note.timestampToDate((long) document.get(Note.DATE_FIELD));

                notes.add(new SimpleNote(title, text, date));
            }
        });
        return notes;
    }

}
