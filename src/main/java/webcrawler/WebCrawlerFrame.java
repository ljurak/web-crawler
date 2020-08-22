package webcrawler;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class WebCrawlerFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 300;

    private static final int DEFAULT_HEIGHT = 300;

    private static final String DEFAULT_TITLE = "Web crawler";

    public WebCrawlerFrame() {
        setTitle(DEFAULT_TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JTextArea content = new JTextArea();
        content.setName("TextArea");
        content.setText("HTML code?");
        content.setEnabled(false);

        add(content);
    }
}
