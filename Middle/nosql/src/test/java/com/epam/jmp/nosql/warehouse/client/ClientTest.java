package com.epam.jmp.nosql.warehouse.client;

import com.epam.jmp.nosql.warehouse.model.Note;
import com.epam.jmp.nosql.warehouse.model.Notebook;
import com.epam.jmp.nosql.warehouse.model.SimpleNote;
import com.epam.jmp.nosql.warehouse.model.SimpleNotebook;
import com.epam.jmp.nosql.warehouse.utils.MongoNotebookManager;
import com.epam.jmp.nosql.warehouse.utils.NotebookManager;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Разверните MongoDB.
 Создайте простейшее консольное приложение которое сохраняет ваши заметки в БД.
 Заметка содержит 3 поля: Дата создания, Тег для записи, Текст записи.
 Например: 10.13.2016, "Домашние дела", "Нужно купить молоко"

 Организуйте выборку из базы данных и вывод в консоль:
 1 Все записи
 2 По тегу
 3 Полнотекстовый поиск по 2 и 3 полю.

 Добавьте возможность удаления найденных записей.

 Tests passed:
 https://monosnap.com/file/jEQPTEr0I8OoO8zBgPKbysKj7W07Av.png
 */

public class ClientTest {
    private static NotebookManager notebookManager;
    private static Notebook notebook;

    private String[] adj1 = new String[] {"солнечный","траурный","плюшевый","бешеный","памятный","трепетный","базовый","скошенный","преданный","ласковый","пойманный","радужный","огненный","радостный","тензорныи","шёлковый","пепельный","ламповый","жареный","загнанный"};
    private String [] noun1 = new String[] {"зайчик","Верник","глобус","ветер","щавель","пёсик","копчик","ландыш","стольник","мальчик","дольщик","Игорь","невод","егерь","пончик","лобстер","жемчуг","кольщик","иогурт","овод"};
    private String[] adj2 = new String[] {"стеклянного","ванильного","резонного","широкого","дешёвого","горбатого","собачьего","исконного","волшебного","картонного","лохматого","арбузного","огромного","запойного","великого","бараньего","ваниального","едрёного","парадного","укромного"};
    private String[] noun2 = new String[] {"зайчика","плова","Пельша","мира","зайчика","жира","мема","ада","бура","жала","нёба","гунна","хлама","шума","воза","сала","фена","зала","рака","свода"};

    @BeforeClass
    public static void setUp() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        notebookManager = (NotebookManager) context.getBean("notebookManager");
        notebook = notebookManager.getNotebook("letov");
        ((SimpleNotebook)notebook).drop();
    }

    @Before
    public void populateDatabase() {
        for (int i = 0; i < noun1.length; i ++) {
            String title = adj1[i] + " " + noun1[i];
            String text = adj1[i] + " " + noun1[i] + " " + adj2[i] + " " + noun2[i];
            Note note = new SimpleNote(title, text);
            notebook.saveNote(note);
        }
    }

    @After
    public void dropDatabase() {
        ((SimpleNotebook)notebook).drop();
    }

    @Test
    public void testNotesWereAddedToDatabase() {
        assertTrue(notebook.count() == 20);
        System.out.println(notebook.find(""));
    }

    @Test
    public void testSearchNoteByTag() {
        List<Note> result = notebook.findByTitle("солнечный зайчик");
        assertFalse(result.isEmpty());
        result.forEach(System.out::println);
    }

    @Test
    public void testSearchNoteByText() {
        List<Note> result = notebook.find("зайчик");
        assertFalse(result.isEmpty());
        result.forEach(System.out::println);
    }

    @Test
    public void testDeleteNote() throws InterruptedException {
        notebook.delete(new SimpleNote("солнечный зайчик", "солнечный зайчик стеклянного зайчика"));
        System.out.println(notebook.count());
        assertTrue(notebook.count() == 19);

    }


    @AfterClass
    public static void tearDown() {
        ((MongoNotebookManager)notebookManager).close();
    }

}