import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class NewsHandler {

    private List<String> ignore = new ArrayList<>();
    private TextArea te;
    private StringBuilder sb = new StringBuilder();

    public void addIgnore(String ignore) {
        this.ignore.add(ignore);
    }

    public void setTextAre(TextArea te) {
        this.te = te;
    }


    public void start() {
        sb.setLength(0);
        String walla = walla();
        String ynet = ynet();

        sb.append("walla: " + walla + "\n");
        sb.append("ynet: " + ynet + "\n");
        te.setText(sb.toString());
    }

    public String walla() {
        Map<String, Integer> wordsMap = new HashMap<>();

        try {
            Document website =//מחלקה קיימת בJSOUp
                    Jsoup

                            .connect("https://www.walla.co.il/").get();
            String title = website.title();
            System.out.println(title);

            ArrayList<Element> elementsList = website.getElementsByClass("with-roof ");
            for (int i = 0; i < elementsList.size(); i++) {
                Element currentElement = elementsList.get(i);
                Element linkElement = currentElement.child(0);
                System.out.println(currentElement.text());
                String LinkToArticle = linkElement.attr("href");
                System.out.println(currentElement.text());
                Document article = Jsoup.connect(LinkToArticle).get();
                List<Element> articleBody = article.getElementsByClass("text-content");
                // System.out.println(articleBody.get(0));
                String body = articleBody.get(0).text();
                String[] words = body.split(" ");

                Integer temp = 0;
                for (int j = 0; j < words.length; j++) {
                    String currentWord = words[j];
                    if (!ignore.contains(currentWord)) {
                        if (wordsMap.containsKey(currentWord)) {
                            wordsMap.put(currentWord, wordsMap.get(currentWord) + 1);
                        } else {
                            wordsMap.put(currentWord, 1);
                        }
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String maxOfAll = "";
        for (int j = 1; j <= 10; j++) {
            int max = 0;
            String max_w = "";
            for (Map.Entry<String, Integer> e : wordsMap.entrySet()) {
                if (e.getValue() > max) {
                    max_w = e.getKey();
                    max = e.getValue();
                }
            }
            if (j == 1) {
                maxOfAll = j + ". " + max_w + " (" + max + " times)";
            }
            sb.append(j + ". " + max_w + " (" + max + " times)\n");
            System.out.println(j + ". " + max_w + " (" + max + " times)");
            wordsMap.remove(max_w);
        }
        return maxOfAll;
    }

    public String ynet() {

        Map<String, Integer> wordsMap = new HashMap<>();
        try {
            Document website = Jsoup.connect("https://www.ynet.co.il/").get();
            String title = website.title();
            System.out.println(title);
            ArrayList<Element> elementsList = website.getElementsByClass("textDiv");
            Element currentElement = elementsList.get(1);
            Element linkElement = currentElement.child(0);
            String LinkToArticle = linkElement.attr("href");

            Document article = Jsoup.connect(LinkToArticle).get();
            List<Element> articleBody = article.select("span[data-text]");


            for (int i = 0; i < articleBody.size(); i++) {
                String body = articleBody.get(i).text();
                System.out.println(body);
                String[] words = body.split(" ");

                for (int j = 0; j < words.length; j++) {
                    String currentWord = words[j];

                    if (!ignore.contains(currentWord)) {
                        if (wordsMap.containsKey(currentWord)) {
                            wordsMap.put(currentWord, wordsMap.get(currentWord) + 1);
                        } else {
                            wordsMap.put(currentWord, 1);
                        }
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String maxOfAll = "";
        for (int j = 1; j <= 10; j++) {
            int max = 0;
            String max_w = "";
            for (Map.Entry<String, Integer> e : wordsMap.entrySet()) {
                if (e.getValue() > max) {
                    max_w = e.getKey();
                    max = e.getValue();
                }
            }
            if (j == 1) {
                maxOfAll = j + ". " + max_w + " (" + max + " times)";
            }
            System.out.println(j + ". " + max_w + " (" + max + " times)");
            wordsMap.remove(max_w);
            sb.append(j + ". " + max_w + " (" + max + " times)\n");


        }
        return maxOfAll;
    }
}


