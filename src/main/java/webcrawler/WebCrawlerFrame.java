package webcrawler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebCrawlerFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 500;

    private static final int DEFAULT_HEIGHT = 400;

    private static final String DEFAULT_TITLE = "Web crawler";

    public WebCrawlerFrame() {
        setTitle(DEFAULT_TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JTextArea content = new JTextArea();
        content.setName("HtmlTextArea");
        content.setEditable(false);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);

        JTextField urlField = new JTextField(30);
        urlField.setName("UrlTextField");

        JButton downloadButton = new JButton("Get text!");
        downloadButton.setName("RunButton");

        downloadButton.addActionListener(event -> {
            String url = urlField.getText();
            try (InputStream in = new BufferedInputStream(new URL(url).openStream())) {
                String page = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                content.setText(page);
            } catch (IOException e) {
                content.setText("");
                content.append("Error occurred:\n");
                content.append(e.getMessage());
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(urlField);
        controlPanel.add(downloadButton);

        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(content));
    }
}
