package com.example.languageexchangebackend.extras;

import com.example.languageexchangebackend.model.Language;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeLanguagesScript {
    public static void main(String[] args) {
        ScrapeLanguagesScript script = new ScrapeLanguagesScript();
        List<Language> scrapedLanguages = script.scrapeLanguages();
        script.saveLanguagesToJSON(scrapedLanguages);
    }

    public List<Language> scrapeLanguages() {
        String url = "https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes";
        List<Language> scrapedLanguages = new ArrayList<>();

        // https://medium.com/@sushain_Dilishan/building-web-scraping-api-with-spring-boot-jsoup-a0cc19dbd5dd
        try {
            //loading the HTML to a Document Object
            Document document = Jsoup.connect(url).get();
            // selecting the element which contains the languages + codes list
            Elements languageRowElements = document.getElementsByTag("tbody").get(0)
                .getElementsByTag("tr");
            for (int i=1; i<languageRowElements.size(); i++) {
                Element languageNameElement = languageRowElements.get(i)
                        .getElementsByTag("td").get(0)
                        .getElementsByTag("a").get(0);
                String languageName = languageNameElement.text();

                Element languageCodeElement = languageRowElements.get(i).getElementsByTag("td").get(1);
                String languageCode = languageCodeElement.text();

                scrapedLanguages.add(new Language(languageName, languageCode, ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scrapedLanguages;
    }

    public void saveLanguagesToJSON(List<Language> scrapedLanguages) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            String dir = "src/main/java/com/example/languageexchangebackend/extras/";
            File languagesFile = new File(dir+"languages.json");
            if (!languagesFile.exists()) {
                languagesFile.createNewFile();
            }

            FileWriter languagesFW = new FileWriter(languagesFile);
            gson.toJson(scrapedLanguages, languagesFW);
            languagesFW.flush();
            languagesFW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved "+scrapedLanguages.size()+" languages.");
    }
}
