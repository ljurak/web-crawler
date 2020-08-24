package webcrawler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 500;

    private static final int DEFAULT_HEIGHT = 600;

    private static final String DEFAULT_TITLE = "Web Crawler";

    private static final Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");

    public WebCrawlerFrame() {
        setTitle(DEFAULT_TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(null);

        JLabel urlLabel = new JLabel("URL:");
        urlLabel.setBounds(10, 10, 50, 25);

        JTextField urlField = new JTextField();
        urlField.setName("UrlTextField");
        urlField.setBounds(60, 10, 300, 25);

        JButton parseButton = new JButton("Parse!");
        parseButton.setName("RunButton");
        parseButton.setBounds(370, 10, 100, 25);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 45, 50, 25);

        JLabel titleContent = new JLabel("");
        titleContent.setName("TitleLabel");
        titleContent.setBounds(60, 45, 400, 25);

        JTextArea content = new JTextArea();
        content.setName("HtmlTextArea");
        content.setEditable(false);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBounds(10, 80, 460, 470);

        parseButton.addActionListener(event -> {
            String url = urlField.getText();
            try (InputStream in = new BufferedInputStream(new URL(url).openStream())) {
                String page = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                Matcher matcher = titlePattern.matcher(page);
                if (matcher.find()) {
                    String title = matcher.group(1);
                    titleContent.setText(title);
                }
                content.setText(page);
            } catch (IOException e) {
                titleContent.setText("");

                content.setText("");
                content.append("Error occurred:\n");
                content.append(e.getMessage());
            }
        });

        add(urlLabel);
        add(urlField);
        add(parseButton);
        add(titleLabel);
        add(titleContent);
        add(scrollPane);
    }
}
